package ec.inventario.ws.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.dto.ProductoDto;
import ec.inventario.ws.excepcion.ServicioExcepcion;
import ec.inventario.ws.modelo.Producto;

@Stateless
public class ProductoDao extends DaoImpl<Producto> {

	private static final String SQL_LISTAR_PRODUCTOS_BODEGA = "Select char(p.id), p.codigo, p.marca, p.nombre, p.descripcion, p.imagen_url, char(p.cantidad),char(p.precio_unitario), char(c.id), c.codigo, c.valor, c.descripcion, char(p.estado), char(b.id)  "
			+ " from BODEGA b, PRODUCTO p, Catalogo c  WHERE b.id =p.id_bodega and c.id=p.id_catalogo_tipo and c.estado=true and b.id=? AND P.ESTADO=true";
	public static final String SQL_LISTAR_TODOS_PRODUCTOS_ACTIVOS = "Select char(p.id), p.codigo, p.marca, p.nombre, p.descripcion, p.imagen_url, char(p.cantidad),char(p.precio_unitario), char(c.id), c.codigo, c.valor, c.descripcion, char(p.estado), char(p.id_BODEGA)  "
			+ " from PRODUCTO p, Catalogo c  WHERE  c.id=p.id_catalogo_tipo and c.estado=true  AND P.ESTADO=true";

	public ProductoDao() {
		super(Producto.class);
	}

	public List<ProductoDto> listarProductosBodega(Long idBodega) throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_PRODUCTOS_BODEGA);
		query.setParameter(1, idBodega);
		return listarPorSentenciaSql(query, ProductoDto.class);

	}

	public List<ProductoDto> listarProductosActivos() throws ServicioExcepcion {
		Query query = getEntityManager().createNativeQuery(SQL_LISTAR_TODOS_PRODUCTOS_ACTIVOS);
		return listarPorSentenciaSql(query, ProductoDto.class);

	}

}
