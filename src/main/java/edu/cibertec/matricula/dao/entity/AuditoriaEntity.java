
package edu.cibertec.matricula.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="auditoria")
public class AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idauditoria;
    
    @Column(name = "fechahora", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    
    @OneToOne
    @JoinColumn(name = "usuario", updatable = false, nullable = false)
    private UsuarioEntity usuario;
    
    private String operacion;
}
