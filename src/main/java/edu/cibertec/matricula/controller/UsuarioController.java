/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping("/")
    public String loginMostrar(){
        return "login";
    }
    
    @RequestMapping("loginAccion")
    public ModelAndView loginAccion(UsuarioEntity usuarioValida){
        ModelAndView mv = null;
        
        UsuarioEntity user = usuarioService.validarLogin(usuarioValida);
        if(user == null){
            mv = new ModelAndView("login", "msgError", "Usuario y/o clave incorrecto.");
            
        }else{
           mv = new ModelAndView("menu", "usuario", user);
        }
        return mv;
    }
    
}
