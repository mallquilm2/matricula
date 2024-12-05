/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cibertec.matricula.service;

import edu.cibertec.matricula.dao.UsuarioDAO;
import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioDAO usuarioDao;
    
    public UsuarioEntity getUsuario(String codigo){
        UsuarioEntity rpta = null;
        Optional<UsuarioEntity> busqueda = usuarioDao.findById(codigo);
        if(busqueda.isPresent())
            rpta = busqueda.get();
        return rpta;
    }
    
    public UsuarioEntity validarLogin(UsuarioEntity usuario){
        UsuarioEntity rpta = getUsuario(usuario.getUsuario());
        if(rpta == null)
                return rpta;
        
        if(!rpta.getClave().equalsIgnoreCase(usuario.getClave()))
            rpta = null;
        
        return rpta;
        
    }
    
    public List<UsuarioEntity> getListaUsuarios(){
        return usuarioDao.findAll();
    }
    
    public void insertarUsuario(UsuarioEntity usuario){
        usuarioDao.save(usuario);
    }
    
    public void eliminarUsuario(String codigo){
        usuarioDao.deleteById(codigo);
    }
    
}
