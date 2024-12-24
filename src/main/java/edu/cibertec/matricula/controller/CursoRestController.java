
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import edu.cibertec.matricula.service.CursoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoRestController {
    
    @Autowired
    private CursoService cursoService;
    
    @GetMapping
    public List<CursoEntity> listarTodos(){
        return cursoService.listarTodos();
    }
    
    @PostMapping
    public void insertarCurso(@RequestBody CursoEntity ce){
        cursoService.insertar(ce);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCurso(@PathVariable("id") int codigo){
        cursoService.eliminar(codigo);
    }
    
}
