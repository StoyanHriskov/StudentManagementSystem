package student.management.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.management.system.model.Course;
import student.management.system.repository.CourseRepository;

@Service
public class CourseService {
	private CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public void addCourse(Course course) {
		this.courseRepository.save(course);
	}

	public void removeStudentFromCourse(int courseId, int studentId) {
		this.courseRepository.getOne(courseId).removeStudent(studentId);
	}

	public void removeCourse(int courseId) {
		Course courseToDelete = courseRepository.getOne(courseId);
		if(courseToDelete!=null) {
			this.courseRepository.delete(courseToDelete);
		}
	}

	public List<Course> showAllCourses() {
		return this.courseRepository.findAll();
	}


}
