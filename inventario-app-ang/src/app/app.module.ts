import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { PanelModule } from "primeng/panel";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TabMenuModule } from "primeng/tabmenu";
import { LoginComponent } from "./login/login.component";
import { CardModule } from "primeng/card";
import { PasswordModule } from "primeng/password";
import { InputTextModule } from "primeng/inputtext";
import { FormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { ToastModule } from "primeng/toast";
import { MessageService, ConfirmationService } from "primeng/api";
import { HttpClientModule } from "@angular/common/http";

import { PanelMenuModule } from "primeng/panelmenu";
import { GestionarInventarioComponent } from "./adm/gestionar-inventario/gestionar-inventario.component";
import { VistaUsuarioComponent } from "./usuario/vista-usuario/vista-usuario.component";
import { TabViewModule } from "primeng/tabview";
import { FieldsetModule } from "primeng/fieldset";
import { DropdownModule } from "primeng/dropdown";
import { TableModule } from "primeng/table";
import { DialogModule } from "primeng/dialog";
import { MessagesModule } from "primeng/messages";
import { MessageModule } from "primeng/message";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { DataViewModule } from "primeng/dataview";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    GestionarInventarioComponent,
    VistaUsuarioComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    PanelModule,
    BrowserAnimationsModule,
    TabMenuModule,
    CardModule,
    PasswordModule,
    InputTextModule,
    ButtonModule,
    ToastModule,
    HttpClientModule,
    PanelMenuModule,
    TabViewModule,
    FieldsetModule,
    DropdownModule,
    TableModule,
    DialogModule,
    MessageModule,
    MessagesModule,
    ConfirmDialogModule,
    DataViewModule
  ],
  providers: [MessageService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule {}
