package ec.inventario.ws.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.dto.MenuDto;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class MenuDao extends DaoImpl<Object> {

	private static final String SQL_LISTAR_MENU_USUARIO = "Select char(m.id), m.nombre, m.url from Menu m, PerfilXMenu pm "
			+ " where m.id = pm.id_menu and pm.id_perfil=? and pm.estado=true";

	public List<MenuDto> listarMenuPorPerfil(Long idPerfil) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_MENU_USUARIO);
		query.setParameter(1, idPerfil);
		return listarPorSentenciaSql(query, MenuDto.class);
	}
}
