package edu.cibertec.matricula;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatriculaApplication {
    
    @Autowired
    HolaMundo holaMundo;

	public static void main(String[] args) {
		SpringApplication.run(MatriculaApplication.class, args);
	}

}
