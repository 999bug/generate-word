package com.ncst.controller;

import com.ncst.model.GenerateReportDTO;
import com.ncst.service.IReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Lsy
 * @date 2022/6/9
 */
@RestController
@RequestMapping("/drm/report")
@Slf4j
public class ReportController {

    @Resource
    private IReportService iReportService;

    /**
     * 支持批量传入id，用英文逗号区分生成word报告
     * @param teamIds 球队IDid集合
     * @return 将生成的报告返回给前端
     */
    @PostMapping("download")
    public ResponseEntity<byte[]> downLoadReport(@RequestParam("teamIds") List<String> teamIds) {
        if (CollectionUtils.isEmpty(teamIds)) {
            return ResponseEntity.notFound().build();
        }
        ResponseEntity<byte[]> body;
        GenerateReportDTO generateWord = null;
        try {
            generateWord = iReportService.generateWord(teamIds);
            body = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + URLEncoder.encode(generateWord.getFileName(),
                            StandardCharsets.UTF_8.displayName()))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(generateWord.getWordReportFile().length())
                    .body(Files.readAllBytes(generateWord.getWordReportFile().toPath()));
            log.info("文件位置: {}", generateWord.getWordReportFile());
            log.info("文件夹所在位置: {}", generateWord.getTmpDirectory());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage().getBytes(StandardCharsets.UTF_8));
        } finally {
            // todo 测试时不删除报告，实际场景放开下面注释
//            if (generateWord != null) {
//                // 删除压缩包
//                FileUtils.deleteQuietly(generateWord.getWordReportFile());
//                try {
//                    // 删除临时文件夹
//                    FileUtils.deleteDirectory(generateWord.getTmpDirectory());
//                } catch (IOException e) {
//                   log.warn("删除临时文件失败 {}", generateWord.getTmpDirectory());
//                }
//            }
        }
        return body;
    }
}
