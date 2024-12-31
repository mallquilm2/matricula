
package edu.cibertec.matricula.mapper;

import edu.cibertec.matricula.dao.entity.UsuarioEntity;
import edu.cibertec.matricula.domain.UsuarioDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    
    @Mapping(source = "id", target = "usuario")
    @Mapping(source = "contrasenia", target = "clave")  
    @Mapping(source = "nombre", target = "nombreCompleto") 
    UsuarioEntity usuarioDomainToUsuarioEntity(UsuarioDomain usuario);
}
