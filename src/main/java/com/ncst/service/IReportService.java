package com.ncst.service;

import com.ncst.model.GenerateReportDTO;

import java.util.List;

/**
 * @author lisy
 */
public interface IReportService {

    /**
     * @param teamIds 切换ID集合
     * @return 根据切换ID集合批量生成报告
     */
    GenerateReportDTO generateWord(List<String> teamIds) throws Exception;

}
