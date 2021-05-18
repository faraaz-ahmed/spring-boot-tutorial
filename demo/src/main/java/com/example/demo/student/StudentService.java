package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // can also use @Component but it's more readable with @Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudents(Student student) {
        if (this.checkIfEmailExists(student)) {
            throw new IllegalStateException("email already exists");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> studentById = studentRepository.findStudentById(studentId);
        if (!studentById.isPresent()) {
            throw new IllegalStateException("email does not exist");
        }
        this.studentRepository.deleteById(studentId);
    }

    @Transactional // no need to use any queries because of this annotation.
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id:" + studentId + "doesn't exist."));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
//            if (this.checkIfEmailExists(student)) {
//                throw new IllegalStateException("email already exists");
//            }
            student.setEmail(email);
        }
    }

    public boolean checkIfEmailExists(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        return studentByEmail.isPresent();
    }
}
