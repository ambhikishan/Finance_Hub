package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.SavingsPlan;

@Repository

public interface SavingsPlanRepo extends JpaRepository<SavingsPlan,Long>
{

    @Query("SELECT s FROM SavingsPlan s WHERE s.name=:name")
    Optional<SavingsPlan> findByName(String name);


}