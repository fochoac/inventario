import { MenuTo } from './menu-to';
import { RespuestaTo } from './respuesta-to';
export class UsuarioTo {
  respuesta: RespuestaTo;
  idUsuario: string;
  idPerfil: string;
  nombreUsuario: string;
  nombrePerfil: string;
  usuario: string;
  clave: string;
  listarMenu: MenuTo[];
}
