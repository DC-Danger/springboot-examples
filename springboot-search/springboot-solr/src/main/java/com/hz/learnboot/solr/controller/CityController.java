package com.hz.learnboot.solr.controller;

import com.hz.learnboot.solr.domain.City;
import com.hz.learnboot.solr.service.CityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * CityController
 *
 * @Author hezhao
 * @Time 2018-07-22 22:45
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    // 查询所有
    @GetMapping
    @CrossOrigin
    public Object list(){
        return cityService.findAll();
    }

    // 根据名称查询
    @GetMapping(value = "/selectByName/{cityName}")
    @CrossOrigin
    public Object selectObjectByName(@PathVariable("cityName") String cityName){
        return cityService.findByCityName(cityName);
    }

    // 根据名称查询，并且分页
    @GetMapping(value = "/select")
    @CrossOrigin
    public Object selectObject(HttpServletRequest request){
        try {
            return cityService.query(request.getParameter("description"), getPageRequest(request));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 新增
    @PostMapping(value = "/save")
    public Object save(City city){
        cityService.save(city);
        return "success";
    }

    /**
     * 获取分页请求
     */
    private PageRequest getPageRequest(HttpServletRequest request){
        int page = 1;
        int size = 10;
        Sort sort = null;
        try {
            String sortName = request.getParameter("sortName");
            String sortOrder = request.getParameter("sortOrder");
            if(StringUtils.isNotBlank(sortName) ){
                if("desc".equalsIgnoreCase(sortOrder)){
                    sort = new Sort(Sort.Direction.DESC, sortName);
                }else{
                    sort = new Sort(Sort.Direction.ASC, sortName);
                }
            }
            page = Integer.parseInt(request.getParameter("pageNo")) - 1;
            size = Integer.parseInt(request.getParameter("pageSize"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(sort == null){
            sort = new Sort(Sort.Direction.ASC, "id");
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return pageRequest;
    }

}
