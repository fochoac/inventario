import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { VentaTo } from "../modelo/venta-to";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class VentaService {
  constructor(private http: HttpClient) {}

  public guardar(venta: VentaTo): Observable<VentaTo> {
    return this.http.post<VentaTo>(
      "http://localhost:8080/inventario-ws/api/venta/guardar",
      venta
    );
  }
}
