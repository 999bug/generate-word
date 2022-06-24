package com.ncst.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Etcd相关配置
 */
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "report")
@Getter
@Setter
public class ReportConfig {
    /**
     * 模板文件存放目录
     */
    private String tmplPath;
    /**
     * 模板文件名
     */
    private String autoFlowTmpl;

}
