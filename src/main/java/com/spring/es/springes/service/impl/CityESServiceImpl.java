package com.spring.es.springes.service.impl;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.repository.CityRepository;
import com.spring.es.springes.service.CityService;
import org.elasticsearch.index.query.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityESServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

    @Autowired
    CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {

        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    @Override
    public List<City> searchAllCity() {
        List<City> cities = new ArrayList<>();
        cityRepository.findAll().forEach(e -> cities.add(e));
        return cities;
    }

    @Override
    public Page<City> searchByQuery(String name) {
        Pageable of = PageRequest.of(2, 1);
        List<City> cities = new ArrayList<>();
        Page<City> byCityname = cityRepository.findByCityname(name, of);
        return byCityname;
    }


    /**
     * QueryBuilders.matchPhrasePrefixQuery 这是完全匹配的。
     * ex: 北京  北 可以 京 可以 北京 可以  但是 北京人不可以
     * @param name
     * @return
     */
    @Override
    public Page<City> searchComplexQuery(String name) {
        MatchPhrasePrefixQueryBuilder cityname = QueryBuilders.matchPhrasePrefixQuery("cityname", name);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(cityname)
                .withIndices("cityindex")
                .build();
        Page<City> search = cityRepository.search(build);
        return search;
    }
}
