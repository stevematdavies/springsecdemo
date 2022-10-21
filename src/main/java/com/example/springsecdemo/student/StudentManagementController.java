package com.example.springsecdemo.student;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("Register a new Student");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("Delete a Student");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody  Student student) {
        System.out.println("Updating a student");
        System.out.printf("%s %s%n", studentId, student.toString());
    }


}
