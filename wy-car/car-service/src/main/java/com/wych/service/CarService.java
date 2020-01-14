package com.wych.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wych.mapper.CarMapper;
import com.wych.mapper.CarTypeMapper;
import com.wych.mapper.LineMapper;
import com.wych.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CarService {

   @Autowired
   private CarMapper carMapper;
   @Autowired
   private CarTypeMapper carTypeMapper;
   @Autowired
   private LineMapper lineMapper;

   //发布车源
    @Transactional
   public void  saveCar(Car car) {
        System.out.println(car);
       carMapper.insertSelective(car);
    }

    public PageResult<Car> queryCarList(SearchDTO searchDTO, Integer page, Integer rows) {
        //System.out.println("xxx"+searchDTO);
        PageHelper.startPage(page,rows);
        List<Car> list= carMapper.selectAll();
        for(Car car:list){

            CarType carType = new CarType();
            carType.setId(car.getCarType());
            CarType carType1 = carTypeMapper.selectOne(carType);
            car.setCarTypeName(carType1.getCarTypeName());

            LineType lineType = new LineType();
            lineType.setId(car.getLineType());
            LineType lineType1 = lineMapper.selectOne(lineType);
            car.setLineTypeName(lineType1.getLineName());

        }
        PageInfo<Car> pageInfo = new PageInfo<Car>(list);
        return new PageResult<>(pageInfo.getTotal(),(long)pageInfo.getPages(), list);

    }

    public PageResult<Car> selectCarList(SearchDTO searchDTO1, Integer page, Integer rows) {
        // 1、查询SPU
        // 分页,最多允许查100条
        PageHelper.startPage(page,rows);
        System.out.println(searchDTO1);
        if(searchDTO1.getCarTypeId ()==0||searchDTO1.getCarTypeId ()==-1){
            searchDTO1.setCarTypeId(null);
        }
      /* CarType carType=new CarType();
        carType.setId(searchDTO1.getCarTypeId ());
        carTypeMapper.selectOne(carType);*/
        if(searchDTO1.getLineType ()==0||searchDTO1.getLineType ()==-1){
            searchDTO1.setLineType(null);
            System.out.println(searchDTO1.getLineType());
        }
        if(searchDTO1.getPrice ()==0||searchDTO1.getPrice ()==-1){
            searchDTO1.setPrice(0);
        }
        if(searchDTO1.getCarWeight ()==0||searchDTO1.getCarWeight ()==-1){
            searchDTO1.setCarWeight(0);
        }
        Integer price = searchDTO1.getPrice();
        switch (price){
            case 0:
                searchDTO1.setMaxPrice(null);
                searchDTO1.setMinPrice(null);
                break;
            case 2:
                searchDTO1.setMaxPrice(null);
                searchDTO1.setMinPrice(0.0);
                break;
            case  3:
                searchDTO1.setMaxPrice(5000.0);
                searchDTO1.setMinPrice(0.0);
                break;
            case  4:
                searchDTO1.setMaxPrice(10000.0);
                searchDTO1.setMinPrice(5000.0);
                break;
            case  5:
                searchDTO1.setMaxPrice(15000.0);
                searchDTO1.setMinPrice(10000.0);
                break;
            case  6:
                searchDTO1.setMaxPrice(null);
                searchDTO1.setMinPrice(15000.0);
                break;
        }
        switch (searchDTO1.getCarWeight()){
            case -1:
                searchDTO1.setMaxWeight(null);
                searchDTO1.setMinWeight(null);
                break;
            case 2:
                searchDTO1.setMaxWeight(30.0);

                searchDTO1.setMinWeight(0.0);
                break;
            case  3:
                searchDTO1.setMaxWeight(300.0);
                searchDTO1.setMinWeight(30.0);
                break;
            case  4:
                searchDTO1.setMaxWeight(1000.0);
                searchDTO1.setMinWeight(300.0);
                break;
            case  5:
                searchDTO1.setMaxWeight(2000.0);
                searchDTO1.setMinWeight(1000.0);
                break;
            case  6:
                searchDTO1.setMaxWeight(2000.0);
                searchDTO1.setMinWeight(null);
                break;
        }


        List<Car> list= carMapper.selectListCar(searchDTO1.getCarTypeId(),searchDTO1.getEndAddress(),
                searchDTO1.getStartAddress(), searchDTO1.getLineType(),
                searchDTO1.getMaxPrice(),searchDTO1.getMinPrice(),searchDTO1.getMinWeight(),searchDTO1.getMaxWeight());

        for(Car car:list){

            CarType carType = new CarType();
            carType.setId(car.getCarType());
            CarType carType1 = carTypeMapper.selectOne(carType);
            car.setCarTypeName(carType1.getCarTypeName());

            LineType lineType = new LineType();
            lineType.setId(car.getLineType());
            LineType lineType1 = lineMapper.selectOne(lineType);
            car.setLineTypeName(lineType1.getLineName());

        }

        PageInfo<Car> pageInfo = new PageInfo<Car>(list);
        return new PageResult<>(pageInfo.getTotal(),(long)pageInfo.getPages(), list);

    }



  //导出Excel
    public Workbook exportCarExcel() {
        //需求:
        // 1.先查询数据库sys_user表里的内容;
        // 2.把sys_user表里的内容存放到excel文件中;
        // 2.1 创建excel文件
        //excel:公文包--->sheet文件1|文件2|文件3--->row行--->cell单元格--->给单元赋值
        // 2.2 再把sys_user表里的数据复制到excel中
        // 3.把excel文件下载下来.

        //1.创建公文包
        Workbook workbook = new HSSFWorkbook();
        //此时就有了一个空白的excel文件
        Sheet sheet = workbook.createSheet("车源信息表");

        //2.在sheet中创建行
        //2.1.创建第一行,作为表头
        Row firstRow = sheet.createRow(0);

        String[] titles = {"车辆类型", "货物类型", "线路类型", "价格", "开始地点", "结束地点"};
        for (int i = 0; i < titles.length; i++) {
            //2.2 创建第一行中的单元格
            Cell cell = firstRow.createCell(i);
            //2.3 给单元格赋值
            cell.setCellValue(titles[i]);
        }

        //2.4. 把数据库中的数据,复制到excel中.
        List<Map<String, Object>> cars = carMapper.exportCarInfo();
        String[] cols = {"carTypeId", "goodsTypeId", "lineType", "price", "startAddress", "endAddress"};

        //2.5. 创建excel第二行...
        for (int i = 0; i < cars.size(); i++) {
            //从第2行开始创建
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < titles.length; j++) {
                Cell cell = row.createCell(j);
                //Map===SysUser
                Map<String, Object> map = cars.get(i);
                Object value = map.get(cols[j]);
                cell.setCellValue(String.valueOf(value));
            }
        }

        return workbook;
    }


}
