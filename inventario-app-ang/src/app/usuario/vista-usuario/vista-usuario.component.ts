import { Component, OnInit } from "@angular/core";
import { UsuarioTo } from "src/app/modelo/usuario-to";
import { Router } from "@angular/router";
import { ProductoService } from "src/app/servicios/producto.service";
import { VentaService } from "src/app/servicios/venta.service";
import { ProductoTo } from "src/app/modelo/producto-to";
import { MessageService } from "primeng/api";
import { VentaTo } from "src/app/modelo/venta-to";

@Component({
  selector: "app-vista-usuario",
  templateUrl: "./vista-usuario.component.html",
  styleUrls: ["./vista-usuario.component.css"]
})
export class VistaUsuarioComponent implements OnInit {
  mostrarDialogo = false;
  usuario: UsuarioTo;
  productoSeleccionado: ProductoTo;
  productos: ProductoTo[];
  venta: VentaTo;
  montoTotal = 0;
  constructor(
    private messageService: MessageService,
    private route: Router,
    private productoServicio: ProductoService,
    private ventaServicio: VentaService
  ) {
    this.usuario = JSON.parse(sessionStorage.getItem("usuario"));
    if (!this.usuario) {
      this.route.navigateByUrl("/login");
      return;
    }
    if (this.usuario.nombrePerfil == "Administrador") {
      sessionStorage.clear();
      this.route.navigateByUrl("/login");
      return;
    }
    this.venta = new VentaTo();
    this.productos = new Array();
    this.productoSeleccionado = new ProductoTo();
  }

  ngOnInit() {
    this.cargarProductos();
  }

  salir() {
    sessionStorage.clear();
    this.route.navigateByUrl("/login");
  }

  cargarProductos() {
    this.productoServicio.listarTodosProductosActivos().subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.productos = resultado.listaProductos;
          this.productos.forEach(producto => {
            producto.tipoDescripcion = producto.tipo.descripcion;
          });
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
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

  guardarVenta() {
    this.ventaServicio.guardar(this.venta).subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.messageService.add({
            severity: "success",
            summary: "Información",
            detail: "Venta realizada exitosamente!"
          });
          this.mostrarDialogo = false;
          this.montoTotal = 0;
          this.venta = new VentaTo();
          this.cargarProductos();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
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
  seleccionarProducto(event: Event, producto: ProductoTo) {
    if (Number(producto.cantidad) <= 0) {
      this.messageService.add({
        severity: "error",
        summary: "Error",
        detail: "No existe este producto en stock."
      });
      return;
    }
    this.montoTotal = 0;
    this.productoSeleccionado = producto;
    this.mostrarDialogo = true;
    event.preventDefault();
  }
  calcular() {
    if (
      Number(this.venta.cantidad) > Number(this.productoSeleccionado.cantidad)
    ) {
      this.venta.cantidad = "0";
      this.montoTotal = 0;
      this.messageService.add({
        severity: "error",
        summary: "Error",
        detail:
          "No puede ingresar un nímero superior a las unidades disponibles"
      });
      return;
    }
    this.montoTotal =
      Number(this.venta.cantidad) *
      Number(this.productoSeleccionado.precioUnitario);
  }
  habilitarBoton(): boolean {
    if (this.montoTotal) {
      return this.montoTotal > 0;
    }
    return false;
  }
  realizarVenta() {
    this.venta.idProducto = this.productoSeleccionado.id;
    this.venta.idUsuario = this.usuario.idUsuario;
    this.venta.valorTotal = String(this.montoTotal);
    this.guardarVenta();
  }
  cancelar() {
    this.mostrarDialogo = false;
  }
}
