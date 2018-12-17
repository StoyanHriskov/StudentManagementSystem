package student.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.management.system.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);

    List<Student> findByAddress(String address);

    List<Student> findByAge(int age);
	
}
