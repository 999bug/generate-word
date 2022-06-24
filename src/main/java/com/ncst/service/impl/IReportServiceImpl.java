package com.ncst.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import com.ncst.model.*;
import com.ncst.service.IReportService;
import com.ncst.util.I2DrmWordUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author lisy
 */
@Service("reportService")
public class IReportServiceImpl implements IReportService {


    @Resource
    private ReportConfig reportConfig;


    @Override
    public GenerateReportDTO generateWord(List<String> teamIds) throws Exception {
        // 临时目录存放所有的文档
        File tmpDirectory = new File(FileUtils.getTempDirectory(), IdUtil.fastSimpleUUID());
        if (!tmpDirectory.mkdir()) {
            throw new IOException("Can't create directory " + tmpDirectory.getPath());
        }
        // todo 根据实际开发需要这块可以遍历 teamIds
        Map<String, File> resultMap = new HashMap<>();
        Map<String, Object> map = generateMap();
        String reportName = "下载报告.doc";
        File wordReportFile = new File(tmpDirectory + File.separator + reportName);
        I2DrmWordUtil.generateWord(map, wordReportFile, new File(reportConfig.getTmplPath()), reportConfig.getAutoFlowTmpl());
        resultMap.put(reportName, wordReportFile);
        return zipFile(tmpDirectory, resultMap);
    }


    /**
     * 多个文件使用压缩，一个文件直接返回
     *
     * @param tmpDirectory 临时文件夹
     * @param resultMap    key:文件名，value:文件绝对路径
     * @return 是否需要压缩文件
     */
    private GenerateReportDTO zipFile(File tmpDirectory, Map<String, File> resultMap) {
        String fileName = null;
        File wordReportFile = null;
        if (resultMap.size() > 1) {
            fileName = "八年四班学生信息" + DateFormatUtils.format(new Date(), "yyyy_MMdd_HHmm") + ".zip";
            wordReportFile = ZipUtil.zip(tmpDirectory);
        } else {
            for (Map.Entry<String, File> entry : resultMap.entrySet()) {
                fileName = entry.getKey();
                wordReportFile = entry.getValue();
            }
        }
        return new GenerateReportDTO(fileName, wordReportFile, tmpDirectory);
    }

    /**
     * @return 像word模板 map 对象中填充信息
     */
    private Map<String, Object> generateMap() throws Exception {
        Map<String, Object> map = new HashMap<>();

        Team team = new Team();
        team.setTeamName("金州勇士");
        team.setNum(15);
        team.setC("鲁尼");
        team.setPf("格林");
        team.setSg("克莱");
        team.setPg("库里");
        team.setSf("嘴哥");
        team.setBench("妹夫、大聪明");
        map.put("team", team);

        map.put("images", getFlowImage());
        map.put("careers", getCareers());
        return map;
    }

    private List<Career> getCareers() {
        List<Career> careers = new ArrayList<>();
        careers.add(new Career("库里", "34", "35%", "4", "2", "8"));
        careers.add(new Career("克莱", "30", "30%", "3", "4", "9"));
        careers.add(new Career("格林", "16", "12%", "15", "6", "14"));
        careers.add(new Career("嘴哥", "18", "26%", "14", "3", "10"));
        careers.add(new Career("鲁尼", "10", "20%", "16", "7", "16"));
        return careers;
    }

    private List<ImageDTO> getFlowImage() throws Exception {
        List<ImageDTO> list = new ArrayList<>();
        int i = 1;
        list.add(getItem(i++, "库里", "C:\\Users\\SERVER\\OneDrive\\桌面\\nba\\curry.png"));
        list.add(getItem(i++, "克莱", "C:\\Users\\SERVER\\OneDrive\\桌面\\nba\\kaly.png"));
        list.add(getItem(i++, "格林", "C:\\Users\\SERVER\\OneDrive\\桌面\\nba\\green.png"));
        list.add(getItem(i++,"嘴哥", "C:\\Users\\SERVER\\OneDrive\\桌面\\nba\\kiss.png"));
        list.add(getItem(i, "鲁尼", "C:\\Users\\SERVER\\OneDrive\\桌面\\nba\\luni.png"));
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list;
    }

    private ImageDTO getItem(Integer id, String name, String imagePath) throws Exception{
        final ImageDTO image = new ImageDTO(id, name, I2DrmWordUtil.getImageBase64String(imagePath));
        final Map<String, Integer> map = I2DrmWordUtil.getHeightAndWidth(image.getPicture());
        image.setHeight(map.get("height"));
        image.setWidth(map.get("width"));
        return image;
    }
}
