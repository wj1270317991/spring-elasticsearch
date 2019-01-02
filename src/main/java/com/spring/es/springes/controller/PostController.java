package com.spring.es.springes.controller;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.domain.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * com.spring.es.springes.controller
 * <strong>描述：</strong>
 * <strong>功能：</strong>
 * <strong>使用场景：</strong>
 * <strong>注意事项：</strong>
 *
 * @author: wangjun
 * @date: 2019/1/2.
 */
@RestController
@Api("elasticSearch")
public class PostController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @ApiOperation("查询")
    @GetMapping("/singleWord")
    public Object singleTitle(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }

    @ApiOperation("查询,排序")
    @GetMapping("/sort")
    public Object sort(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(word))
                .withSort(SortBuilders.fieldSort("userId").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("weight").order(SortOrder.DESC))
                .withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }




}
