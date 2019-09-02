
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UsuarioTo } from '../modelo/usuario-to';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public validarUsuario(usuario: UsuarioTo): Observable<UsuarioTo> {

    return this.http.post<UsuarioTo>("http://localhost:8080/inventario-ws/api/usuario/validar", usuario);
  }
}
