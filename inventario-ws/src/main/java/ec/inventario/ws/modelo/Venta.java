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
 * Entity implementation class for Entity: VENTA
 *
 */
@Entity
@Table(name = "VENTA")
@Data
public class Venta {

	@Id
	@SequenceGenerator(name = "SEQ_ID_VENTA", sequenceName = "ID_VENTA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_VENTA")
	private Long id;
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;
	private int cantidad;
	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotal;

}
