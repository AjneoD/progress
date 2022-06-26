package com.example.demo.Repository;


import com.example.demo.Entities.Spid;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpidRepo extends JpaRepository<Spid, Long> {
    Optional<Spid> findSpidByUserId(User users);

}