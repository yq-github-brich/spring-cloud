package com.bjpowernode.web.controller;

import com.bjpowernode.entity.Student;
import com.bjpowernode.service.StudentRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/feign")
public class StudentController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${change.set}")
    private String change;

    @Autowired
    private StudentRemoteClient studentRemoteClient;

    @HystrixCommand(fallbackMethod = "fallback1")
    @RequestMapping("/get/{id}")
    public Student getStudent(@PathVariable("id") Integer id){
        System.out.println(name);
        System.out.println(change);
        return studentRemoteClient.getStudent(id);
    }

    public Student fallback1(Integer id,Throwable throwable){
        Student student = new Student();
        student.setName(throwable.getMessage());
        return student;
    }


    @HystrixCommand(fallbackMethod = "fallback2")
    @RequestMapping("/exception")
    public String getException(){
        studentRemoteClient.getException();
        return "未抛出异常";
    }
    public String fallback2(Throwable throwable){
        return "服务降级2--------->"+throwable.getMessage();
    }

    @HystrixCommand(fallbackMethod = "fallback3")
    @RequestMapping("/timeout")
    public String testOutTime(){
        String s = studentRemoteClient.testOutTime();
        return s;
    }
    public String fallback3(Throwable throwable){
        return "服务降级3--------->"+throwable.getMessage();
    }

    @RequestMapping("/error")
    public String error(){
        int a=1,b=0;
        System.out.println(a/b);
        return "error";
    }
}
