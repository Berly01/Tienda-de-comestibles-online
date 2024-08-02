
package com.company.model;

import java.util.List;
import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;

public interface ReporteVentasDAO
    extends BusinessObjectDAO
{


    public ReporteVentas findByPersistenceId(Long persistenceId);

    public List<ReporteVentas> findByBuenasVentas(Boolean buenasVentas, int startIndex, int maxResults);

    public List<ReporteVentas> find(int startIndex, int maxResults);

    public Long countForFindByBuenasVentas(Boolean buenasVentas);

    public Long countForFind();

    public ReporteVentas newInstance(Boolean buenasVentas);

}
