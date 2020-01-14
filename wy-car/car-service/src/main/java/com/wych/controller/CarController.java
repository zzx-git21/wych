package com.wych.controller;

import com.wych.pojo.Car;
import com.wych.pojo.PageResult;
import com.wych.pojo.SearchDTO;
import com.wych.service.CarService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController

public class CarController {
  @Autowired
    private CarService service;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
   /*发布车源*/
    @GetMapping("/addcar")
    public ResponseEntity<Void> saveCar( Car car){

                service.saveCar(car);
                return ResponseEntity.ok(null);
    }
   //找车

    //分页
    @GetMapping("/pageCar")
    public ResponseEntity<PageResult<Car>> queryCarList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                        @RequestParam(value = "rows",defaultValue = "5")Integer rows,
                                                        SearchDTO searchDTO){
        PageResult<Car> result = service.queryCarList(searchDTO,page,rows);
        return ResponseEntity.ok(result);

    }
    @GetMapping("/selectCar")
    public ResponseEntity<PageResult<Car>> selectCarList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                                        @RequestParam(value = "rows",defaultValue = "5")Integer rows,
                                                        SearchDTO searchDTO){
        PageResult<Car> result = service.selectCarList(searchDTO,page,rows);
        return ResponseEntity.ok(result);

    }


   //导出文件
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response){
        //Workbook:只是内存中的一个对象,并不是一个真实的excle文件
        Workbook workbook = service.exportCarExcel();
        try {
            response.setContentType("application/octet-stream");
            //以附件形式进行下载--->把服务器里的某个文件,输出到浏览器客户端.
            String fileName = "员工信息.xls";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置以附件形式下载的响应头
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            //生成一个真实的excel文件.
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

