package com.ropulva.CalendarManagement.creator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreatorRepository extends JpaRepository<CreatorModel, String > {


    @Query(value = "SELECT * FROM creator WHERE email=:email",nativeQuery = true)
    CreatorModel getCreator(String email);
}
