
package edu.cibertec.matricula.security;

import edu.cibertec.matricula.dao.UsuarioDAO;
import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService{
    
    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity ue = usuarioDao.findById(username).get();
        
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        
        UserDetails udet = new User(ue.getUsuario(), "{noop}"+ue.getClave(), roles);
        return udet;
    }
    
}
