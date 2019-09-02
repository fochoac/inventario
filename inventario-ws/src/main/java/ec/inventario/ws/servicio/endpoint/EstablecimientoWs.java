package ec.inventario.ws.servicio.endpoint;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.EstablecimientoDto;
import ec.inventario.ws.dto.SucursalDto;
import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.servicio.EstablecimientoServicio;

@Path("establecimiento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class EstablecimientoWs {

	@EJB
	private EstablecimientoServicio establecimientoServicio;

	@Lock(LockType.READ)
	@POST
	@Path("informacion")
	public EstablecimientoDto obtenerInformacionEstablecimiento(UsuarioDto usuario) {
		return establecimientoServicio.obtenerDatosEstablecimiento(usuario);
	}

	@Lock(LockType.READ)
	@POST
	@Path("listarSucursales")
	public SucursalDto listarSucursales(EstablecimientoDto establecimientoDto) {
		return establecimientoServicio.listarSucursalesPorEstablecimiento(establecimientoDto);
	}

	@Lock(LockType.READ)
	@POST
	@Path("listarBodegas")
	public BodegaDto listarBodegas(SucursalDto sucursalDto) {
		return establecimientoServicio.listarBodegasPorSucursal(sucursalDto);
	}
}
