package com.chouchongkeji.mvc.controller.file;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.file.FileService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yichenshanren
 * @date 2018/6/20
 */

@RestController
@RequestMapping("noauth/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传图片 base64 方式
     *
     * @param base64 base64
     * @return
     * @author yichen
     */
    @RequestMapping("upload")
    @ResponseBody
    private Response upload(String base64, Integer module) {
        // 参数不能为空
        if (StringUtils.isBlank(base64) || module == null) {
            return ResponseFactory.errMissingParameter();
        }
        // 检查用户token
//        Response response = userService.checkToken(token);
//        if (response.isSuccessful()) {
        return fileService.uploadBase64(base64, module);
//        }
//        return response;
    }

    /**
     * 上传图片 base64 方式
     *
     * @return
     * @author yichen
     */
    @RequestMapping("upload/multi")
    @ResponseBody
    private Response uploadMt(Integer module, HttpServletRequest request) {
        ArrayList<MultipartFile> files = null;
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            files = new ArrayList<>();
            while (iter.hasNext()) {
                files.add(multiRequest.getFile(iter.next()));
            }
        }
        // 参数不能为空
        if (module == null ||
                CollectionUtils.isEmpty(files)) {
            return ResponseFactory.errMissingParameter();
        }

        // 检查用户token
//        Response response = userService.checkToken(token);
//        if (response.isSuccessful()) {
        return fileService.uploadMulti(files, module);
//        }
//        return response;
    }
}
