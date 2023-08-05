package com.example.july2023.controller;

import com.example.july2023.model.Student;
import com.example.july2023.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/get/{rollno}")
    @Operation(description = "This method retrieves the student details based on the rollno" +
            "provided. If exists, it returns the 200(OK) response, else it returns" +
            "400(BAD_REQUEST) response")
    public ResponseEntity<Object> getStudentByRollno(@PathVariable("rollno") int rollno){
        System.out.println("INSIDE THE CONTROLLER - input: "+rollno);
        Student student = studentService.getStudentByRollno(rollno);
        if(student.getRollno() == 0){
            log.error("STUDENT DOESN'T EXIST");
            return new ResponseEntity<>("STUDENT DOESN'T EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public List<Student> getStudentsByName(@PathVariable("name") String name){
        log.info("Searching for the students with name: "+name);
        return studentService.getStudentsByName(name);
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        if(student.getRollno() <= 0)
            return new ResponseEntity<>("ROLLNO IS INVALID/MISSING - PLEASE CHECK AND ADD AGAIN", HttpStatus.BAD_REQUEST);
        try {
            studentService.upsertStudent(student);
            return new ResponseEntity<>("STUDENT ADDED SUCCESSFULLY", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("EXCEPTION OCCURED WHILE EXECUTING DB OPERATION, PLEASE CHECK WITH ADMINISTRATOR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public void updateStudent(@RequestBody Student student) throws JsonProcessingException {
        studentService.upsertStudent(student);
    }

    @DeleteMapping("/delete/{rollno}")
    public void deleteStudent(@PathVariable("rollno") int rollno){
        studentService.deleteStudent(rollno);
    }

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"students.csv\"");
        studentService.writeStudentsToCsv(servletResponse.getWriter());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {
        try {
            studentService.readFileContents(file.getInputStream());
            //FileUtils.forceDelete(file.getResource().getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

}
