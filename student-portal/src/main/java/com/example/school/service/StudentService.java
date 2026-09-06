package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        if (student.getEnrolledCourses() == null) {
            student.setEnrolledCourses(new ArrayList<>());
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    public Student enrollCourse(String studentId, String courseId) {
        Student student = getStudentById(studentId);

        if (student.getEnrolledCourses() == null) {
            student.setEnrolledCourses(new ArrayList<>());
        }

        if (!student.getEnrolledCourses().contains(courseId)) {
            student.getEnrolledCourses().add(courseId);
            studentRepository.save(student);
        }
        return student;
    }

    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    public Student updateStudent(String id, Student studentDetails) {
        Student existingStudent = getStudentById(id);

        existingStudent.setName(studentDetails.getName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setDepartment(studentDetails.getDepartment());

        return studentRepository.save(existingStudent);
    }
}