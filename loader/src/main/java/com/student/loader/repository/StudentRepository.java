package com.student.loader.repository;

import com.student.loader.entity.StudentEntity;
import com.student.loader.model.Details;
import com.student.loader.model.StudentAllDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    @Query(nativeQuery = true,
    value="select  d.id,name,age,class classId,a.id addressId, street,city,state,zipcode,h.id hobbyId,hobby1,hobby2,hobby3 " +
            " from student_details d join student_address a on d.id = a.student_id " +
            "join student_hobbies h on d.id=h.student_id")
    public List<Details> getAllDetails();

    @Query(nativeQuery = true,
            value="select  d.id,name,age,class classId,a.id addressId, street,city,state,zipcode,h.id hobbyId,hobby1,hobby2,hobby3 " +
                    " from student_details d join student_address a on d.id = a.student_id " +
                    "join student_hobbies h on d.id=h.student_id where d.id :id")
    public Details getDetails(@Param("id") int studentId);

    @Query(nativeQuery = true,
            value="select  d.id,name,age,class classId,a.id addressId, street,city,state,zipcode,h.id hobbyId,hobby1,hobby2,hobby3 " +
                    " from student_details d join student_address a on d.id = a.student_id " +
                    "join student_hobbies h on d.id=h.student_id where d.name like %:name%")
    public List<Details> searchByName(@Param("name") String name);


}
