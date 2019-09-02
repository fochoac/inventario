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

import ec.inventario.ws.dao.MenuDao;
import ec.inventario.ws.dao.UsuarioDao;
import ec.inventario.ws.dto.MenuDto;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@RunWith(PowerMockRunner.class)
public class UsuarioServicioTest {

	@InjectMocks
	private UsuarioServicio usuarioServicio;

	@Mock
	private MenuDao menuDao;

	@Mock
	private UsuarioDao usuarioDao;
	private UsuarioDto usuarioDto;
	private MenuDto menu;

	@Before
	public void cargarDatos() {

		this.usuarioDto = new UsuarioDto("1", "1", "Fernando", "Administrador");
		this.menu = new MenuDto("1", "pPRUEBA", "URL");
	}

	@Test
	public void testCargarDatosUsuario() throws ServicioExcepcion {
		List<UsuarioDto> usuarios = new ArrayList<>();
		usuarios.add(usuarioDto);
		Mockito.when(usuarioDao.cargarDatosUsuario(Mockito.anyString(), Mockito.anyString())).thenReturn(usuarios);
		List<MenuDto> listaMenu = new ArrayList<MenuDto>();
		listaMenu.add(menu);
		Mockito.when(menuDao.listarMenuPorPerfil(1L)).thenReturn(listaMenu);
		UsuarioDto respuesta = usuarioServicio.cargarDatosUsuario(usuarioDto);
		assertEquals(CodigoRetornoEnum.CORRECTO.getCodigo(), respuesta.getRespuesta().getCodigo());
	}

}
