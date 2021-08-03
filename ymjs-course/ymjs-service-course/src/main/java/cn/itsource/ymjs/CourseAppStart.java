package cn.itsource.ymjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//服务注册与发现
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.itsource.ymjs.api")
public class CourseAppStart {
    public static void main(String[] args) {
        SpringApplication.run(CourseAppStart.class);
    }
}
