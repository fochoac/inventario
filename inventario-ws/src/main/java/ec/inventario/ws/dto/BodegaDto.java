package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BodegaDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = 2112228282989240347L;
	private String idBodega;
	private String codigo;
	private String nombre;
	private String idSucursal;
	private List<BodegaDto> listaBodegas;

	public BodegaDto() {
		super();
	}

	public BodegaDto(String idBodega, String codigo, String nombre, String idSucursal) {
		super();
		this.idBodega = idBodega;
		this.codigo = codigo;
		this.nombre = nombre;
		this.idSucursal = idSucursal;
	}

}
