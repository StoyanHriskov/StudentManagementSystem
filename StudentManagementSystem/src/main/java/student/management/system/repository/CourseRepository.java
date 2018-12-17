package student.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.management.system.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
