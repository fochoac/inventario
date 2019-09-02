package ec.inventario.ws.servicio;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import ec.inventario.ws.dao.ProductoDao;
import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.ProductoDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;
import ec.inventario.ws.modelo.Producto;

@RunWith(PowerMockRunner.class)
public class ProductoServicioTest {

	@InjectMocks
	private ProductoServicio productoServicio;

	@Mock
	private ProductoDao productoDao;

	private ProductoDto producto;

	private BodegaDto bodega;

	@Before
	public void cargarDatos() {
		this.producto = new ProductoDto();
		this.producto.setId("1");

		this.bodega = new BodegaDto("1", "001", "BODEGA 001", "1");
	}

	@Test
	public void testGuardarProductoProducto() throws ServicioExcepcion {
		Mockito.doAnswer((i) -> {
			return null;
		}).when(productoDao).guardar(Mockito.any(Producto.class));
	}

	@Test
	public void testObtenerProductoPorId() {
		Producto entidad = new Producto();
		entidad.setId(1L);
		Mockito.when(productoDao.obtenerPorLlavePrimaria(1L)).thenReturn(entidad);
		assertEquals("1", entidad.getId().toString());
	}

	@Test
	public void testListarProductosPorBodega() throws ServicioExcepcion {
		List<ProductoDto> lista = new ArrayList<>();
		lista.add(producto);
		Mockito.when(productoDao.listarProductosBodega(1L)).thenReturn(lista);
		ProductoDto respuesta = productoServicio.listarProductosPorBodega(bodega);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

	@Test
	public void testListarProductosActivos() throws ServicioExcepcion {
		List<ProductoDto> lista = new ArrayList<>();
		lista.add(producto);
		Mockito.when(productoDao.listarProductosActivos()).thenReturn(lista);
		ProductoDto respuesta = productoServicio.listarProductosActivos();
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

}
