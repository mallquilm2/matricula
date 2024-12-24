package edu.cibertec.matricula;

import edu.cibertec.matricula.dao.UsuarioDAO;
import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MatriculaApplicationTests {
    
    @Autowired
    private PasswordEncoder codificador;
    
    @Autowired
    private UsuarioDAO usao;
    

	@Test
	void contextLoads() {
	}
        
        //@Test
        void insertarUsuario(){
            UsuarioEntity ue = new UsuarioEntity();
            ue.setUsuario("juan");
            ue.setNombreCompleto("Juan Perez");
            ue.setClave(codificador.encode("12345"));
            
            UsuarioEntity a = usao.save(ue);
            Assertions.assertNotNull(a);
        }

}
