package cn.itsource.ymjs.service;

import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.dto.CourseDto;
import cn.itsource.ymjs.utils.PageList;

public interface CourseElasticSearchService {
    PageList<CourseDoc> searchCourse(CourseDto courseDto);
}
