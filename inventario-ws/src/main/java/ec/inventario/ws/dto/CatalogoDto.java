package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CatalogoDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = 5384778441310269600L;
	private String idCatalogo;
	private String codigo;
	private String valor;
	private String descripcion;
	private List<CatalogoDto> listaCatalogos;

	public CatalogoDto() {
		super();
	}

	public CatalogoDto(String idCatalogo, String codigo, String valor, String descripcion) {
		super();
		this.idCatalogo = idCatalogo;
		this.codigo = codigo;
		this.valor = valor;
		this.descripcion = descripcion;
	}

}
