package com.stu.springboot.mapper;

import com.stu.springboot.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

    List<Student> getAll();

    int updateByPrimaryKeySelective(Student student);
}