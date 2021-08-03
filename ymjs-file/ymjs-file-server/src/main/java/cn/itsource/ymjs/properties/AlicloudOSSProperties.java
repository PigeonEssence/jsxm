package cn.itsource.ymjs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file.alicloud")
public class AlicloudOSSProperties {
    private String BucketName ;
    private String AccessKey ;
    private String SecretKey;
    private String Endpoint;
}
