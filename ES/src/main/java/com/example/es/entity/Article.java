package com.example.es.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
//indexName 指定索引名称
@Document(indexName = "article")
@Data
public class Article {
    @Id
    @Field(index = false, type = FieldType.Integer)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * index:是否设置分词  默认为true
     * analyzer：储存时使用的分词器
     * searchAnalyze:搜索时使用的分词器
     * store：是否存储  默认为false
     * type：数据类型  默认值是FieldType.Auto
     */
    @Field(analyzer = "standard", searchAnalyzer = "standard", store = true, type = FieldType.Text)
    private String title;
    @Field(analyzer = "standard", searchAnalyzer = "standard", store = true, type = FieldType.Text)
    private String context;
    @Field(store = true, type = FieldType.Integer)
    private Integer hits;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
}
