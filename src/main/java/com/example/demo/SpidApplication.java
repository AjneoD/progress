package com.example.demo;

import com.example.demo.Entities.Spid;
import com.example.demo.Entities.User;
import com.example.demo.Utilities.Status;
import com.example.demo.Utilities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpidApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpidApplication.class, args);
	}

}