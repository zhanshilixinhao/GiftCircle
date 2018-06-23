package com.chouchongkeji.service.file.impl;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.properties.ServiceProperties;
import com.chouchongkeji.service.file.FileService;
import com.chouchongkeji.service.file.ImageVo;
import com.chouchongkeji.util.ImageUploadUtil;
import com.chouchongkeji.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yichenshanren
 * @date 2018/6/20
 */

@Service
public class FileServiceImpl implements FileService {

    /**
     * @param base 图片base64编码后的字符串
     * @return
     */
    @Override
    public Response uploadBase64(String base, Integer module) {
        String mstr = getModule(module);
        try {
            String uri = ImageUtils.base64ToImage(base, mstr, "jpg");
            ImageVo vo = new ImageVo(uri, uri);
            return ResponseFactory.sucData(vo);
        } catch (Throwable e) {
            e.printStackTrace();
            return ResponseFactory.err("图片上传失败," + e.getMessage());
        }
    }

    private String getModule(Integer module) {
        String mstr = "other";
        if (module == 1) {
            mstr = "order/comment";
        } else if (module == 2) {
            mstr = "order/refund";
        }
        return mstr;
    }

    /**
     *
     * @param files
     * @param module
     * @return
     */
    @Override
    public Response uploadMulti(ArrayList<MultipartFile> files, Integer module) {
        String mstr = getModule(module);
        try {
            List<String> list = ImageUploadUtil.multiUpload(files, mstr);
            return ResponseFactory.sucData(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseFactory.err("上传失败");
    }


}
