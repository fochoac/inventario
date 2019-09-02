package ec.inventario.ws.dao.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ec.inventario.ws.excepcion.ServicioExcepcion;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConvertirSqlObjectUtil {

	private ConvertirSqlObjectUtil() {
		super();
	}

	public static <T> List<T> retornar(List<Object[]> listaSql, Class clase) throws ServicioExcepcion {
		List listaRetorno = new ArrayList();
		for (Object[] o : listaSql) {
			listaRetorno.add(retornarInstancia(o, clase));
		}
		return listaRetorno;
	}

	private static Object retornarInstancia(Object[] o, Class clase) throws ServicioExcepcion {
		try {

			Object[] oAux = new Object[o.length];
			Class[] clases = new Class[o.length];
			for (int i = 0; i < o.length; i++) {
				clases[i] = String.class;
				String elemento = "";
				if (o[i] != null) {
					elemento = String.valueOf(o[i]);
				}
				oAux[i] = String.valueOf(elemento.trim());
			}
			Constructor c = clase.getConstructor(clases);
			return c.newInstance(oAux);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new ServicioExcepcion(e);
		}
	}
}