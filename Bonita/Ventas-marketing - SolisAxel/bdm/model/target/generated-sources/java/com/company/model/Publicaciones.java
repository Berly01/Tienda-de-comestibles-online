
package com.company.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * 
 */
@javax.persistence.Entity(name = "Publicaciones")
@Table(name = "PUBLICACIONES")
@NamedQueries({
    @NamedQuery(name = "Publicaciones.findByPersistenceId", query = "SELECT p\nFROM Publicaciones p\nWHERE p.persistenceId= :persistenceId\n"),
    @NamedQuery(name = "Publicaciones.findByPost", query = "SELECT p\nFROM Publicaciones p\nWHERE p.post= :post\nORDER BY p.persistenceId"),
    @NamedQuery(name = "Publicaciones.find", query = "SELECT p\nFROM Publicaciones p\nORDER BY p.persistenceId"),
    @NamedQuery(name = "Publicaciones.countForFindByPost", query = "SELECT COUNT(p)\nFROM Publicaciones p\nWHERE p.post= :post\n"),
    @NamedQuery(name = "Publicaciones.countForFind", query = "SELECT COUNT(p)\nFROM Publicaciones p\n")
})
public class Publicaciones implements org.bonitasoft.engine.bdm.Entity
{

    @Id
    @GeneratedValue(generator = "default_bonita_seq_generator")
    @GenericGenerator(name = "default_bonita_seq_generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "sequence_name", value = "hibernate_sequence")
    })
    private Long persistenceId;
    @Version
    private Long persistenceVersion;
    @Column(name = "POST", nullable = false)
    @Lob
    private String post;

    public Publicaciones() {
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

    public void setPost(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

}
