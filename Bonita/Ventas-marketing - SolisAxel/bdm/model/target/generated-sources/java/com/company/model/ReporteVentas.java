
package com.company.model;

import javax.persistence.Column;
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
@javax.persistence.Entity(name = "ReporteVentas")
@Table(name = "REPORTEVENTAS")
@NamedQueries({
    @NamedQuery(name = "ReporteVentas.findByPersistenceId", query = "SELECT r\nFROM ReporteVentas r\nWHERE r.persistenceId= :persistenceId\n"),
    @NamedQuery(name = "ReporteVentas.findByBuenasVentas", query = "SELECT r\nFROM ReporteVentas r\nWHERE r.buenasVentas= :buenasVentas\nORDER BY r.persistenceId"),
    @NamedQuery(name = "ReporteVentas.find", query = "SELECT r\nFROM ReporteVentas r\nORDER BY r.persistenceId"),
    @NamedQuery(name = "ReporteVentas.countForFindByBuenasVentas", query = "SELECT COUNT(r)\nFROM ReporteVentas r\nWHERE r.buenasVentas= :buenasVentas\n"),
    @NamedQuery(name = "ReporteVentas.countForFind", query = "SELECT COUNT(r)\nFROM ReporteVentas r\n")
})
public class ReporteVentas implements org.bonitasoft.engine.bdm.Entity
{

    @Id
    @GeneratedValue(generator = "default_bonita_seq_generator")
    @GenericGenerator(name = "default_bonita_seq_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "sequence_name", value = "hibernate_sequence")
    })
    private Long persistenceId;
    @Version
    private Long persistenceVersion;
    @Column(name = "BUENASVENTAS", nullable = false)
    private Boolean buenasVentas;

    public ReporteVentas() {
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

    public void setBuenasVentas(Boolean buenasVentas) {
        this.buenasVentas = buenasVentas;
    }

    public Boolean isBuenasVentas() {
        return buenasVentas;
    }

}
