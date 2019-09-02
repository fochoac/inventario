package ec.inventario.ws.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.dto.CatalogoDto;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class CatalogoDao extends DaoImpl<Object> {

	private static final String SQL_LISTAR_CATALOGOS_ACTIVOS = "Select char(c.id),c.codigo,c.valor,c.descripcion from catalogo c where c.estado=true ";



	public List<CatalogoDto> listarCatalogosActivos() throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_CATALOGOS_ACTIVOS);
		return listarPorSentenciaSql(query, CatalogoDto.class);
	}

}
