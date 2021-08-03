package cn.itsource.ymjs.utils;


import cn.itsource.ymjs.vo.AggrTermsVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//分页对象：easyui只需两个属性，total(总数),datas（分页数据）就能实现分页
@Data
public class PageList<T> {
    private Long total;

    private List<T> rows = new ArrayList<>();

    private Map<String,List<AggrTermsVo>> aggrMap;

    //提供有参构造方法，方便测试
    public PageList(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    //除了有参构造方法，还需要提供一个无参构造方法
    public PageList() {
    }

    public PageList(Long total, Map<String, List<AggrTermsVo>> aggrMap) {
        this.total = total;
        this.aggrMap = aggrMap;
    }

    public PageList(Long total, List<T> rows, Map<String, List<AggrTermsVo>> aggrMap) {
        this.total = total;
        this.rows = rows;
        this.aggrMap = aggrMap;
    }
}
