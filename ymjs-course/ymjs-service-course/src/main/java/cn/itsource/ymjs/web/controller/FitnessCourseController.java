package cn.itsource.ymjs.web.controller;

import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.query.CourseQuery;
import cn.itsource.ymjs.result.JSONResult;
import cn.itsource.ymjs.service.IFitnessCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fitnessCourse")
@Api
public class FitnessCourseController  extends BaseController<FitnessCourse, CourseQuery>{

    @Autowired
    private IFitnessCourseService fitnessCourseService;

}
