package ec.inventario.ws.enumeracion;

import ec.inventario.ws.dto.RespuestaDto;
import lombok.Getter;

public enum CodigoRetornoEnum {
	CORRECTO("1", "OK"), NO_EXISTE_INFORMACION("2", "NO EXISTE INFORMACIÓN DISPONIBLE"),
	ERROR("3", "ERROR EN EL SISTEMA. INTENTE NUEVAMENTE MÁS TARDE");

	@Getter
	private String codigo;
	@Getter
	private String mensaje;

	private CodigoRetornoEnum(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public static RespuestaDto obtenerRespuestaCorrecta() {
		return new RespuestaDto(CORRECTO.codigo, CORRECTO.mensaje);
	}

	public static RespuestaDto obtenerRespuestaInformacionNoExiste() {
		return new RespuestaDto(NO_EXISTE_INFORMACION.codigo, NO_EXISTE_INFORMACION.mensaje);
	}

	public static RespuestaDto obtenerRespuestaErrorSistema() {
		return new RespuestaDto(ERROR.codigo, ERROR.mensaje);
	}

}
