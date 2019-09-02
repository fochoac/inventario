import { CatalogoTo } from "./catalogo-to";
import { RespuestaTo } from "./respuesta-to";

export class ProductoTo {
  respuesta: RespuestaTo;
   id:string;
  codigo: string;
  marca: string;
  nombre: string;
  descripcion: string;
  imagenUrl: string;
  cantidad: string;
  tipoDescripcion: string;
  precioUnitario: string;
  estado: string;
  tipo: CatalogoTo;
  idBodega: string;
  listaProductos: ProductoTo[];
}
