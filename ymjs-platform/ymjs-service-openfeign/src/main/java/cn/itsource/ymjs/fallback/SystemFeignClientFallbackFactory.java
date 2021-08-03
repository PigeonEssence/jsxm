package cn.itsource.ymjs.fallback;


import cn.itsource.ymjs.client.SystemFeignClient;
import cn.itsource.ymjs.result.JSONResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class SystemFeignClientFallbackFactory implements FallbackFactory<SystemFeignClient> {
    @Override
    public SystemFeignClient create(Throwable throwable) {

        return new SystemFeignClient() {
            @Override
            public JSONResult selectByLoginId(Long loginId) {
                throwable.printStackTrace();
                return JSONResult.error("降级了");
            }
        };
    }
}
