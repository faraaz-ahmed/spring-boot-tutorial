package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired // instantiates new object of StudentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody Student student) {
        // RequestBody is used to map the request payload into Student object
        System.out.println("put");
        studentService.addStudents(student);
    }

    @PutMapping()
    public void updateStudent(@RequestParam(required = true) Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        System.out.println("put");
        studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

}
