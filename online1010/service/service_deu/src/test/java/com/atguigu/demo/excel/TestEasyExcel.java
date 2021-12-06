package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
      /*  //实现excel写
        //1 设置写入文件夹地址和名称
        String name = "D:\\write.xlsx";
        //参数:文件路径 实体类class
        EasyExcel.write(name,DemoData.class).sheet("学生列表").doWrite(getData());*/

        //实现excel的读
        String name = "D:\\write.xlsx";
        EasyExcel.read(name, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    public static List getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            DemoData u = new DemoData();
            u.setSname("学生" + i);
            u.setSno(i);
            list.add(u);
        }
        return list;
    }
}
