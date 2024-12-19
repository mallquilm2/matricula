
package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.AuditoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="auditoria")
public interface AuditoriaRespository extends JpaRepository<AuditoriaEntity, Integer>{
    
}
