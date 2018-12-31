
package com.spring.es.springes.service;


import com.spring.es.springes.domain.City;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CityService {

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * 根据关键词，function score query 权重分分页查询
     */
    List<City> searchAllCity();


    /**
     * 根据条件查询信息
     * @param name
     * @return
     */
    Page<City> searchByQuery(String name);


    /**
     * 复杂查询
     * @param name
     * @return
     */
    Page<City> searchComplexQuery(String name);
}