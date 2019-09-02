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

import ec.inventario.ws.dao.CatalogoDao;
import ec.inventario.ws.dto.CatalogoDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@RunWith(PowerMockRunner.class)
public class CatalogoServicioTest {

	@InjectMocks
	private CatalogoServicio catalogoServicio;
	@Mock
	private CatalogoDao catalogoDao;
	
	private List<CatalogoDto> catalogos;

	@Before
	public void cargarDatos() {
		catalogos = new ArrayList<>();
		CatalogoDto catalogoDto = new CatalogoDto("1", "COD001", "VALOR", "DESCRIPCION");
		catalogos.add(catalogoDto);
	}

	@Test
	public void testListarTodosCatalogosTipoProductoActivos() throws ServicioExcepcion {
		Mockito.when(catalogoDao.listarCatalogosActivos()).thenReturn(this.catalogos);
		CatalogoDto respuesta = catalogoServicio.listarTodosCatalogosTipoProductoActivos();
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());

	}

}
