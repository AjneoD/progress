package com.example.demo.Repository;


import com.example.demo.Entities.Spid;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpidRepo extends JpaRepository<Spid, Long> {
    List<Spid> findSpidByUserId(User user);
}