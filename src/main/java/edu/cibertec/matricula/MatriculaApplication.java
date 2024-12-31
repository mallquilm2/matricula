package edu.cibertec.matricula;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.domain.UsuarioDomain;
import edu.cibertec.matricula.mapper.UsuarioMapper;
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
            
            UsuarioDomain usuario = new UsuarioDomain();
            usuario.setId("1");
            usuario.setContrasenia("12345");
            usuario.setNombre("Juan Perez");
            
            UsuarioEntity usuarioEntity = UsuarioMapper.INSTANCE
                    .usuarioDomainToUsuarioEntity(usuario);
            
            System.out.println("ID: "+usuarioEntity.getUsuario());
            System.out.println("NOMBRE: "+usuarioEntity.getNombreCompleto());
            System.out.println("CONTRASEÑA: "+usuarioEntity.getClave());
            
            SpringApplication.run(MatriculaApplication.class, args);
	}

}
