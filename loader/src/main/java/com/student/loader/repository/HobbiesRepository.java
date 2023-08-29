package com.student.loader.repository;

import com.student.loader.entity.HobbiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbiesRepository extends JpaRepository<HobbiesEntity,Integer> {

    public void deleteByStudentId(int id);
}
