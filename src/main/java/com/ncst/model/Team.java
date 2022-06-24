package com.ncst.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Lsy
 * @date 2022/6/22
 * @Description: 报告下载实体类
 */
@Getter
@Setter
public class Team implements Serializable {

    private static final long serialVersionUID = -1333014688884492136L;

    private String teamName;
    private Integer num;
    private String pg;
    private String sg;
    private String sf;
    private String pf;
    private String c;

    // 板凳球员
    private String bench;

}
