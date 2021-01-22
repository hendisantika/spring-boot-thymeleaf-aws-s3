package com.hendisantika.springbootthymeleafawss3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-thymeleaf-aws-s3
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/01/21
 * Time: 05.56
 */
@Controller
public class GreetingController {
    // @Value("#{environment.accesskey}")
    @Value("${accessKey}")
    String accesskey;

    @Value("${secretKey}")
    String secretkey;

    @Value("${bucketName}")
    String bucketName;
}
