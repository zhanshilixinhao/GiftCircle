package com.chouchongkeji.service.file.impl;


import com.chouchongkeji.dial.dao.user.AppVersionMapper;
import com.chouchongkeji.dial.pojo.user.AppVersion;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.file.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2019/4/15
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {


    @Autowired
    private AppVersionMapper appVersionMapper;


    /**
     * 版本更新
     *
     * @param versionCode
     * @return
     * @author linqin
     * @date 2019/4/15
     */
    @Override
    public Response appVersion(Integer versionCode) {
        AppVersion appVersion = appVersionMapper.selectByVersionCode(versionCode);
        return ResponseFactory.sucData(appVersion);
    }


}
