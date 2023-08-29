package com.student.loader.controller;

import com.google.gson.Gson;
import com.student.loader.model.StudentAllDetails;
import com.student.loader.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
//@CrossOrigin(originPatterns = "*",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("csv") MultipartFile csvFile) throws IOException {
        studentService.saveCSVFile(csvFile.getInputStream());
        HashMap<String, String> result = new HashMap<>();
        result.put("fileName ", csvFile.getOriginalFilename());
        result.put("message", "File uploaded successfully");

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping()
    public List<StudentAllDetails> getAllDetails() {
        return studentService.getAllDetails();
    }

    @PostMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<String> updateStudent(@RequestBody() StudentAllDetails student) {
        studentService.update(student);
        return ResponseEntity.ok("Student updated successfully");
    }


    @PostMapping(value = "/delete" , consumes = "application/json",produces = "application/json")
    public ResponseEntity<String> deleteStudent(@RequestBody() StudentAllDetails stud) {
        //studentService.deleteStudent(stDetails);
        studentService.deleteStudent(stud);
        return ResponseEntity.ok(String.format("student %s deleted successfully",stud.getName()));
    }

    @GetMapping("/search")
    public List<StudentAllDetails> searchByName(@RequestParam("q") String name){

    return studentService.searchByName(name);
    }


}
