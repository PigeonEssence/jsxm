package cn.itsource.ymjs.repository;

import cn.itsource.ymjs.entify.FitnessCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessCourseRepository extends JpaRepository<FitnessCourse,Long>, JpaSpecificationExecutor<FitnessCourse> {
}
