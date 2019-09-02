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

import ec.inventario.ws.dao.EstablecimientoDao;
import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.EstablecimientoDto;
import ec.inventario.ws.dto.SucursalDto;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@RunWith(PowerMockRunner.class)
public class EstablecimientoServicioTest {

	@InjectMocks
	private EstablecimientoServicio establecimientoServicio;

	@Mock
	private EstablecimientoDao establecimientoDao;

	private EstablecimientoDto establecimiento;
	private UsuarioDto usuarioDto;
	private SucursalDto sucursalDto;
	private BodegaDto bodega;

	@Before
	public void cargarDatos() {
		this.establecimiento = new EstablecimientoDto("1", "NOMBRE", "RUC");
		this.usuarioDto = new UsuarioDto("1", "1", "Fernando", "Administrador");
		this.sucursalDto = new SucursalDto("1", "MATRIZ", "", "001", "1");
		this.bodega = new BodegaDto("1", "001", "BODEGA 001", "1");
	}

	@Test
	public void testObtenerDatosEstablecimiento() throws ServicioExcepcion {
		Mockito.when(establecimientoDao.obtenerDatosEstablecimientoUsuario(1L)).thenReturn(establecimiento);
		EstablecimientoDto respuesta = establecimientoServicio.obtenerDatosEstablecimiento(usuarioDto);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

	@Test
	public void testListarSucursalesPorEstablecimiento() throws ServicioExcepcion {
		List<SucursalDto> sucursales = new ArrayList<>();
		sucursales.add(this.sucursalDto);
		Mockito.when(establecimientoDao.listarSucursalesPorEstablecimiento(1L)).thenReturn(sucursales);
		SucursalDto respuesta = establecimientoServicio.listarSucursalesPorEstablecimiento(establecimiento);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

	@Test
	public void testListarBodegasPorSucursal() throws ServicioExcepcion {
		List<BodegaDto> bodegas = new ArrayList<>();
		bodegas.add(this.bodega);
		Mockito.when(establecimientoDao.listarBodegasPorSucursal(1L)).thenReturn(bodegas);
		BodegaDto respuesta = establecimientoServicio.listarBodegasPorSucursal(sucursalDto);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

}
