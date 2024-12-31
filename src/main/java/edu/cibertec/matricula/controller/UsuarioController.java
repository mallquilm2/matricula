/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cibertec.matricula.controller;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.service.UsuarioService;
import java.io.IOException;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;
    
    Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    
    @RequestMapping("/login")
    public String loginMostrar(){
        return "login";
    }
    
    @RequestMapping("/menu")
    public ModelAndView menuMostrar(@AuthenticationPrincipal OAuth2User oAuth2User){
        Map<String,Object> attributes = oAuth2User.getAttributes();
        return new ModelAndView("menu","usuario",attributes.get("name"));
    }
    
    
    @RequestMapping("usuarioCrear")
    public ModelAndView crearUsuario(){
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", new UsuarioEntity());
        mv.addObject("accion", "Insertar");
        return mv;
    }
    
    
    @RequestMapping("usuarioGrabar")
    public ModelAndView grabarUsuario(@RequestParam("archivo") MultipartFile archivo,
            @Valid @ModelAttribute("usuarioBean") UsuarioEntity usuario, BindingResult result, ModelMap modelo) throws IOException{
        ModelAndView mv = null;
        
        if(result.hasErrors()){
            mv = new ModelAndView("usuarioDatos","usuarioBean",usuario);
            logger.warn("Datos ingresados incorrectos");
        }else{
            usuario.setFoto(archivo.getBytes());
            usuarioService.insertarUsuario(usuario);
            logger.info("Se registró correctamente el usuario");
            mv = new ModelAndView("usuariosLista", "lista", usuarioService.getListaUsuarios());
        }
        return mv;
    }

    
    @RequestMapping("fotoGrabar")
    public ModelAndView fotoGrabar(@RequestParam("archivo") MultipartFile archivo, @RequestParam("codigoUsuario") String codigoUsuario) throws IOException{
        
        UsuarioEntity usuario = usuarioService.getUsuario(codigoUsuario);
        usuario.setFoto(archivo.getBytes());
        
        return new ModelAndView("usuariosLista", "lista", usuarioService.getListaUsuarios());
    }

    
    @RequestMapping("usuarioMod")
    public ModelAndView usuarioModificar(@RequestParam("codigoUsuario") String codigo){
        ModelAndView mv = new ModelAndView("usuarioDatos", "usuarioBean", usuarioService.getUsuario(codigo));
        mv.addObject("accion","Modificar");
        return mv;
    }
    
    
    @RequestMapping("usuarioEli")
    public ModelAndView usuarioEliminar(@RequestParam("codigoUsuario") String codigo){
        usuarioService.eliminarUsuario(codigo);
        return new ModelAndView("redirect:usuarioListar");
    }
    
    @RequestMapping("usuarioListar")
    public ModelAndView usuarioListar(){
        return new ModelAndView("usuariosLista", "lista", usuarioService.getListaUsuarios());
    }
    
}
