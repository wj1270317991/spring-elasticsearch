package com.spring.es.springes.service.impl;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.repository.CityRepository;
import com.spring.es.springes.service.CityService;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
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


    @Override
    public Page<City> searchComplexQuery(String name) {

//        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery().build();
//        cityRepository.search()
        return null;
    }
}
