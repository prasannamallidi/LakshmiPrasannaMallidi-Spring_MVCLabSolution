package studentManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import studentManagement.model.Student;

@Repository
public class StudentServiceImpl implements  StudentService{

	private SessionFactory sessionFactory;
	private org.hibernate.Session session;
	
	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try{
			session = sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Transactional
	public List<Student> findAll() {
		
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from student").list();
		tx.commit();
		return students;
	}

	@Transactional
	public Student findById(int theId) {
		Student student = new Student();
		Transaction tx = session.beginTransaction();
		student = session.get(Student.class, theId);
		tx.commit();
		return student;
	}

	@Transactional
	public void save(Student student) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
		
	}

	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		//session.delete(Student.class, theId);
		tx.commit();
		
	}

}
