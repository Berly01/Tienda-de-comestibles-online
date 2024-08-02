
package com.company.model;

import java.util.List;
import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;

public interface PublicacionesDAO
    extends BusinessObjectDAO
{


    public Publicaciones findByPersistenceId(Long persistenceId);

    public List<Publicaciones> findByPost(String post, int startIndex, int maxResults);

    public List<Publicaciones> find(int startIndex, int maxResults);

    public Long countForFindByPost(String post);

    public Long countForFind();

    public Publicaciones newInstance(String post);

}
