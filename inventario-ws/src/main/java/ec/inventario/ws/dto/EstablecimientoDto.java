package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EstablecimientoDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = -6659658146497949082L;

	private String idEstablecimiento;
	private String nombre;
	private String ruc;
	private List<SucursalDto> sucursales;

	public EstablecimientoDto() {
		super();
	}

	public EstablecimientoDto(String idEstablecimiento, String nombre, String ruc) {
		super();
		this.idEstablecimiento = idEstablecimiento;
		this.nombre = nombre;
		this.ruc = ruc;
	}

}
