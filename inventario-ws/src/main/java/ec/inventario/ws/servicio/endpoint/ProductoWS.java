package ec.inventario.ws.servicio.endpoint;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.inventario.ws.dto.BodegaDto;
import ec.inventario.ws.dto.ProductoDto;
import ec.inventario.ws.servicio.ProductoServicio;

@Path("producto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ProductoWS {

	@EJB
	private ProductoServicio productoServicio;

	@POST
	@Path("listarPorBodega")
	public ProductoDto listarProductosBodega(BodegaDto bodegaTo) {
		return productoServicio.listarProductosPorBodega(bodegaTo);
	}

	@POST
	@Path("listarProductosActivos")
	public ProductoDto listarProductosBodega() {
		return productoServicio.listarProductosActivos();
	}

	@POST
	@Path("guardar")
	public ProductoDto guardar(ProductoDto producto) {
		return productoServicio.guardarProducto(producto);
	}
}
