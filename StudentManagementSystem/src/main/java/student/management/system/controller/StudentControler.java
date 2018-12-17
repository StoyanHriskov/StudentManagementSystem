package student.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import student.management.system.model.Student;
import student.management.system.service.StudentService;

@Controller
public class StudentControler {
	private final StudentService studentService;

	@Autowired
	public StudentControler(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/addStudent")
	public String addStudentView(Model model) {
		model.addAttribute("student", new Student());
		return "addStudent";
	}

	// ADD STUDENT
	@PostMapping(value = "/addStudent")
	public String addStudent(@ModelAttribute("student") Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println(error);
			}
		}
		studentService.addStudent(student);
		return "redirect:/showStudents";
	}

	// SHOW ALL STUDENTS
	@GetMapping(value = "/showStudents")
	public ModelAndView showStudents(Model model) {
		ModelAndView modelAndView = new ModelAndView("showStudents");
		modelAndView.addObject("students", studentService.showAllStudents());
		return modelAndView;
	}

	@GetMapping("/allStudents")
	@ResponseBody
	public ResponseEntity<List<Student>> findAllStudents() {
		return ResponseEntity.ok(studentService.showAllStudents());
	}

	// EDIT STUDENT

	@GetMapping(value = "/editStudent/{id}")
	public ModelAndView editStudent(@PathVariable("id") int id, Student student) {
		ModelAndView modelAndView = new ModelAndView("editStudent");
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	@PostMapping(value = "/editStudent/{id}")
	public String editStudent(@ModelAttribute("student") Student student, @PathVariable("id") int Id) {
		studentService.editStudent(student);
		return "redirect:/showStudents";
	}

	// DELETE
	@DeleteMapping(value = "/removeStudent/{id}")
	public String removeStudentById(@PathVariable("id") int id) {
		studentService.removeStudent(id);
		return "redirect:/showStudents";
	}

	// FIND BY NAME
	@GetMapping("/studentsByName/{name}")
	public ResponseEntity<?> getStudentByName(@PathVariable String name) {
		List<Student> result = this.studentService.findByName(name);
		return ResponseEntity.ok(result);
	}

	// FIND BY AGE
	@GetMapping("/studentsByAge/{age}")
	public ResponseEntity<?> getStudentByAge(@PathVariable int age) {
		List<Student> result = this.studentService.findByAge(age);
		return ResponseEntity.ok(result);
	}

	// FIND BY ADDRESS
	@GetMapping("/studentsByAddress/{address}")
	public ResponseEntity<?> getStudentByAddress(@PathVariable String address) {
		List<Student> result = this.studentService.findByAddress(address);
		return ResponseEntity.ok(result);
	}

	// ADD COURSE
	@PostMapping("/students/{studentId}/courses{courseId}")
		public ResponseEntity<?> addCourseToStudent(
				@PathVariable int studentId, @PathVariable int courseId) {
			this.studentService.addCourse(studentId, courseId);
			return (ResponseEntity<?>) ResponseEntity.ok();
		}
	
}
