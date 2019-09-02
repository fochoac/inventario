package ec.inventario.ws.dao;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.EstablecimientoDto;
import ec.inventario.ws.dto.SucursalDto;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class EstablecimientoDao extends DaoImpl<Object> {

	private static final String SQL_OBTENER_DATOS_ESTABLECIMIENTO = "SELECT char(e.id), e.nombre, e.ruc  from ESTABLECIMIENTO e, ESTABLECIMIENTOXUSUARIO eu where eu.id_establecimiento=e.id and eu.id_usuario=? and eu.estado=true ";
	private static final String SQL_LISTAR_SUCURSALES_ESTABLECIMIENTO = "SELECT char(s.id), s.nombre,s.direccion,s.SUCURSAL,char(s.id_establecimiento) from sucursal s where s.id_establecimiento=?";
	private static final String SQL_LISTAR_BODEGAS_SUCURSAL = "Select char(b.id), b.codigo, b.nombre, char(s.id) from bodega b, sucursal s where b.id_sucursal=s.id  and s.id=?";

	public EstablecimientoDto obtenerDatosEstablecimientoUsuario(Long idUsuario) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_OBTENER_DATOS_ESTABLECIMIENTO);
		query.setParameter(1, idUsuario);
		List<EstablecimientoDto> lista = listarPorSentenciaSql(query, EstablecimientoDto.class);
		if (Objects.isNull(lista) || lista.isEmpty()) {
			return null;
		}
		return lista.get(0);

	}

	public List<SucursalDto> listarSucursalesPorEstablecimiento(Long idEstablecimiento) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_SUCURSALES_ESTABLECIMIENTO);
		query.setParameter(1, idEstablecimiento);
		return listarPorSentenciaSql(query, SucursalDto.class);
	}

	public List<BodegaDto> listarBodegasPorSucursal(Long idSucursal) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_BODEGAS_SUCURSAL);
		query.setParameter(1, idSucursal);
		return listarPorSentenciaSql(query, BodegaDto.class);
	}
}
