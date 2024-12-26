package com.example.es.controller;


import com.example.es.dao.ArticleDao;
import com.example.es.entity.Article;
import com.example.es.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-12-04
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    ArticleDao articleESDao;
    // 创建文章
    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        boolean saved = articleService.save(article);
        articleESDao.save(article);
        if (saved) {
            return ResponseEntity.ok("Article created successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to create article");
        }
    }

    // 根据ID获取文章
    @GetMapping("/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {
        Article article = articleService.getById(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.badRequest().body(("Article not found"));
        }
    }

    // 更新文章
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setId(Math.toIntExact(id));  // 确保ID被更新
        boolean updated = articleService.updateById(article);
        if (updated) {
            return ResponseEntity.ok("Article updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update article");
        }
    }

    // 删除文章
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        boolean deleted = articleService.removeById(id);
        if (deleted) {
            return ResponseEntity.ok("Article deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete article");
        }
    }

    // 获取所有文章列表
    @GetMapping
    public ResponseEntity<?> getAllArticles() {
        List<Article> articles = articleService.list();
        return ResponseEntity.ok(articles);
    }

}
