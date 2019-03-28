package com.stu.springboot.controller;

import com.stu.springboot.pojo.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestFullController {
    @GetMapping("/boot/user/{id}")
    public Object user(@PathVariable("id") Integer id){
        Student student = new Student();
        student.setStuid(id);
        student.setStuname("张三丰");
        student.setStusex(0);

        return  student;
    }
}
