package com.udemy.spring.repositories;

import com.udemy.spring.entities.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {

    @Query("SELECT l from List l LEFT JOIN l.user u WHERE u.email = ?1")
    public java.util.List<List> findByUserEmail(String email);
}
