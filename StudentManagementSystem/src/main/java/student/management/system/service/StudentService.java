package student.management.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import student.management.system.model.Course;
import student.management.system.model.Student;
import student.management.system.repository.CourseRepository;
import student.management.system.repository.StudentRepository;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private CourseRepository courseRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public void addStudent(Student studentToAdd) {
				this.studentRepository.save(studentToAdd);
	}

	public void addCourseToStudent(int studentID, int courseID) {
		Course course = this.courseRepository.getOne(courseID);
		Student studentToSave = this.studentRepository.getOne(studentID);
		studentToSave.addCourse(course);
		this.studentRepository.save(studentToSave);
	}

	public void removeStudent(int studentID) {
		Student studentToDelete = studentRepository.getOne(studentID);
		if(studentToDelete!=null) {
			this.studentRepository.delete(studentToDelete);
		}
	}

	public List<Student> showAllStudents() {
		return this.studentRepository.findAll();
	}
	
	public void editStudent(Student student) {
        studentRepository.save(student);
    }
	
	public void addCourse(int studentId,int courseId) {
		Course courseToAdd = this.courseRepository.getOne(courseId);
		Student studentToAdd = this.studentRepository.getOne(studentId);
		this.studentRepository.getOne(studentId).addCourse(courseToAdd);
		this.courseRepository.getOne(courseId).addStudent(studentToAdd);
	}
	
	public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
	
	public List<Student> findByAge(int age){
		return studentRepository.findByAge(age);
	}
	
	public List<Student> findByAddress(String address){
		return studentRepository.findByAddress(address);
	}
}
