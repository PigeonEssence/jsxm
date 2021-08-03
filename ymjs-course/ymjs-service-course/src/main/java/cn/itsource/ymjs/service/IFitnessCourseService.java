package cn.itsource.ymjs.service;

import cn.itsource.ymjs.entify.FitnessCourse;
import cn.itsource.ymjs.query.BaseQuery;
import cn.itsource.ymjs.query.CourseQuery;
import cn.itsource.ymjs.result.JSONResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFitnessCourseService  extends IBaseService<FitnessCourse, CourseQuery>{

    void onLineCourse(Long id);

    void offLineCourse(Long id);
}
