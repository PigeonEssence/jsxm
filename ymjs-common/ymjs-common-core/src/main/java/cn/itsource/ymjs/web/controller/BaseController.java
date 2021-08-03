package cn.itsource.ymjs.web.controller;


import cn.itsource.ymjs.result.JSONResult;
import cn.itsource.ymjs.result.PageResult;
import cn.itsource.ymjs.service.IBaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class BaseController<T,Q> {

    @Autowired
    protected IBaseService<T, Q> baseService ;

    @GetMapping("/one/{id}")
    @ApiOperation(value = "根据ID查询单个对象")
    public JSONResult selectById(@PathVariable("id") Long id){
        T t = baseService.selectById(id);
        return JSONResult.success(t);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除单个对象")
    public JSONResult deleteById(@PathVariable("id") Long id){
        boolean success = baseService.deleteById(id);
        return JSONResult.success(success);
    }

    @DeleteMapping
    @ApiOperation(value = "根据ID批量删除多个对象")
    public JSONResult deleteByIds(@RequestBody List<Long> ids){
        boolean success = baseService.deleteByIds(ids);
        return JSONResult.success(success);
    }

    @PostMapping
    @ApiOperation(value = "添加一个对象")
    public JSONResult insert(@RequestBody T t){
        T rt = baseService.insert(t);
        return JSONResult.success(rt);
    }

    //修改部门
    @PutMapping
    @ApiOperation(value = "根据ID修改对象")
    public JSONResult update(@RequestBody T t){
        T rt = baseService.updateById(t);
        return JSONResult.success(rt);
    }

    //分页查询
    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("/page")
    public JSONResult selectForPage(@RequestBody Q q){
        PageResult pageResult = baseService.selectPage(q);
        return JSONResult.success(pageResult);
    }

    @GetMapping("/all")
    @ApiOperation(value = "查询所有",notes="不带任何条件")
    public JSONResult selectAll(){
        return JSONResult.success(baseService.selectAll());
    }
}
