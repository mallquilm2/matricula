
package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<UsuarioEntity, String>{
    
}
