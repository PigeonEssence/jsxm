package cn.itsource.ymjs.web.controller;

import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.query.CourseQuery;
import cn.itsource.ymjs.result.JSONResult;
import cn.itsource.ymjs.service.IFitnessCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope  //刷新配置
@RestController
@RequestMapping("/fitnessCourse")
@Api
public class FitnessCourseController  extends BaseController<FitnessCourse, CourseQuery>{
    @Autowired
    private IFitnessCourseService fitnessCourseService;

    //课程上线 onLineCourse
    @RequestMapping(value="/onLineCourse/{id}",method= RequestMethod.POST)
    public JSONResult onLineCourse(@PathVariable("id") Long id){
        try {
         fitnessCourseService.onLineCourse(id);
             return JSONResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.error("上线失败！"+e.getMessage());
        }
}

    //课程下线 offLineCourse
    @RequestMapping(value="/offLineCourse/{id}",method= RequestMethod.POST)
    public JSONResult offLineCourse(@PathVariable("id") Long id){
        try {
            fitnessCourseService.offLineCourse(id);
            return JSONResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.error("下线失败！"+e.getMessage());
        }
    }

/*    @GetMapping("/user/{id}")
    //限流降级
    @SentinelResource(value="course",blockHandler="exceptionHandler",fallback= "getByIdfallback")
    public FitnessCourse getById(@PathVariable Long id){
        FitnessCourse fitnessCourse = new FitnessCourse();
        fitnessCourse.setId(1L);
        fitnessCourse.setName("啦啦啦");
        return fitnessCourse;
}
    // 限流与阻塞处理 : 参数要和 被降级的方法参数一样
    public FitnessCourse exceptionHandler(@PathVariable Long id, BlockException ex) {
        ex.printStackTrace();
        System.out.println("限流了...");
        FitnessCourse fitnessCourse = new FitnessCourse();
        fitnessCourse.setId(-1L);
        fitnessCourse.setName("限流了");
        return fitnessCourse;
    }

    // 熔断降级，参数和返回值与源方法一致
    public FitnessCourse getByIdfallback(@PathVariable Long id){
        FitnessCourse fitnessCourse = new FitnessCourse();
        fitnessCourse.setId(-1L);
        fitnessCourse.setName("熔断了");
        return  fitnessCourse;
    }*/
}
