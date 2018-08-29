package com.moxi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传接口
 * Created by yangfeng on 20/08/2018.
 */
public interface IKnowledgeService {
    /**
     * 导入数据
     * @param fileName
     * @param mfile
     * @param
     * @return
     */

    String batchImport(String fileName, MultipartFile mfile, HttpServletRequest request);

}
