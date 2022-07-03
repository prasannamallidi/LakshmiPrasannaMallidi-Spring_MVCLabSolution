package studentManagement.service;

import java.util.List;
import studentManagement.model.Student;
import javax.persistence.*;


public interface StudentService {
	
	public List<Student> findAll();
	public Student findById(int theId);
	public void save(Student student);
	public void deleteById(int theId);
}
