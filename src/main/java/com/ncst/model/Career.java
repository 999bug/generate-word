package com.ncst.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lsy
 * @date 2022/6/22
 * @Description: 任务执行详细记录
 */
@Getter
@Setter
@NoArgsConstructor
public class Career implements Serializable {

    private static final long serialVersionUID = -3276756886298012383L;

    public Career(String name, String score, String trisection, String backboard, String block, String assist) {
        this.name = name;
        this.score = score;
        this.trisection = trisection;
        this.backboard = backboard;
        this.block = block;
        this.assist = assist;
    }

    private String name;
    private String score;
    private String trisection;
    private String backboard;
    private String block;
    private String assist;

}
