package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = -8479739470487089649L;

	private String idUsuario;
	private String idPerfil;
	private String nombreUsuario;
	private String nombrePerfil;
	private String usuario;
	private String clave;
	private List<MenuDto> listarMenu;

	public UsuarioDto() {
		super();
	}

	public UsuarioDto(String idUsuario, String idPerfil, String nombreUsuario, String nombrePerfil) {
		super();
		this.idUsuario = idUsuario;
		this.idPerfil = idPerfil;
		this.nombreUsuario = nombreUsuario;
		this.nombrePerfil = nombrePerfil;
	}

}
