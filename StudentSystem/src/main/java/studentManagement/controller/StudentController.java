package studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import studentManagement.model.Student;
import studentManagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents = studentService.findAll();
		theModel.addAttribute("Students",theStudents);
		return "list-Students";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {
		Student theStudent = studentService.findById(theId);
		theModel.addAttribute("Student",theStudent);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("department") String department, @RequestParam("country") String country) {
		Student theStudent;
		if(id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCountry(country);
			theStudent.setDepartment(department);
		}
		else
		{
			theStudent = new Student(firstName, lastName, department, country);
			
		}
		studentService.save(theStudent);
		return "redirect:/student/list";
	}

}
