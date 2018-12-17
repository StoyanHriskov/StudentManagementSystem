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

import student.management.system.model.Course;
import student.management.system.service.CourseService;

@Controller
public class CourseControler {
	private final CourseService courseService;

	@Autowired
	public CourseControler(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("/addCourse")
    public String addCourseView(Model model){
        model.addAttribute("course", new Course());
        return "addCourse";
    }
	
	//ADD COURSE
	@PostMapping(value="/addCourse")
	public String addCourse(@ModelAttribute("course") Course course, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error);
            }
        }
		this.courseService.addCourse(course);
		return "redirect:/showStudents";
	}
	//DELETE
    
	 @DeleteMapping(value = "/removeCourse/{id}")
	    public String removeStudent(@PathVariable("id") int Id) {
	        courseService.removeCourse(Id);
	        return "redirect:/showCourses";
	    }
	 
	//SHOW ALL COURSES
		@GetMapping(value="/showCourses")
		public ModelAndView showCourses(Model model){
	        ModelAndView modelAndView = new ModelAndView("showCourses");
	        modelAndView.addObject("courses", courseService.showAllCourses());
	        return modelAndView;
	    }
		
		@GetMapping("/allCourses")
	    @ResponseBody
	    public ResponseEntity<List<Course>> findAllStudents(){
	        return ResponseEntity.ok(courseService.showAllCourses());
	    }
	
}
