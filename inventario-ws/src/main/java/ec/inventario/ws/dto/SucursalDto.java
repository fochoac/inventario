package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SucursalDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = -8233696991529712957L;

	private String idSucursal;

	private String nombre;
	private String direccion;
	private String codigoSRI;
	private String idEstablecimiento;
	private List<SucursalDto> listaSucursales;

	public SucursalDto() {
		super();
	}

	public SucursalDto(String idSucursal, String nombre, String direccion, String codigoSRI, String idEstablecimiento) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigoSRI = codigoSRI;
		this.idEstablecimiento = idEstablecimiento;
	}

}
