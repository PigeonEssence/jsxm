package cn.itsource.ymjs.service.impl;

import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.api.SearchFeignClient;
import cn.itsource.ymjs.constants.BaseConstance;
import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.query.CourseQuery;

import cn.itsource.ymjs.result.JSONResult;
import cn.itsource.ymjs.service.IFitnessCourseService;

import cn.itsource.ymjs.utils.AssertUtil;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Transactional
@Service
public class FitnessCourseServiceImpl extends BaseServiceImpl<FitnessCourse, CourseQuery> implements IFitnessCourseService {
    @Autowired
    public SearchFeignClient searchFeignClient;
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

    @Override
    public void onLineCourse(Long id) {
        //* 1.判断参数
        AssertUtil.isNotNull(id,"无效的参数" );

        //* 2.判断课程状态必须是下线状态
        FitnessCourse course = super.selectById(id);
        AssertUtil.isNotNull(course,"课程无效" );
        AssertUtil.isTrue(course.getStatus() == BaseConstance.CourseConstants.STATUS_OFF_LIINE,
                "课程不是下线状态");

        //* 3.修改课程状态为：上线状态
        course.setStatus(BaseConstance.CourseConstants.STATUS_ON_LIINE);
        //添加上线时间
        Date date = new Date();
        course.setOnlineTime(date);
        //通过ID上传
        super.updateById(course);

       //* 4.调用Feign保存课程到ES中
        CourseDoc doc = new CourseDoc();
        BeanUtils.copyProperties(course, doc);

        JSONResult result = searchFeignClient.addCourse(doc);
        AssertUtil.isTrue( result.isSuccess(),"课程添加到ES失败");

        //publishMessageToMQ(course,date);
    }

    @Override
    public void offLineCourse(Long id) {
        //* 1.判断参数
        AssertUtil.isNotNull(id,"无效的参数" );

        //* 2.判断课程状态必须是上线状态
        FitnessCourse course = super.selectById(id);
        AssertUtil.isNotNull(course,"课程无效" );
        AssertUtil.isTrue(course.getStatus() == BaseConstance.CourseConstants.STATUS_ON_LIINE,
                "课程不是下线状态");

        //* 3.修改课程状态为：上线状态
        course.setStatus(BaseConstance.CourseConstants.STATUS_OFF_LIINE);
        //添加下线时间
        //Date date = new Date();
        //course.setOnlineTime(date);
        //通过ID上传
        super.updateById(course);

        //* 4.调用Feign保存课程到ES中
        CourseDoc doc = new CourseDoc();
        BeanUtils.copyProperties(course, doc);

        JSONResult result = searchFeignClient.deleCourse(doc);
        AssertUtil.isTrue( result.isSuccess(),"课程删除失败");

        //publishMessageToMQ(course,date);
    }
}
