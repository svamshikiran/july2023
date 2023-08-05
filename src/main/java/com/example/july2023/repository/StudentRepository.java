package com.example.july2023.repository;

import com.example.july2023.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findByName(String name);
    //select * from student where name=?

    public List<Student> findByCity(String city);
    //select * from student where city=?

    public List<Student> findByNameAndCity(String name, String city);
    //select * from student where name=? and city=?

    /*
    @NamedQuery(name = "select count(*) from student")
    public int findNoOfStudents();

     */

}















    /*
    public List<Student> findByName(String name);

    public List<Student> findByCity(String city);

    @Query(name = "select * from student where name=? and city=?")
    public List<Student> findByNameAndCity(String name, String city);

     */

