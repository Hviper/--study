package com.example.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.es.entity.Article;
import com.example.es.mapper.ArticleMapper;
import com.example.es.service.IArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-12-04
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Transactional
    public void batchInsertArticles() {
        List<Article> articles = new ArrayList<>(10000);  // 用于存储批量插入的文章

        // 生成10000条数据
        for (int i = 10000; i <= 20000; i++) {
            Article article = new Article();
            article.setTitle("Title " + i);
            article.setContext("Context of article " + i);
            article.setHits(i);
            articles.add(article);
        }

        // 批量插入
        this.saveBatch(articles);
    }
}
