package com.example.demo.Service;

import com.example.demo.Entities.Spid;
import com.example.demo.Entities.User;
import com.example.demo.Repository.SpidRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Utilities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SpidService {

    @Autowired
    private SpidRepo spidRepo;
    @Autowired
    private UserRepo userRepo;

    public Spid findSpidById(long id) throws Exception {
        Optional<Spid> spid = spidRepo.findById(id);

        if (!spid.isPresent()) {
            throw new Exception("This spid does not exists");
        }

        return spid.get();
    }

    @Transactional
    public Spid createSpid(Spid spid) throws Exception {
        Optional<Spid> currentSpid = spidRepo.findSpidByUserId(spid.getUserId());
        Optional<User> user = userRepo.findById(spid.getUserId().getId());

        if (!user.isPresent()) {
            throw new Exception("This user does not exists");
        }


        if (currentSpid.isPresent()) {
            throw new Exception("This user has already created a spid");
        }

        spid.setCreatedBy(user.get().getUsername());

        return spidRepo.save(spid);
    }

    public Iterable<Spid> retrieveAllSpids() {
        return spidRepo.findAll();
    }

    public Spid changeStatus(long id) throws Exception {
        Spid spid = findSpidById(id);
        spid.setStatus(Status.READY_FOR_REVIEW);
        return spidRepo.save(spid);
    }

    public void deleteSpid(long id) throws Exception {

        Optional<Spid> spid  = spidRepo.findById(id);

        if (!spid.isPresent()) {
            throw new Exception("This SPID does not exists");
        }

        if (spid.get().getStatus() != Status.PENDING) {
            throw new Exception("You are not allowed to delete this SPID");
        }

        spidRepo.delete(spid.get());
    }
}