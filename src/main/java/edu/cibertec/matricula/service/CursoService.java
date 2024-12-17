
package edu.cibertec.matricula.service;

import edu.cibertec.matricula.dao.CursoDAO;
import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CursoService {
    
    private RestTemplate restTemplate;
    
    public CursoService(){
        restTemplate = new RestTemplate();
    }
    
    @Autowired
    CursoDAO cursoDAO;
    
    
    
    @Value("${uri.rest.cursos}")
    private String urlServidorMicro;
    
    //Métodos de Microservicio
    
    public List<CursoEntity> listarTodos(){
        List<CursoEntity> rpta = null;
        CursoEntity[] lista = restTemplate.getForObject(urlServidorMicro, CursoEntity[].class);
        rpta = Arrays.asList(lista);
        return rpta;
    }
    
    public CursoEntity obtenerUno(int codigo){
        CursoEntity rpta = null;
        rpta = restTemplate.getForObject(urlServidorMicro+"/"+codigo, CursoEntity.class);
        return rpta;
    }
    
    public void insertar(CursoEntity ce){
        restTemplate.postForEntity(urlServidorMicro, ce, CursoEntity.class);
    }
    
    public void modificar(CursoEntity ce){
        restTemplate.put(urlServidorMicro+"/"+ce.getIdCurso(), ce);
    }
    
    public void eliminar(int codigo){
        restTemplate.delete(urlServidorMicro+"/"+codigo);
    }
    
    //Métodos de Monolito
    
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
