package com.chouchongkeji.mvc.controller.file;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.file.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2019/4/15
 */
@RestController
@RequestMapping("noauth/version")
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    /**
     * 版本更新
     *
     * @return
     * @author linqin
     * @date 2019/4/15
     */
    @PostMapping("app")
    public Response appVersion() {
        return appVersionService.appVersion();
    }

}
