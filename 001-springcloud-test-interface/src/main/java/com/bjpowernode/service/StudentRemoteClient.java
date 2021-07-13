package com.bjpowernode.service;

import com.bjpowernode.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@FeignClient("001-springcloud-hystrix-provider")
@Component
public interface StudentRemoteClient {

    @RequestMapping("/select/{id}")
    public Student getStudent(@PathVariable("id") Integer id);

    @RequestMapping("/exception")
    public void getException();

    @RequestMapping("/outTime")
    public String testOutTime();
}
