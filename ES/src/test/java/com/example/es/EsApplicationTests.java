package com.example.es;

import com.example.es.dao.ArticleDao;
import com.example.es.entity.Article;
import com.example.es.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EsApplicationTests {

	@Autowired
	private ElasticsearchRestTemplate template;

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	ArticleServiceImpl articleService;
	@Test
	void contextLoads() {
		articleService.batchInsertArticles();
	}

	//通过springboot es向elasticsearch数据库储存一条数据
	@Test
	public void testSave() {
		//创建文档
		Article article = new Article();
		article.setId(1);
		article.setTitle("es搜索");
		article.setContext("成功了吗");
		//保存文档
		articleDao.save(article);
	}

	//修改
	@Test
	public void testUpdate() {
		//判断数据库中是否有你指定的id的文档，如果没有。就进行保存，如果有，就进行更新
		//创建文档
		Article article = new Article();
		article.setId(1);
		article.setTitle("es搜索1");
		article.setContext("成功了吗1");
		//保存文档
		articleDao.save(article);
	}

	//删除
	@Test
	public void testDelete() {
//        根据主键删除
		articleDao.deleteById(1);
	}


	//重新构建数据
	@Test
	public void makeData(){
		for (int i = 1; i <= 10; i++) {
			//创建文档
			Article article = new Article();
			article.setId(i);
			article.setTitle("es搜索"+i);
			article.setContext("成功了吗"+i);
			article.setHits(100+i);
			//保存数据
			articleDao.save(article);
		}
	}

	//查询所有
	@Test
	public void findAll(){
		Iterable<Article> all = articleDao.findAll();
		for (Article article : all) {
			System.out.println(article);
		}
	}

	//主键查询
	@Test
	public void testFindById(){
		Optional<Article> id = articleDao.findById(1);
		System.out.println(id.get());
	}

	//分页查询
	@Test
	public void testFindAllWithPage(){
		//设置分页条件
		//page代表页码，从0开始
		PageRequest pageRequest = PageRequest.of(1, 3);

		Page<Article> all = articleDao.findAll(pageRequest);
		for (Article article : all) {
			System.out.println(article);
		}
	}

	//排序查询
	@Test
	public void testFindWithSort(){
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("hits"));
		Iterable<Article> all = articleDao.findAll(sort);
		for (Article article : all) {
			System.out.println(article);
		}
	}

	//分页加排序查询
	@Test
	public void testFindAllWithPageAndSort(){
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("hits"));
		//设置分页条件
		PageRequest pageable = PageRequest.of(1, 3, sort);

		Page<Article> page = articleDao.findAll(pageable);

		for (Article article : page.getContent()) {
			System.out.println(article);
		}
	}


	//根据标题查询
	@Test
	public void testFindByTitle(){
		List<Article> es = articleDao.findByTitle("es");
		for (Article e : es) {
			System.out.println(e);
		}
	}

	//根据标题或内容查询
	@Test
	public void testFindByTitleOrContext(){
		List<Article> es = articleDao.findByTitleOrContext("es", "1");
		for (Article e : es) {
			System.out.println(e);
		}

	}

	//根据标题和内容查询（含分页）
	@Test
	public void testFindByTitleOrContextWithPage(){
		//设置排序条件
		Sort sort = Sort.by(Sort.Order.desc("hits"));

		//设置分页条件
		PageRequest pageRequest = PageRequest.of(1, 3, sort);

		List<Article> es = articleDao.findByTitleOrContext("es", "1", pageRequest);
		for (Article e : es) {
			System.out.println(e);
		}
	}

}
