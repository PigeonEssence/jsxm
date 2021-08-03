package cn.itsource.ymjs.service;

import cn.itsource.ymjs.result.PageResult;

import java.util.List;

public interface IBaseService<T,Q> {

    T selectById(Long id);

    T updateById(T t);

    T insert(T t);

    boolean deleteById(Long id);

    List<T> selectAll();

    PageResult selectPage(Q q);

    boolean deleteByIds(List<Long> ids);
}
