package com.ncst.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.Serializable;

/**
 * @author Lsy
 * @date 2022/6/23
 * @Description: 报告下载DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class GenerateReportDTO implements Serializable {

    private static final long serialVersionUID = 4546526385916380779L;

    public GenerateReportDTO(String fileName, File wordReportFile, File tmpDirectory) {
        this.fileName = fileName;
        this.wordReportFile = wordReportFile;
        this.tmpDirectory = tmpDirectory;
    }

    /**
     * 报告文件名称
     */
    String fileName;
    /**
     * 报告文件绝对路径
     */
    File wordReportFile;
    /**
     * 临时文件夹，存放所有报告的目录
     */
    File tmpDirectory;
}