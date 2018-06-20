package com.chouchongkeji.service.file;

import com.chouchongkeji.goexplore.common.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * @author yichenshanren
 * @date 2018/6/20
 */

public interface FileService {

    /**
     * 上传图片 base64 方式
     *
     * @param base 图片base64编码后的字符串
     * @return
     */
    Response uploadBase64(String base, Integer module);

    Response uploadMulti(ArrayList<MultipartFile> files, Integer module);

}
