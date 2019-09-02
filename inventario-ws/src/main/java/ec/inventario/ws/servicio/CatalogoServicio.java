package ec.inventario.ws.servicio;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.inventario.ws.dao.CatalogoDao;
import ec.inventario.ws.dto.CatalogoDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class CatalogoServicio {

	private static final Logger LOG = Logger.getLogger(CatalogoServicio.class.getName());

	@EJB
	private CatalogoDao catalogoDao;

	public CatalogoDto listarTodosCatalogosTipoProductoActivos() {
		CatalogoDto respuesta = new CatalogoDto();
		try {
			List<CatalogoDto> catalogos = catalogoDao.listarCatalogosActivos();
			if (Objects.isNull(catalogos) || catalogos.isEmpty()) {
				respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return respuesta;
			}
			respuesta.setListaCatalogos(catalogos);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
		} catch (ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al obtener los datos del establecimiento", e);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
		}
		return respuesta;

	}

}
