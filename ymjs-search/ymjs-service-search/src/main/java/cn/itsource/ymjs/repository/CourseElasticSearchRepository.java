package cn.itsource.ymjs.repository;

import cn.itsource.ymjs.CourseDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseElasticSearchRepository extends ElasticsearchRepository<CourseDoc,Long> {
}
