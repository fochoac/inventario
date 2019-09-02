package ec.inventario.ws.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity implementation class for Entity: PRODUCTO
 *
 */
@Entity
@Table(name = "PRODUCTO")
@Data
public class Producto {

	@Id
	@SequenceGenerator(name = "SEQ_ID_PRODUCTO", sequenceName = "ID_PRODUCTO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PRODUCTO")
	private Long id;

	private String codigo;
	private String marca;
	private String nombre;
	private String descripcion;
	@Column(name = "IMAGEN_URL")
	private String imagenUrl;
	private int cantidad;
	@Column(name = "PRECIO_UNITARIO")
	private BigDecimal precioUnitario;
	@Column(name = "ID_CATALOGO_TIPO")
	private Long idCatalogoTipo;
	@Column(name = "ID_BODEGA")
	private Long idBodega;
	private boolean estado;

}
