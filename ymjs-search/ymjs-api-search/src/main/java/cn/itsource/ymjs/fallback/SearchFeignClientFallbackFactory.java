package cn.itsource.ymjs.fallback;


import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.api.SearchFeignClient;
import cn.itsource.ymjs.result.JSONResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SearchFeignClientFallbackFactory implements FallbackFactory<SearchFeignClient> {
        @Override
        public SearchFeignClient create(Throwable throwable) {
            return new SearchFeignClient() {

                @Override
                public JSONResult addCourse(CourseDoc doc) {
                    return JSONResult.error("添加失败，降级了");
                }

                @Override
                public JSONResult deleCourse(CourseDoc doc) {
                    return JSONResult.error("删除失败，降级了");
                }
            };
        }

}
