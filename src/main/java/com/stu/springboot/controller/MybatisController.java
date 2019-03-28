package com.stu.springboot.controller;

import com.stu.springboot.pojo.Student;
import com.stu.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MybatisController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/boot/students")
    public Object students(){
        /**
         *      //高并发缓存穿透测试（多线程）
         *         Runnable runnable = new Runnable() {
         *             @Override
         *             public void run() {
         *                 studentService.getAllStudent();
         *             }
         *         };
         *         //使用Executors线程池创建多个线程
         *         ExecutorService executorService = Executors.newFixedThreadPool(25);
         *         for (int i=0;i<10000;i++){
         *             executorService.submit(runnable);
         *         }
          */


        return studentService.getAllStudent();
    }

    @Transactional  //标示此方法使用到了事务控制
    @GetMapping("/boot/update")
    public int update(){
        Student student = new Student();
        student.setStuid(1);
        student.setStuname("xxx");
        student.setStusex(0);

        int update = studentService.upDate(student);
        return update;
    }
}
