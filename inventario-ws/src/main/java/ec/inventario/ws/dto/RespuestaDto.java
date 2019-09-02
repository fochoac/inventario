package ec.inventario.ws.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RespuestaDto implements Serializable {

	private static final long serialVersionUID = -4368466313514906799L;

	private String codigo;

	private String mensaje;

	@Getter
	@Setter
	protected RespuestaDto respuesta;

	public RespuestaDto() {
		super();
	}

	public RespuestaDto(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

}
