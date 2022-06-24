package com.ncst.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lsy
 * @date 2022/6/22
 */
@Slf4j
public class I2DrmWordUtil {

    private I2DrmWordUtil() {}

    /**
     * 使用FreeMarker自动生成Word文档
     *
     * @param dataMap      生成Word文档所需要的数据
     * @param outPutPath   本地输出路径
     * @param templateDir  模板存放文件夹
     * @param templateName 模板名字
     */
    public static void generateWord(Map<String, Object> dataMap, File outPutPath, File templateDir, String templateName) throws IOException {
        final Template template = getTemplate(templateDir, templateName);
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutPath.getAbsolutePath()), StandardCharsets.UTF_8))) {
            //FreeMarker使用Word模板和数据生成Word文档
            template.process(dataMap, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException("报告生成失败!");
        }
    }

    /**
     * @return 模板对象
     */
    private static Template getTemplate(File templateDir, String templateName) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        // 设置模板所在路径
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 设置FreeMarker生成Word文档所需要的模板
        return configuration.getTemplate(templateName, "UTF-8");
    }

    /**
     * 将图片内容转换成Base64编码的字符串
     * @param imageFile 图片文件的全路径名称
     * @return 转换成Base64编码的图片内容字符串
     */
    public static String getImageBase64String(String imageFile) throws IOException{
        if (StringUtils.isBlank(imageFile)) {
            throw new IOException("image is empty");
        }
        File file = new File(imageFile);
        if (!file.exists()) {
            throw new IOException(file + "not found");
        }

        byte[] data;
        try (InputStream is = new FileInputStream(file)) {
            data = new byte[is.available()];
            is.read(data);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return new BASE64Encoder().encode(data);
    }

    /**
     * 从base64文件流中获取图片属性
     * @param data  base64文件流
     */
    public static Map<String, Integer> getHeightAndWidth(String data) {
        Map<String, Integer> map=new HashMap<>();
        try {
            byte[] strBase64 = new BASE64Decoder().decodeBuffer(data);
            InputStream is = new ByteArrayInputStream(strBase64);
            BufferedImage image = ImageIO.read(is);
            map.put("width",image.getWidth());
            map.put("height",image.getHeight());
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }


}
