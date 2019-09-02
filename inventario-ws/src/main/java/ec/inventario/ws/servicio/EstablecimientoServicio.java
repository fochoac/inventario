package ec.inventario.ws.servicio;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.inventario.ws.dao.EstablecimientoDao;
import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.EstablecimientoDto;
import ec.inventario.ws.dto.SucursalDto;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.enumeracion.CodigoRetornoEnum;
import ec.inventario.ws.excepcion.ServicioExcepcion;

@Stateless
public class EstablecimientoServicio {
	private static final Logger LOG = Logger.getLogger(EstablecimientoServicio.class.getName());
	
	@EJB
	private EstablecimientoDao establecimientoDao;

	public EstablecimientoDto obtenerDatosEstablecimiento(UsuarioDto usuarioDto) {
		EstablecimientoDto respuesta = new EstablecimientoDto();
		try {
			EstablecimientoDto datos = establecimientoDao
					.obtenerDatosEstablecimientoUsuario(Long.valueOf(usuarioDto.getIdUsuario()));
			if (Objects.isNull(datos)) {
				respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return respuesta;
			}
			datos.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return datos;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al obtener los datos del establecimiento", e);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
		}
		return respuesta;
	}

	public SucursalDto listarSucursalesPorEstablecimiento(EstablecimientoDto establecimientoDto) {
		SucursalDto respuesta = new SucursalDto();
		try {
			List<SucursalDto> sucursales = establecimientoDao
					.listarSucursalesPorEstablecimiento(Long.valueOf(establecimientoDto.getIdEstablecimiento()));
			if (Objects.isNull(sucursales) || sucursales.isEmpty()) {
				respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return respuesta;
			}
			respuesta.setListaSucursales(sucursales);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return respuesta;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al obtener los datos del establecimiento", e);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
		}
		return respuesta;
	}

	public BodegaDto listarBodegasPorSucursal(SucursalDto sucursalDto) {
		BodegaDto respuesta = new BodegaDto();
		try {
			List<BodegaDto> bodegas = establecimientoDao
					.listarBodegasPorSucursal(Long.valueOf(sucursalDto.getIdSucursal()));
			if (Objects.isNull(bodegas) || bodegas.isEmpty()) {
				respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaInformacionNoExiste());
				return respuesta;
			}
			respuesta.setListaBodegas(bodegas);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaCorrecta());
			return respuesta;
		} catch (NumberFormatException | ServicioExcepcion e) {
			LOG.log(Level.SEVERE, "Error al obtener los datos de bodegas de la sucursalo", e);
			respuesta.setRespuesta(CodigoRetornoEnum.obtenerRespuestaErrorSistema());
		}
		return respuesta;
	}
}
