package com.wych.service;

import com.wych.common.enums.ExceptionEnums;
import com.wych.common.exception.WlkgException;
import com.wych.common.utils.CodecUtils;
import com.wych.common.utils.NumberUtils;
import com.wych.mapper.UserMapper;
import com.wych.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    static final String KEY_PREFIX = "user:code:phone:";

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private AmqpTemplate amqpTemplate;

    public Boolean sendVerfyCode(String phone) {

        try {
            //- 1）我们接收页面发送来的手机号码
            //- 2）生成一个随机验证码
            String code = NumberUtils.generateCode(6);

            //- 3）将验证码保存在服务端
            redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);

            //- 4）发送短信，将验证码发送到用户手机
            Map<String, String> msg = new HashMap<>();
            msg.put("code", code);
            msg.put("phone", phone);
            amqpTemplate.convertAndSend("wych.sms.exchange", "sms.verify.code", msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public void register(User user, String code) {

        //- 1）校验短信验证码
        String key = KEY_PREFIX + user.getPhone();
        String redisCode = redisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(redisCode)) {
            throw new WlkgException(ExceptionEnums.REGISTER_CODE_IS_ERROR);
        }

        //- 2）生成盐
        String salt = CodecUtils.generateSalt();

        //- 3）对密码加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        //- 5）删除Redis中的验证码
        // 写入数据库
        boolean boo = userMapper.insertSelective(user) == 1;

        // 如果注册成功，删除redis中的code
        if (boo) {
            try {
                this.redisTemplate.delete(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //完善信息
    public void updateUser( User user) {



        userMapper.updateByPrimaryKeySelective(user);

    }


    //查询手机号是否存在
    public boolean selectData(String data) {

        User user = new User();
        user.setPhone(data);

        return userMapper.selectCount(user) == 0;


    }


    //个人信息资料回显
    public User selecetUserInfo(Long id) {

        User user = userMapper.selectByPrimaryKey(id);


        return user;
    }
}
