package com.stu.springboot.service;

import com.stu.springboot.pojo.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudent();

    public int upDate(Student student);
}
