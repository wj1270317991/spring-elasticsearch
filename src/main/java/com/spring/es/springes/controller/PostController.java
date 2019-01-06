package com.spring.es.springes.controller;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.domain.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.MultiMatchQueryBuilder.Type.BEST_FIELDS;
import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.*;

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


    @ApiOperation("单字段对某字符串模糊查询")
    @GetMapping("/singleMatch")
    public Object singleMatch(String content, Integer userId, @PageableDefault Pageable pageable) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("content", content))
                .withPageable(pageable).build();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("userId", userId)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }


    /**
     * 单字段对某短语进行匹配查询，短语分词的顺序会影响结果
     */
    @ApiOperation("单字段对某短语进行匹配查询，短语分词的顺序会影响结果")
    @GetMapping("/singlePhraseMatch")
    public Object singlePhraseMatch(String content, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchPhraseQuery("content", content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }



    @ApiOperation("matchPhraseQuery扩展测试数据")
    @GetMapping("/peradd")
    public Object add(){

        List<Post> list = new ArrayList<>();
        List<IndexQuery> queries = new ArrayList<>();

        Post post = new Post();
        post.setTitle("我是");
        post.setContent("我爱中华人民共和国");
        post.setWeight(1);
        post.setUserId(1);
        list.add(post);

        post = new Post();
        post.setTitle("我是");
        post.setContent("中华共和国");
        post.setWeight(2);
        post.setUserId(2);
        list.add(post);
        for (Post i : list){
            queries.add(new IndexQueryBuilder().withIndexName("projectname").withObject(i).build());
        }
        elasticsearchTemplate.bulkIndex(queries);
        return null;
    }



    @ApiOperation("多字段匹配")
    @GetMapping("/multiMatch")
    public Object singleUserId(String title, @PageableDefault(sort = "weight", direction = Sort.Direction.DESC) Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                //http://www.cnblogs.com/yjf512/p/4897294.html                                                          //默认为OR
                .withQuery(multiMatchQuery(title, "title", "content").type(BEST_FIELDS).tieBreaker(0.3f).operator(AND))
                .withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }



    @ApiOperation("删除一个插入的对象")
    @GetMapping("/delItem")
    public Object delItem() {
        String info = elasticsearchTemplate.delete(Post.class, "Ed1RGGgBxTiHuvFMOhlv");
        return info;
    }



    @ApiOperation("删除一个索引")
    @GetMapping("/delIndex")
    public Object del(String indexName) {
        boolean b = elasticsearchTemplate.deleteIndex(indexName);
        return b;
    }
}
