
package edu.cibertec.matricula.service;

import edu.cibertec.matricula.dao.CursoDAO;
import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    
    @Autowired
    CursoDAO cursoDAO;
    
    public List<CursoEntity> consultaPorEstado(int estado){
        return cursoDAO.consultaPorEstado(estado);
    }
    
    
    public List<CursoEntity> abiertoIncompleto(){
        return cursoDAO.abiertoIncompleto();
    }
    
    
    public List<CursoEntity> consultarFaltantes(Integer cantidad){
        return cursoDAO.consultarFaltantes(cantidad);
    }
    
    
    public List<CursoEntity> consultarPorFecha(Date fecha){
        return cursoDAO.consultarPorFecha(fecha);
    }
    
    
    public List<CursoEntity> consultarPorNombre(String cadena){
        return cursoDAO.consultarPorNombre(cadena);
    }
    
    
}
