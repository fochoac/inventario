package ec.inventario.ws.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class UsuarioDao extends DaoImpl<Object> {

	private static final String SQL_VALIDAR_USUARIO = "Select char(u.id), char(p.id), u.nombre, p.nombre  from Usuario u, Perfil p, PerfilXUsuario pu "
			+ " where pu.id_usuario=u.id and pu.id_perfil=p.id and pu.estado=true and u.usuario=? and u.clave=?";

	public List<UsuarioDto> cargarDatosUsuario(String usuario, String clave) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_VALIDAR_USUARIO);
		query.setParameter(1, usuario);
		query.setParameter(2, clave);
		return listarPorSentenciaSql(query, UsuarioDto.class);
	}
}
