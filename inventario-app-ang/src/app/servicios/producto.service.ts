import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ProductoTo } from "../modelo/producto-to";
import { Observable } from "rxjs";
import { BodegaTo } from "../modelo/bodega-to";

@Injectable({
  providedIn: "root"
})
export class ProductoService {
  constructor(private http: HttpClient) {}

  public guardar(producto: ProductoTo): Observable<ProductoTo> {
    return this.http.post<ProductoTo>(
      "http://localhost:8080/inventario-ws/api/producto/guardar",
      producto
    );
  }

  public listarProductosBodega(bodega: BodegaTo): Observable<ProductoTo> {
    return this.http.post<ProductoTo>(
      "http://localhost:8080/inventario-ws/api/producto/listarPorBodega",
      bodega
    );
  }

  public listarTodosProductosActivos(): Observable<ProductoTo> {
    return this.http.post<ProductoTo>(
      "http://localhost:8080/inventario-ws/api/producto/listarProductosActivos",
      null
    );
  }
}
