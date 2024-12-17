
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.CursoEntity;
import edu.cibertec.matricula.service.CursoService;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CursoController {
    
    
    @Autowired
    private CursoService cursoService;
    
    @RequestMapping("cursoListar")
    public ModelAndView cursoMostrarMicro(){
        ModelAndView mv = new ModelAndView("curso","lista",cursoService.listarTodos());
        mv.addObject("cursoBean",new CursoEntity());
        return mv;
    }
    
    @RequestMapping("cursoGrabar")
    public ModelAndView cursoGrabar(@ModelAttribute("cursoBean") CursoEntity curso){
        ModelAndView mv = new ModelAndView("curso");
        cursoService.insertar(curso);
        mv.addObject("lista",cursoService.listarTodos());
        return mv;
    }
    
    @RequestMapping("cursoEliminar")
    public ModelAndView cursoEliminar(@RequestParam("codigo") int codigo){
        cursoService.eliminar(codigo);
        return new ModelAndView("redirect:cursoListar");
    }
    
    
    @RequestMapping("cursoMostrar")
    public String cursoMostrar(){
        return "cursoBusqueda";
    }
    
    @RequestMapping("cursoBusqueda")
    public ModelAndView busquedaAccion(@RequestParam("tipo") String tipo, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("cursoBusqueda");
        
        switch (tipo) {
            case "estado":
                int estado = Integer.parseInt(request.getParameter("estado"));
                mv.addObject("lista", cursoService.consultaPorEstado(estado));
                break;
            case "incompleto":
                mv.addObject("lista", cursoService.abiertoIncompleto());
                break;
            case "faltante":
                int faltante = Integer.parseInt(request.getParameter("numero"));
                mv.addObject("lista", cursoService.consultarFaltantes(faltante));
                break;
            case "porFecha":
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                mv.addObject("lista", cursoService.consultarPorFecha(fecha));
                break;
            case "nombre":
                String nombre = request.getParameter("nombreCurso");
                mv.addObject("lista", cursoService.consultarPorNombre(nombre));
                break;
        }
        
        return mv;
    }
    
}
