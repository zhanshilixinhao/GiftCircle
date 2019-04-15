package com.chouchongkeji.service.file;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2019/4/15
 */

public interface AppVersionService {

    /**
     * 版本更新
     *
     * @param versionCode
     * @return
     * @author linqin
     * @date 2019/4/15
     */
    Response appVersion(Integer versionCode);
}
