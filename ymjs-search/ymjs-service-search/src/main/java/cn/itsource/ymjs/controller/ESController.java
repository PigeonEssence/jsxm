package cn.itsource.ymjs.controller;


import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.dto.CourseDto;
import cn.itsource.ymjs.result.JSONResult;
import cn.itsource.ymjs.service.CourseElasticSearchService;
import cn.itsource.ymjs.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.itsource.ymjs.repository.CourseElasticSearchRepository;

@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private CourseElasticSearchRepository repository;

    @Autowired
    private CourseElasticSearchService courseElasticSearchService;
    //添加课程
    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public JSONResult addCourse(@RequestBody CourseDoc doc){
        System.out.println("进来了");
        repository.save(doc);
        System.out.println("进来了");
        return JSONResult.success();
    }
    //删除课程
    @RequestMapping(value = "/delCourse",method = RequestMethod.POST)
    public JSONResult deleCourse(@RequestBody CourseDoc doc){
        repository.delete(doc);
        return JSONResult.success();
    }
    //搜索课程
    @RequestMapping(value = "/searchCourse",method = RequestMethod.POST)
    public PageList<CourseDoc> searchCourse(@RequestBody CourseDto courseDto){
        return courseElasticSearchService.searchCourse(courseDto);
    }
}
