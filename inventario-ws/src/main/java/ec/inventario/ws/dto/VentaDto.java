package ec.inventario.ws.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VentaDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = -5263100390472079223L;
	private String idVenta;
	private String idProducto;
	private String idUsuario;
	private String cantidad;
	private String valorTotal;
}
