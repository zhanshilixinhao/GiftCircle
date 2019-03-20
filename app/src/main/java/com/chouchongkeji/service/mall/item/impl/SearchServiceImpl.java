package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.article.ArticleMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoAffairMapper;
import com.chouchongkeji.dial.dao.user.memo.MemoFestivalMapper;
import com.chouchongkeji.dial.pojo.gift.article.Article;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.mall.item.SearchService;
import com.chouchongkeji.service.mall.item.vo.ItemArticleListVo;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.chouchongkeji.service.user.memo.vo.MemoItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linqin
 * @date 2019/3/20 10:18
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private MemoAffairMapper memoAffairMapper;

    @Autowired
    private MemoFestivalMapper memoFestivalMapper;



    /**
     * 商品文章事件搜索
     *
     * @param keyword 关键字
     * @return
     * @author: linqin
     * @Date: 2018/7/6
     */
    @Override
    public Response searchItemArticle(Integer userId, String keyword,Integer type) {
        List<ItemArticleListVo> listVo = new ArrayList<>();
        ItemArticleListVo vo ;
        if (type == 1){
            // 查询商品
            List<ItemListVo> items = itemMapper.selectItemList(keyword);
            if (!CollectionUtils.isEmpty(items)){
                for (ItemListVo item : items) {
                    vo = new ItemArticleListVo();
                    vo.setId(item.getItemId());
                    vo.setTitle(item.getTitle());
                    vo.setCover(item.getCover());
                    vo.setPrice(item.getPrice());
                    vo.setType(1);
                    listVo.add(vo);
                }
            }
            return ResponseFactory.sucData(listVo);
        }else if (type == 2){
            // 根据关键字查询文章
            List<Article> articles = articleMapper.selectArticleList(keyword);
            if (!CollectionUtils.isEmpty(articles)){
                for (Article article : articles) {
                    vo = new ItemArticleListVo();
                    vo.setId(article.getId());
                    vo.setTitle(article.getTitle());
                    vo.setCover(article.getCover());
                    vo.setSummary(article.getSummary());
                    vo.setType(2);
                    listVo.add(vo);
                }
            }
            return ResponseFactory.sucData(listVo);
        }else {
            // 根据关键字查询事件
            List<MemoItemVo> list = memoAffairMapper.selectAllByUserIdKeyword(userId,keyword);
            // 节日事件
            List<MemoItemVo> memos = memoFestivalMapper.selectAllByKeyword(keyword);
            if (!CollectionUtils.isEmpty(memos)) {
                list.addAll(memos);
            }
            return ResponseFactory.sucData(list);
        }

    }


}
