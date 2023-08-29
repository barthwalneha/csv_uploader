package com.student.loader.repository;

import com.student.loader.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
    public void deleteByStudentId(int id);

}
