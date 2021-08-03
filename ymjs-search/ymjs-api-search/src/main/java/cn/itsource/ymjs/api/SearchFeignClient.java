package cn.itsource.ymjs.api;

import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.fallback.SearchFeignClientFallbackFactory;
import cn.itsource.ymjs.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-search",fallbackFactory = SearchFeignClientFallbackFactory.class)
public interface SearchFeignClient {

    @RequestMapping(value = "/es/addCourse", method = RequestMethod.POST)
    JSONResult addCourse(@RequestBody CourseDoc doc);

    @RequestMapping(value = "/es/delCourse", method = RequestMethod.POST)
    JSONResult deleCourse(@RequestBody CourseDoc doc);
}