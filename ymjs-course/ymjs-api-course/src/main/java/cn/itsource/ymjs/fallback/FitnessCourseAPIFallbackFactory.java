package cn.itsource.ymjs.fallback;

import cn.itsource.ymjs.api.FitnessCourseAPI;
import cn.itsource.ymjs.entify.FitnessCourse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**======================================================================================
 * 方法描述：降级
 ======================================================================================*/
@Component
@Slf4j
public class FitnessCourseAPIFallbackFactory implements FallbackFactory<FitnessCourseAPI> {

    @Override
    public FitnessCourseAPI create(Throwable throwable) {
        return new FitnessCourseAPI() {
            @Override
            public FitnessCourse selectById(Long id) {
                log.error(throwable.getMessage());
                throwable.printStackTrace();
                return null;
            }
        };
    }
}
