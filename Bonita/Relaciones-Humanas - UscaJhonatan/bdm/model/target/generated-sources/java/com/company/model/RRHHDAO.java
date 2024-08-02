
package com.company.model;

import java.time.LocalDate;
import java.util.List;
import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;

public interface RRHHDAO
    extends BusinessObjectDAO
{


    public RRHH findByPersistenceId(Long persistenceId);

    public List<RRHH> findBySolicitanteId(Long solicitanteId, int startIndex, int maxResults);

    public List<RRHH> findByCalificacionObtenida(Integer calificacionObtenida, int startIndex, int maxResults);

    public List<RRHH> findByFechaInicio(LocalDate fechaInicio, int startIndex, int maxResults);

    public List<RRHH> findByEstaApronado(Boolean estaApronado, int startIndex, int maxResults);

    public List<RRHH> find(int startIndex, int maxResults);

    public Long countForFindBySolicitanteId(Long solicitanteId);

    public Long countForFindByCalificacionObtenida(Integer calificacionObtenida);

    public Long countForFindByFechaInicio(LocalDate fechaInicio);

    public Long countForFindByEstaApronado(Boolean estaApronado);

    public Long countForFind();

    public RRHH newInstance(Long solicitanteId, Integer calificacionObtenida, LocalDate fechaInicio);

}
