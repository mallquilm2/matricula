
package edu.cibertec.matricula.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


public class JwtUtil {
    
    //Metodo para crear el JWT y enviarlo al cliente en el heder de la respuesta.
    static void addAuthentication(HttpServletResponse res, String username){
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+60000))
                .signWith(SignatureAlgorithm.HS512, "C1bert3c")
                .compact();
        res.addHeader("Autorization", token);
    }
    
    //Valida el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        
        if(token!=null){
            String user = Jwts.parser().setSigningKey("C1bert3c")
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();
            
            return user!=null
                    ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList())
                    : null;
            
        }
        return null;
    }
    
}
