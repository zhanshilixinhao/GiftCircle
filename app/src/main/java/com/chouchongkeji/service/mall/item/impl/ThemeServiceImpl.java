package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.item.ThemeItemMapper;
import com.chouchongkeji.dial.dao.gift.item.ThemeMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.item.ThemeService;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.chouchongkeji.service.mall.item.vo.ThemeVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yy
 * @date 2018/6/20
 **/

@Service
public class ThemeServiceImpl implements ThemeService{
    @Autowired
    private ThemeItemMapper themeItemMapper;

    @Autowired
    private ThemeMapper themeMapper;

    /**
     * 获得好物主题列表
     *
     * @param: []
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @Override
    public Response getThemeList() {
        Integer status = 1;
        List<ThemeVo> themeVos = themeMapper.selectByStatus(status);
        return ResponseFactory.sucData(themeVos);
    }

    /**
     * 获得主题商品列表
     *
     * @param: [id 主题id, page 分页信息]
     * @return: com.chouchongkeji.goexplore.common.Response
     * @author: yy
     * @Date: 2018/6/20
     */
    @Override
    public Response getThemeItemList(Integer id, PageQuery page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ItemListVo> itemListVos = themeItemMapper.selectByThemeId(id);
        return ResponseFactory.sucData(itemListVos);
    }
}
