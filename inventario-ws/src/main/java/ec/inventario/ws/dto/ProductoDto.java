package ec.inventario.ws.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductoDto extends RespuestaDto implements Serializable {

	private static final long serialVersionUID = -3650176407991005642L;

	private String id;
	private String codigo;
	private String marca;
	private String nombre;
	private String descripcion;
	private String imagenUrl;
	private String cantidad;
	private String estado;
	private String precioUnitario;
	private CatalogoDto tipo;
	private String idBodega;
	private List<ProductoDto> listaProductos;

	public ProductoDto() {
		super();
	}

	public ProductoDto(String id, String codigo, String marca, String nombre, String descripcion, String imagenUrl,
			String cantidad, String precioUnitario, String idCatalogo, String codigoCatalogo, String valorCatalogo,
			String descripcionCatalogo, String estado, String idBodega) {

		this.id = id;
		this.codigo = codigo;
		this.marca = marca;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagenUrl = imagenUrl;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.estado = estado;
		this.idBodega = idBodega;
		this.tipo = new CatalogoDto(idCatalogo, codigoCatalogo, valorCatalogo, descripcionCatalogo);
	}

}
