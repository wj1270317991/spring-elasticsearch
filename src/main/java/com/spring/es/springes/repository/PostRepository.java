package com.spring.es.springes.repository;

import com.spring.es.springes.domain.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * com.spring.es.springes.repository
 * <strong>描述：</strong>
 * <strong>功能：</strong>
 * <strong>使用场景：</strong>
 * <strong>注意事项：</strong>
 *
 * @author: wangjun
 * @date: 2019/1/2.
 */
public interface PostRepository extends ElasticsearchRepository<Post,String> {
}
