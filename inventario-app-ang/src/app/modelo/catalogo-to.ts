import { RespuestaTo } from './respuesta-to';

export class CatalogoTo {
  respuesta: RespuestaTo;
  idCatalogo: string;
  codigo: string;
  valor: string;
  descripcion: string;
  listaCatalogos: CatalogoTo[];
}
