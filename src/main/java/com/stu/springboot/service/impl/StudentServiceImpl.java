package com.stu.springboot.service.impl;

import com.stu.springboot.mapper.StudentMapper;
import com.stu.springboot.pojo.Student;
import com.stu.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    /**注入一个springboot自动配置好的RedisTemplate对象来对redis进行操作**/
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StudentMapper studentMapper;

    /**
     *
     * @返回Student表所有行
     */
    @Override
    public List<Student> getAllStudent() {
        //更改key的序列方式使redis缓存key值易读，取key的时候也易取
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        /**先到redis缓存中查找
         * 若是在高并发条件下此处有点问题（缓存穿透）
         * 使用线程同步锁解决高并发条件下的缓存穿透问题**/

        List<Student> students = (List<Student>) redisTemplate.opsForValue().get("allstudents");
        //双重检测锁
        if (students == null){
            synchronized (this){
                students = (List<Student>) redisTemplate.opsForValue().get("allstudents");
                //缓存中若没有则去数据库查找
                if(null == students){
                    System.out.println("查询的是数据库+++++++++");
                    students = studentMapper.getAll();
                    redisTemplate.opsForValue().set("allstudents",students);
                }else {
                    System.out.println("查询的是缓存++++++");
                }

            }
        }else {
            System.out.println("查询的是缓存++++++");
        }

        return students;
    }

    /**
     *
     * @返回更新记录的条数
     */
    @Override
    public int upDate(Student student) {
        int update = studentMapper.updateByPrimaryKeySelective(student);
        System.out.println("更新结果是："+update);
        return update;
    }
}
