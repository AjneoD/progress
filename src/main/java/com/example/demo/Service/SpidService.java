package com.example.demo.Service;

import com.example.demo.Entities.Spid;
import com.example.demo.Entities.User;
import com.example.demo.Repository.SpidRepo;
import com.example.demo.Utilities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpidService {

    @Autowired
    private SpidRepo spidRepo;

    public List<Spid> getSpidByUserId(User user) {
        return spidRepo.findSpidByUserId(user);
    }

    @Transactional
    public Spid createSpid(Spid spid) throws Exception {
        List<Spid> spids = spidRepo.findSpidByUserId(spid.getUserId());
        if (spids.size() > 0) {
            throw new Exception("This user has already created a spid");
        }
        spid.setCreatedBy(spid.getUserId().getUsername());
        return spidRepo.save(spid);
    }

    public Iterable<Spid> retrieveAllSpids() {
        return spidRepo.findAll();
    }

    public Spid changeStatus(Spid spid) {
        spid.setStatus(Status.READY_FOR_REVIEW);
        return spidRepo.save(spid);
    }

    public Spid getSpid(User user) throws Exception {
        List<Spid> spid = spidRepo.findSpidByUserId(user);
        if (spid.size() <= 0) {
            throw new Exception("There is no SPID for this user");
        }
        return spid.get(0);
    }

    public void deleteSpid(Spid spid) throws Exception {
        if (spid.getStatus() != Status.PENDING) {
            throw new Exception("You are not allowed to delete this SPID");
        }
        spidRepo.delete(spid);
    }
}