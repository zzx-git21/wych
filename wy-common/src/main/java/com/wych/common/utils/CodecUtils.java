package com.wych.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.replace;

public class CodecUtils {

    public static String md5Hex(String data,String salt) {
        //java.lang
        //common-lang
        if (isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }

    public static String generateSalt(){
        return replace(UUID.randomUUID().toString(), "-", "");
    }

}