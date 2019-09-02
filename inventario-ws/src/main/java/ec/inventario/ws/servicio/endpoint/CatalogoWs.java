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

import ec.inventario.ws.dto.CatalogoDto;
import ec.inventario.ws.servicio.CatalogoServicio;

@Path("catalogo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class CatalogoWs {

	@EJB
	private CatalogoServicio catalogoServicio;

	@Lock(LockType.READ)
	@POST
	@Path("listarTipoProducto")
	public CatalogoDto listarTipoProducto() {
		return catalogoServicio.listarTodosCatalogosTipoProductoActivos();
	}

}
