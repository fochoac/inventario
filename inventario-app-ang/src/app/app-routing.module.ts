import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { GestionarInventarioComponent } from "./adm/gestionar-inventario/gestionar-inventario.component";
import { VistaUsuarioComponent } from "./usuario/vista-usuario/vista-usuario.component";

const routes: Routes = [
  { path: "", component: LoginComponent },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "administrador",
    component: GestionarInventarioComponent
  },
  {
    path: "usuario",
    component: VistaUsuarioComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
