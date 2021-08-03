package cn.itsource.ymjs.api;

import cn.itsource.ymjs.constants.APIConstants;
import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.fallback.FitnessCourseAPIFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**======================================================================================
 * 方法描述：课程RPC的API
 ======================================================================================*/
@FeignClient(value = APIConstants.NAME_SERVICE_COURSE , fallbackFactory = FitnessCourseAPIFallbackFactory.class)
@RequestMapping("/fitnessCourse" )
public interface FitnessCourseAPI {

    @GetMapping("/one/{id}")
    FitnessCourse selectById(@PathVariable("id")Long id);
}
