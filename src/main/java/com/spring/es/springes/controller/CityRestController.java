package com.spring.es.springes.controller;

import com.spring.es.springes.domain.City;
import com.spring.es.springes.domain.IntelligentStore;
import com.spring.es.springes.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.GeoPolygonQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
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


    @ApiOperation("距离查询")
    @GetMapping(value = "geoSearch")
    public Page<IntelligentStore> findAllByLocaltion(
            double lat, double lon, double distance, Pageable pageable) {
        //多边形范围
        List<GeoPoint> point1 = new ArrayList<>();
        List<GeoPoint> point2 = new ArrayList<>();

        GeoPolygonQueryBuilder polygonQueryBuilder =
                QueryBuilders.geoPolygonQuery("location",point1);


        GeoDistanceQueryBuilder builder =
                QueryBuilders.geoDistanceQuery("location")//查询字段
                        .point(lat, lon)//设置经纬度
                        .distance(distance, DistanceUnit.METERS)//设置距离和单位（米）
                        .geoDistance(GeoDistance.ARC);

        GeoDistanceSortBuilder sortBuilder =
                SortBuilders.geoDistanceSort("location",lat,lon)
                        .point(lat, lon)
                        .unit(DistanceUnit.METERS)
                        .order(SortOrder.ASC);//排序方式

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withFilter(polygonQueryBuilder)
                .withSort(sortBuilder);
        return null;
    }
}
