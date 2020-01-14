package com.wych.mapper;

import com.wych.pojo.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CarMapper extends Mapper<Car> {
    @Select(value = {" <script>" +
            " SELECT tb_car.* FROM tb_car  " +
            " <where> 1=1 " +
            " <if test=' carType !=null  ' >  AND tb_car.car_type =#{carType}</if> " +

            " <if test='  lineType!=null ' >  AND tb_car.line_type =#{lineType}</if> " +

            " <if test=\"startAddress!='' and startAddress != null  \">  AND  tb_car.start_address=#{startAddress}</if> " +
            " <if test=\"endAddress != '' and endAddress != null \" >  AND  tb_car.end_address=#{endAddress}</if> " +

            " <if test='maxPrice != null  ' >  AND  tb_car.price &lt;= #{maxPrice} </if> " +
            " <if test='minPrice != null  ' >  AND  tb_car.price  &gt;=#{minPrice} </if> " +//
            " <if test=' maxWeight != null ' >  AND  tb_car.car_weight &lt;= #{maxWeight}</if> " +
            " <if test=' minWeight != null ' >  AND  tb_car.car_weight &gt;=#{minWeight}</if> " +
            " </where>" +
            "order by tb_car.id ASC" +
            " </script>"})
    List<Car> selectListCar(@Param("carType") Integer carType, @Param("endAddress") String endAddress,
                            @Param("startAddress") String startAddress, @Param("lineType") Integer lineType,
                            @Param("maxPrice") Double maxPrice, @Param("minPrice") Double minPrice, @Param("minWeight") Double minWeight, @Param("maxWeight") Double maxWeight);

   @Select(" select car_type ,goods_type,line_type,price,start_address, end_address \n" +
           "        from tb_car")
    List<Map<String, Object>> exportCarInfo();
}
