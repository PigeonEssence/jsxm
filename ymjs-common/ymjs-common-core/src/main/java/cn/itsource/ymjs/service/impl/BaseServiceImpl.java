package cn.itsource.ymjs.service.impl;

import cn.itsource.ymjs.constants.SortConstants;
import cn.itsource.ymjs.exception.GlobalException;
import cn.itsource.ymjs.query.BaseQuery;
import cn.itsource.ymjs.result.PageResult;
import cn.itsource.ymjs.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
public abstract class BaseServiceImpl<T,Q> implements IBaseService<T,Q> {

    @Autowired
    private JpaRepository<T,Long> repository;

    @Override
    public T selectById(Long id) {
        try {
            return repository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("未找到数据 ## selectById({})",id);
        }
        return null;
    }

    @Override
    public T updateById(T t) {
        //有ID就是修改
        return repository.save(t);
    }

    @Override
    public T insert(T t) {
        return repository.save(t);
    }

    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<T> selectAll() {
        return repository.findAll();
    }

    @Override
    public PageResult selectPage(Q q) {
        /**=====================================zs处理排序=====================================**/
        Pageable pageable = creatPageable(q);;
        /**=====================================带条件查询=====================================**/
        Specification specification = createWhereForSelectPage(q);
        /**=====================================执行查询=====================================**/
        Page<T> result = null;
        if(specification == null){
            result = repository.findAll(pageable);
        }else{
            if(!(repository instanceof JpaSpecificationExecutor)){
                throw new GlobalException("repository 不是 JpaSpecificationExecutor的子类");
            }
            JpaSpecificationExecutor repository = (JpaSpecificationExecutor) this.repository;
            result = repository.findAll(specification,pageable);
        }
        /**=====================================封装结果=====================================**/
        return new PageResult(result.getTotalElements(),result.getContent());
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        ids.forEach(id->{repository.deleteById(id);});
        return true;
    }

    /**======================================================================================
     * 方法描述：创建分页对象
     ======================================================================================*/
    protected Pageable  creatPageable(Q q){
        if(!(q instanceof BaseQuery)){
            throw new GlobalException("查询对象必须继承BaseQuery,或者Controller<T,Q>泛型指定错误");
        }
        BaseQuery baseQuery = (BaseQuery) q;
        Pageable pageable;
        if(StringUtils.hasLength(baseQuery.getSortField())){
            /**=====================================处理排序=====================================**/
            //添加排序字段，和排序方式
            Sort.Direction orderType = Sort.Direction.ASC;
            if(StringUtils.hasLength(baseQuery.getSortType()) && baseQuery.getSortType().equalsIgnoreCase("desc")){
                orderType =  Sort.Direction.DESC;
            }
            /**=====================================创建分页查询对象=====================================**/
            pageable = new PageRequest(baseQuery.getCurrentPage(), baseQuery.getPageSize(),
                    orderType,
                    //SortConstants 页面传入排序编号，对应排序字段
                    SortConstants.getField(baseQuery.getSortField()));
        }else{
            /**=====================================没有排序字段情况=====================================**/
            //不排序
            pageable = new PageRequest(baseQuery.getCurrentPage()-1, baseQuery.getPageSize());
        }
        return pageable;
    }

    /**======================================================================================
     * 方法描述：创建条件对象 ， 由子类自己实现

     return new Specification<T>(){
        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            //关键字查询
            if(baseQuery.getKeyword() != null){
                //关键字模糊查询
                Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), "%"+baseQuery.getKeyword()+"%");
                query.where(criteriaBuilder.and(p1));
            }
            return query.getRestriction();
        }
    };
     ======================================================================================*/
    protected Specification createWhereForSelectPage(Q q){
       return null;
    }

}
