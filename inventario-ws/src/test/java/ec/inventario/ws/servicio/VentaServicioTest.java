package ec.inventario.ws.servicio;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ec.inventario.ws.dao.VentaDao;
import ec.inventario.ws.dto.VentaDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;
import ec.inventario.ws.modelo.Producto;

@RunWith(PowerMockRunner.class)
public class VentaServicioTest {
	@InjectMocks
	private VentaServicio ventaServicio;
	@Mock
	private VentaDao ventaDao;

	@Mock
	private ProductoServicio productoServicio;

	
	private Producto producto;
	private VentaDto venta;

	@Before
	public void cargarDatos() {
		this.producto = new Producto();
		this.producto.setId(1L);
		this.producto.setCantidad(10);
		this.producto.setPrecioUnitario(BigDecimal.ONE);
		this.venta = new VentaDto();
		venta.setCantidad("5");
		venta.setIdProducto("1");
		venta.setIdUsuario("1");

	}

	@Test
	public void guardarVenta() throws ServicioExcepcion {
		Mockito.when(productoServicio.obtenerProductoPorId(1L)).thenReturn(producto);
		Mockito.doAnswer((i) -> {
			return null;
		}).when(productoServicio).guardarProducto(Mockito.any(Producto.class));
		VentaDto respuesta = ventaServicio.guardarVenta(venta);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

}
