package ec.inventario.ws.dao;

import javax.ejb.Stateless;

import ec.inventario.ws.dao.impl.DaoImpl;
import ec.inventario.ws.modelo.Venta;

@Stateless
public class VentaDao extends DaoImpl<Venta> {

	public VentaDao() {
		super(Venta.class);
	}
}
