import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UsuarioTo } from "../modelo/usuario-to";
import { EstablecimientoTo } from "../modelo/establecimiento-to";
import { SucursalTo } from "../modelo/sucursal-to";
import { BodegaTo } from "../modelo/bodega-to";
import { ProductoTo } from "../modelo/producto-to";

@Injectable({
  providedIn: "root"
})
export class GestionInventarioService {
  establecimiento: EstablecimientoTo;
  sucursales: SucursalTo[];
  constructor(private http: HttpClient) {}

  public obtenerInformacionEstablecimiento(
    usuario: UsuarioTo
  ): Observable<EstablecimientoTo> {
    return this.http.post<EstablecimientoTo>(
      "http://localhost:8080/inventario-ws/api/establecimiento/informacion",
      usuario
    );
  }

  public listarSucursales(
    establecimiento: EstablecimientoTo
  ): Observable<SucursalTo> {
    return this.http.post<SucursalTo>(
      "http://localhost:8080/inventario-ws/api/establecimiento/listarSucursales",
      establecimiento
    );
  }

  public listarBodegas(sucursal: SucursalTo): Observable<BodegaTo> {
    return this.http.post<BodegaTo>(
      "http://localhost:8080/inventario-ws/api/establecimiento/listarBodegas",
      sucursal
    );
  }
}
