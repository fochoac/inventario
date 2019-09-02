import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { MessageService } from "primeng/api";
import { LoginService } from "../servicios/login.service";
import { UsuarioTo } from "../modelo/usuario-to";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  usuario: string;
  clave: string;
  constructor(
    private messageService: MessageService,
    private loginService: LoginService,
    private route: Router
  ) {}

  ngOnInit() {}

  ingresar() {
    if (!this.usuario || !this.clave) {
      this.messageService.add({
        severity: "error",
        summary: "Error",
        detail: "Ingrese los campos obligatorios"
      });
      return;
    }
    const usuarioTo = new UsuarioTo();
    usuarioTo.usuario = this.usuario;
    usuarioTo.clave = this.clave;
    this.loginService.validarUsuario(usuarioTo).subscribe(
      respuesta => {
        if (respuesta.respuesta.codigo == "1") {
          sessionStorage.setItem("usuario", JSON.stringify(respuesta));
          if (respuesta.nombrePerfil == "Administrador") {
            this.route.navigateByUrl("/administrador");
          } else {
            this.route.navigateByUrl("/usuario");
          }
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Información",
            detail: "Usuario o clave incorrecta"
          });
        }
      },
      error => {
        this.messageService.add({
          severity: "error",
          summary: "Error",
          detail: error
        });
      }
    );
  }
}
