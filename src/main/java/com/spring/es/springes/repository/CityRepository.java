package com.spring.es.springes.repository;


import com.spring.es.springes.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long> {

    Page<City> findByCityname(String name, Pageable page);
}
