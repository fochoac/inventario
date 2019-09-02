package ec.inventario.ws.servicio.endpoint;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.inventario.ws.dto.UsuarioDto;
import ec.inventario.ws.servicio.UsuarioServicio;

@Path("usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class UsuarioWs {
    @EJB
    private UsuarioServicio usuarioServicio;

    @POST
    @Path("validar")
    public UsuarioDto validarUsuario(UsuarioDto usuario) {
        return usuarioServicio.cargarDatosUsuario(usuario);
    }
}
