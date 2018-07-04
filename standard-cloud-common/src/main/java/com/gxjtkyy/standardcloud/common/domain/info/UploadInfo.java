package com.gxjtkyy.standardcloud.common.domain.info;

import lombok.Data;

/**
 * 文件上传信息
 * @Package com.gxjtkyy.standardcloud.common.domain.info
 * @Author lizhenhua
 * @Date 2018/6/29 14:52
 */
@Data
public class UploadInfo {

    /**原名*/
    private String originalName;

    /**扩展名*/
    private String ext;

    /**文件名*/
    private String fileName;

    /**保存路径*/
    private String savePath;

}
