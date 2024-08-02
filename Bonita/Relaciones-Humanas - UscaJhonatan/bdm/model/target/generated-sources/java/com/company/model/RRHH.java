
package com.company.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * 
 */
@javax.persistence.Entity(name = "RRHH")
@Table(name = "RRHH")
@NamedQueries({
    @NamedQuery(name = "RRHH.findByPersistenceId", query = "SELECT r\nFROM RRHH r\nWHERE r.persistenceId= :persistenceId\n"),
    @NamedQuery(name = "RRHH.findBySolicitanteId", query = "SELECT r\nFROM RRHH r\nWHERE r.solicitanteId= :solicitanteId\nORDER BY r.persistenceId"),
    @NamedQuery(name = "RRHH.findByCalificacionObtenida", query = "SELECT r\nFROM RRHH r\nWHERE r.calificacionObtenida= :calificacionObtenida\nORDER BY r.persistenceId"),
    @NamedQuery(name = "RRHH.findByFechaInicio", query = "SELECT r\nFROM RRHH r\nWHERE r.fechaInicio= :fechaInicio\nORDER BY r.persistenceId"),
    @NamedQuery(name = "RRHH.findByEstaApronado", query = "SELECT r\nFROM RRHH r\nWHERE r.estaApronado= :estaApronado\nORDER BY r.persistenceId"),
    @NamedQuery(name = "RRHH.find", query = "SELECT r\nFROM RRHH r\nORDER BY r.persistenceId"),
    @NamedQuery(name = "RRHH.countForFindBySolicitanteId", query = "SELECT COUNT(r)\nFROM RRHH r\nWHERE r.solicitanteId= :solicitanteId\n"),
    @NamedQuery(name = "RRHH.countForFindByCalificacionObtenida", query = "SELECT COUNT(r)\nFROM RRHH r\nWHERE r.calificacionObtenida= :calificacionObtenida\n"),
    @NamedQuery(name = "RRHH.countForFindByFechaInicio", query = "SELECT COUNT(r)\nFROM RRHH r\nWHERE r.fechaInicio= :fechaInicio\n"),
    @NamedQuery(name = "RRHH.countForFindByEstaApronado", query = "SELECT COUNT(r)\nFROM RRHH r\nWHERE r.estaApronado= :estaApronado\n"),
    @NamedQuery(name = "RRHH.countForFind", query = "SELECT COUNT(r)\nFROM RRHH r\n")
})
public class RRHH implements org.bonitasoft.engine.bdm.Entity
{

    @Id
    @GeneratedValue(generator = "default_bonita_seq_generator")
    @GenericGenerator(name = "default_bonita_seq_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "sequence_name", value = "hibernate_sequence")
    })
    private Long persistenceId;
    @Version
    private Long persistenceVersion;
    @Column(name = "SOLICITANTEID", nullable = false)
    private Long solicitanteId;
    @Column(name = "CALIFICACIONOBTENIDA", nullable = false)
    private Integer calificacionObtenida;
    @Column(name = "FECHAINICIO", nullable = false, length = 10)
    @Convert(converter = org.bonitasoft.engine.business.data.generator.DateConverter.class)
    private LocalDate fechaInicio;
    @Column(name = "ESTAAPRONADO", nullable = true)
    private Boolean estaApronado;

    public RRHH() {
    }

    public void setPersistenceId(Long persistenceId) {
        this.persistenceId = persistenceId;
    }

    public Long getPersistenceId() {
        return persistenceId;
    }

    public void setPersistenceVersion(Long persistenceVersion) {
        this.persistenceVersion = persistenceVersion;
    }

    public Long getPersistenceVersion() {
        return persistenceVersion;
    }

    public void setSolicitanteId(Long solicitanteId) {
        this.solicitanteId = solicitanteId;
    }

    public Long getSolicitanteId() {
        return solicitanteId;
    }

    public void setCalificacionObtenida(Integer calificacionObtenida) {
        this.calificacionObtenida = calificacionObtenida;
    }

    public Integer getCalificacionObtenida() {
        return calificacionObtenida;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setEstaApronado(Boolean estaApronado) {
        this.estaApronado = estaApronado;
    }

    public Boolean isEstaApronado() {
        return estaApronado;
    }

}
