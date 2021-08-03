package cn.itsource.ymjs.service.impl;

import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.query.CourseQuery;
import cn.itsource.ymjs.repository.FitnessCourseRepository;
import cn.itsource.ymjs.service.IFitnessCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Transactional
@Service
public class FitnessCourseServiceImpl extends BaseServiceImpl<FitnessCourse, CourseQuery> implements IFitnessCourseService {

    /**======================================================================================
     * 方法描述：查询方法在父类，这个构造自己的查询条件
     ======================================================================================*/
    @Override
    protected Specification createWhereForSelectPage(CourseQuery courseQuery) {
        return new Specification<FitnessCourse>(){
            @Override
            public Predicate toPredicate(Root<FitnessCourse> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(courseQuery.getKeyword() != null){
                    //关键字模糊查询
                    Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), "%"+courseQuery.getKeyword()+"%");
                    query.where(criteriaBuilder.and(p1));
                }
                return query.getRestriction();
            }
        };
    }

}
