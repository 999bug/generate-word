package com.ncst.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Lsy
 * @date 2022/6/23
 * @Description: 各阶段流程图
 */
@Getter
@Setter
@NoArgsConstructor
public class ImageDTO implements Serializable {

    private static final long serialVersionUID = 3298645305630928744L;

    public ImageDTO(Integer id, String picName, String picture) {
        this.id = id;
        this.picName = picName;
        this.picture = picture;

    }

    /**
     * 图片ID 需要不同
     */
    private int id;
    /**
     * 图片名字
     */
    private String picName;
    /**
     * 图片base64
     */
    private String picture;

    private int width;
    private int height;

}
