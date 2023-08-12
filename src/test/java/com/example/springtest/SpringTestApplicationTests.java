package com.example.springtest;

import com.example.springtest.business.abstracts.ICityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTestApplicationTests {
    @Autowired
    ICityService cittyService;
    @Test
    void contextLoads() {
        cittyService.testServiceMethod();
    }

}
