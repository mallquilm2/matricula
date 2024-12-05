
package edu.cibertec.matricula.dao;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CursoDAO extends JpaRepository<CursoEntity, Integer>{
    
    //Consulta de Cursos por estado
    @Query("SELECT c FROM CursoEntity c WHERE c.estado = ?1")
    public List<CursoEntity> consultaPorEstado(int estado);
    
    //Consulta de curso abierto pero aun no completado
    //NamedQuery
    public List<CursoEntity> abiertoIncompleto();
    
    //Consulta de Cursos a los que falta X número de alumnos para llenarse.
    //Native QUery
    @Query(value = "SELECT * FROM Curso WHERE   alumnosMin - alumnosAct = :cantidad", nativeQuery = true)
    public List<CursoEntity> consultarFaltantes(@Param("cantidad") Integer cantidad);
    
    //Consulta de cursos despues de la fecha X
    //Manejo de fecha
    @Query("SELECT c FROM CursoEntity c WHERE c.fechaInicio >= :fecha")
    public List<CursoEntity> consultarPorFecha(@Param("fecha") Date fecha);
    
    //Consulta de curso por su nombre
    //Invocación por Store Procedure
    @Query(nativeQuery = true, value = "call Curso_Por_Nombre(:nombre)")
    public List<CursoEntity> consultarPorNombre(@Param("nombre") String cadena);
    
}
