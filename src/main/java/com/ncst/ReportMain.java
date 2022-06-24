package com.ncst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lsy
 * @date 2022/6/24
 */
@SpringBootApplication(scanBasePackages = "com.ncst")
public class ReportMain {

    public static void main(String[] args) {
        SpringApplication.run(ReportMain.class, args);
    }
}
