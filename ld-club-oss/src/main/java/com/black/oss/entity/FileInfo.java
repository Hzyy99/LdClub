package com.black.oss.entity;

import lombok.Data;

/**
 * 文件
 */
@Data
public class FileInfo {

    private String fileName;

    private Boolean directoryFlag;

    private String etag;


}
