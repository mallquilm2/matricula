package edu.cibertec.matricula;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatriculaApplication implements CommandLineRunner{
    
    @Autowired
    HolaMundo holaMundo;

	public static void main(String[] args) {
		SpringApplication.run(MatriculaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        holaMundo.saludar();
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
                System.out.println("Inspeccionando los beans creados por spring boot.");
                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }
        };
    }

}
