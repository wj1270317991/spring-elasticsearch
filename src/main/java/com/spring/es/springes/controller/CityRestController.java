package com.spring.es.springes.controller;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


/**
 * Created by wangjun on 2018/12/27.
 */

@EnableSwagger2
@RestController
@Api("swaggerDemoController相关的api")
public class CityRestController {

    @Autowired
    private CityService cityService;


    @ApiOperation("添加城市")
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @ApiOperation("查询城市")
    @RequestMapping(value = "/api/allcity/search", method = RequestMethod.GET)
    public List<City> searchCity() {
        return cityService.searchAllCity();
    }

    @ApiOperation("关键字查询")
    @GetMapping(value = "/api/city/search")
    public Page<City> searchCityByQuery(@RequestParam String name){
        Page<City> cities = cityService.searchByQuery(name);
        return cities;
    }

    @ApiOperation("复杂查询")
    @GetMapping(value = "/api/complex/search")
    public Page<City> complexSearch(@RequestParam String name){
        Page<City> cities = cityService.searchComplexQuery(name);
        return cities;
    }
}
