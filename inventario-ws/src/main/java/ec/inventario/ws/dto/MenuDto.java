package ec.inventario.ws.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuDto implements Serializable {

	private static final long serialVersionUID = -8453759351039466604L;
	private String idMenu;
	private String nombre;
	private String url;

	public MenuDto() {
		super();
	}

	public MenuDto(String idMenu, String nombre, String url) {
		super();
		this.idMenu = idMenu;
		this.nombre = nombre;
		this.url = url;
	}

}
