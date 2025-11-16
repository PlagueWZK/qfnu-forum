package com.qfnu.forum.repository;

import com.qfnu.forum.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByType(String type);
    List<Course> findByIsTrial(Boolean isTrial);
}
