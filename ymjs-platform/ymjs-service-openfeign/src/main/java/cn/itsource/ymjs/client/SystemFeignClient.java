package cn.itsource.ymjs.client;

import cn.itsource.ymjs.fallback.SystemFeignClientFallbackFactory;
import cn.itsource.ymjs.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "system-server",fallbackFactory = SystemFeignClientFallbackFactory.class)
public interface SystemFeignClient {

    //根据登录名查询机构
    @RequestMapping(value = "/tenant/selectByLoginId/{loginId}",method = RequestMethod.GET)
    JSONResult selectByLoginId(@PathVariable("loginId")Long loginId);
}
