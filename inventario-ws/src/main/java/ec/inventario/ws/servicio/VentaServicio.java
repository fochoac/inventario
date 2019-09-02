package ec.inventario.ws.servicio;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.inventario.ws.dao.VentaDao;
import ec.inventario.ws.dto.RespuestaDto;
import ec.inventario.ws.dto.VentaDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;
import ec.inventario.ws.modelo.Producto;
import ec.inventario.ws.modelo.Venta;

@Stateless
public class VentaServicio {

	private static final Logger LOG = Logger.getLogger(VentaServicio.class.getName());

	@EJB
	private VentaDao ventaDao;

	@EJB
	private ProductoServicio productoServicio;

	public VentaDto guardarVenta(VentaDto ventaDto) {
		VentaDto resultado = new VentaDto();
		try {
			Producto producto = productoServicio.obtenerProductoPorId(Long.valueOf(ventaDto.getIdProducto()));
			final int cantidadComprada = Integer.parseInt(ventaDto.getCantidad());
			if (producto.getCantidad() >= cantidadComprada) {
				producto.setCantidad(producto.getCantidad() - cantidadComprada);
			} else {
				RespuestaDto r = new RespuestaDto();
				r.setCodigo(CodigoRetornoEnum.ERROR.getCodigo());
				r.setMensaje("La cantidad seleccionada no existe en stock");
				resultado.setRespuesta(r);
				return resultado;
			}
			Venta venta = new Venta();
			venta.setCantidad(cantidadComprada);
			venta.setIdProducto(producto.getId());
			venta.setIdUsuario(Long.valueOf(ventaDto.getIdUsuario()));
			BigDecimal valor = producto.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidadComprada));
			valor.setScale(2);
			venta.setValorTotal(valor);
			productoServicio.guardarProducto(producto);
			ventaDao.guardar(venta);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return resultado;
		} catch (ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al ejecutar la compra", e);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
			return resultado;
		}
	}
}
