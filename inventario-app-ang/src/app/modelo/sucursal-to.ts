import { RespuestaTo } from './respuesta-to';

export class SucursalTo {
  respuesta: RespuestaTo;
  idSucursal: string;
  nombre: string;
  direccion: string;
  codigoSRI: string;
  idEstablecimiento: string;
  listaSucursales: SucursalTo[];
}
