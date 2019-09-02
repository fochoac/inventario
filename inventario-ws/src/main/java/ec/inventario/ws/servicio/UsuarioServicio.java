package ec.inventario.ws.servicio;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.inventario.ws.dao.MenuDao;
import ec.inventario.ws.dao.UsuarioDao;
import ec.inventario.ws.dto.MenuDto;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class UsuarioServicio {

	private static final Logger LOG = Logger.getLogger(UsuarioServicio.class.getName());

	@EJB
	private MenuDao menuDao;

	@EJB
	private UsuarioDao usuarioDao;

	public UsuarioDto cargarDatosUsuario(UsuarioDto usuario) {
		UsuarioDto resultado = new UsuarioDto();
		try {
			List<UsuarioDto> usuarios = usuarioDao.cargarDatosUsuario(usuario.getUsuario(), usuario.getClave());
			if (Objects.isNull(usuario) || usuarios.isEmpty()) {
				resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return resultado;
			}
			UsuarioDto usuarioAutorizado = usuarios.get(0);
			List<MenuDto> listaMenu = menuDao.listarMenuPorPerfil(Long.valueOf(usuarioAutorizado.getIdPerfil()));
			usuarioAutorizado.setListarMenu(listaMenu);
			usuarioAutorizado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return usuarioAutorizado;

		} catch (ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al validar el usuario", e);
			resultado.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
			return resultado;
		}
	}
}
