package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // can also use @Component but it's more readable with @Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(1L, "alex", "alex@gm.com", LocalDate.of(2000, Month.JANUARY, 5), 21)
        );
    }
}
