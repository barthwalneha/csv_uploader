package com.student.loader.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.student.loader.entity.AddressEntity;
import com.student.loader.entity.HobbiesEntity;
import com.student.loader.entity.StudentEntity;
import com.student.loader.model.Details;
import com.student.loader.model.StudentAllDetails;
import com.student.loader.repository.AddressRepository;
import com.student.loader.repository.HobbiesRepository;
import com.student.loader.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HobbiesRepository hobbiesRepository;


    public boolean saveCSVFile(InputStream csvStream) {
        List<StudentAllDetails> list = read(csvStream);
        list.forEach(d -> saveStudentDetail(d));
        return true;
    }

    public void update(StudentAllDetails student) {
        saveStudentDetail(student);
    }

    public void saveStudentDetail(StudentAllDetails details) {
        StudentEntity student = new StudentEntity(defaultIdIfNull(details.getId()), details.getName(), details.getAge(), details.getClassId());
        StudentEntity updatedStudent = studentRepository.save(student);

        AddressEntity address = new AddressEntity(defaultIdIfNull(details.getAddressId()), updatedStudent.getId(), details.getStreet(), details.getCity(), details.getState(), details.getZipcode());
        addressRepository.save(address);

        HobbiesEntity hobies = new HobbiesEntity(defaultIdIfNull(details.getHobbyId()), updatedStudent.getId(), details.getHobby1(), details.getHobby2(), details.getHobby3());
        hobbiesRepository.save(hobies);
    }

    private List<StudentAllDetails> read(InputStream csvStream) {
        return new CsvToBeanBuilder(new InputStreamReader(csvStream))
                .withSkipLines(1)
                .withType(StudentAllDetails.class)
                .build()
                .parse();


    }

    private int defaultIdIfNull(Integer id) {
        return id == null ? 0 : id;
    }


    public List<StudentAllDetails> getAllDetails() {
        List<Details> list = studentRepository.getAllDetails();
        List<StudentAllDetails> stdDeatils = new ArrayList<>();

        for (Details d : list) {
            StudentAllDetails ad = new StudentAllDetails(
                    d.getName(),
                    d.getAge(),
                    d.getClassId(),
                    d.getStreet(),
                    d.getCity(),
                    d.getState(),
                    d.getZipcode(),
                    d.getHobby1(),
                    d.getHobby2(),
                    d.getHobby3(),
                    d.getId(),
                    d.getHobbyId(),
                    d.getAddressId()
            );
            stdDeatils.add(ad);
        }

        return stdDeatils;
    }

    public String deleteStudent(StudentAllDetails st) {

        //StudentEntity st =studentRepository.findById(id).get();
        addressRepository.deleteById(st.getAddressId());
        hobbiesRepository.deleteById(st.getHobbyId());
        studentRepository.deleteById(st.getId());

        return "deleted";

    }

    public List<StudentAllDetails> searchByName(String name) {
        List<Details> list = studentRepository.searchByName(name);
        List<StudentAllDetails> stdDeatils = new ArrayList<>();

        for (Details d : list) {
            StudentAllDetails ad = new StudentAllDetails(
                    d.getName(),
                    d.getAge(),
                    d.getClassId(),
                    d.getStreet(),
                    d.getCity(),
                    d.getState(),
                    d.getZipcode(),
                    d.getHobby1(),
                    d.getHobby2(),
                    d.getHobby3(),
                    d.getId(),
                    d.getHobbyId(),
                    d.getAddressId()
            );
            stdDeatils.add(ad);
        }

        return stdDeatils;

    }
}
