package com.bjpowernode.service.impl;

import com.bjpowernode.entity.Student;
import com.bjpowernode.dao.StudentMapper;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudent(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
