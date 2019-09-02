package ec.inventario.ws.excepcion;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ServicioExcepcion extends Exception {

	private static final long serialVersionUID = 8684461033745908340L;

	public ServicioExcepcion() {
		super();

	}

	public ServicioExcepcion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ServicioExcepcion(String message, Throwable cause) {
		super(message, cause);

	}

	public ServicioExcepcion(String message) {
		super(message);

	}

	public ServicioExcepcion(Throwable cause) {
		super(cause);

	}

}
