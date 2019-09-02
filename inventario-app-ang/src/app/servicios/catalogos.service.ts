import { Injectable } from "@angular/core";
import { CatalogoTo } from "../modelo/catalogo-to";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class CatalogosService {
  constructor(private http: HttpClient) {}

  public listarTipoProductoActivos(): Observable<CatalogoTo> {
    return this.http.post<CatalogoTo>(
      "http://localhost:8080/inventario-ws/api/catalogo/listarTipoProducto",
      null
    );
  }
}
