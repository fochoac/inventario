package ec.inventario.ws.servicio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.inventario.ws.dao.ProductoDao;
import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.ProductoDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;
import ec.inventario.ws.modelo.Producto;

@Stateless
public class ProductoServicio {

	private static final Logger LOG = Logger.getLogger(ProductoServicio.class.getName());

	@EJB
	private ProductoDao productoDao;

	public void guardarProducto(Producto producto) throws ServicioExcepcion {
		productoDao.guardar(producto);
	}

	public Producto obtenerProductoPorId(Long idProducto) {
		return productoDao.obtenerPorLlavePrimaria(idProducto);
	}

	public ProductoDto listarProductosPorBodega(BodegaDto bodegaDto) {
		ProductoDto resultado = new ProductoDto();
		try {
			List<ProductoDto> productos = productoDao.listarProductosBodega(Long.valueOf(bodegaDto.getIdBodega()));
			if (Objects.isNull(productos) || productos.isEmpty()) {
				resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return resultado;
			}
			resultado.setListaProductos(productos);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return resultado;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al listar los productos", e);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
			return resultado;
		}
	}

	public ProductoDto listarProductosActivos() {
		ProductoDto resultado = new ProductoDto();
		try {
			List<ProductoDto> productos = productoDao.listarProductosActivos();
			if (Objects.isNull(productos) || productos.isEmpty()) {
				resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return resultado;
			}
			resultado.setListaProductos(productos);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return resultado;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al listar los productos", e);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
			return resultado;
		}
	}

	public ProductoDto guardarProducto(ProductoDto productoDto) {
		ProductoDto resultado = new ProductoDto();
		try {
			if (Objects.isNull(productoDto.getId()) || productoDto.getId().isEmpty()) {

				guardarProducto(new Producto(), productoDto);
			} else {
				Producto producto = productoDao.obtenerPorLlavePrimaria(Long.valueOf(productoDto.getId()));
				guardarProducto(producto, productoDto);
			}

			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return resultado;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al guardar el producto", e);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
			return resultado;
		}
	}

	private void guardarProducto(Producto producto, ProductoDto productoDto) throws ServicioExcepcion {
		producto.setEstado(Boolean.valueOf(productoDto.getEstado()));
		producto.setCantidad(Integer.parseInt(productoDto.getCantidad()));
		producto.setCodigo(productoDto.getCodigo());
		producto.setDescripcion(productoDto.getDescripcion());
		producto.setIdBodega(Long.valueOf(productoDto.getIdBodega()));
		producto.setIdCatalogoTipo(Long.valueOf(productoDto.getTipo().getIdCatalogo()));
		producto.setImagenUrl(productoDto.getImagenUrl());
		producto.setMarca(productoDto.getMarca());
		producto.setNombre(productoDto.getNombre());
		BigDecimal precio = new BigDecimal(productoDto.getPrecioUnitario());
		precio.setScale(2);
		producto.setPrecioUnitario(precio);
		productoDao.guardar(producto);
	}

}
