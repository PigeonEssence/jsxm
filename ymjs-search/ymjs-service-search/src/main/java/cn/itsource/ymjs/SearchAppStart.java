package cn.itsource.ymjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//服务注册与发现
@SpringBootApplication
@EnableDiscoveryClient
public class SearchAppStart {
    public static void main(String[] args) {
        SpringApplication.run( SearchAppStart.class);
    }
}
