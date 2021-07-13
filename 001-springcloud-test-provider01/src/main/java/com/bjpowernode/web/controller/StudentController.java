package com.bjpowernode.web.controller;


import com.bjpowernode.entity.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/select/{id}")
    public Student getStudent(@PathVariable("id") Integer id){
        System.out.println("均衡负载---->001");
        return studentService.getStudent(id);
    }

    @RequestMapping("/exception")
    public void getException(){
        System.out.println("均衡负载---->001");
        if (true){
            throw new RuntimeException("运行时异常");
        }
    }

    @RequestMapping("/outTime")
    public String testOutTime(){
        System.out.println("均衡负载---->001");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "未超时";
    }

}
