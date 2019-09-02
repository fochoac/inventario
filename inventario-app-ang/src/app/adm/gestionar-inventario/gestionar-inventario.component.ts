import { GestionInventarioService } from "./../../servicios/gestion-inventario.service";
import { Component, OnInit } from "@angular/core";
import { UsuarioTo } from "src/app/modelo/usuario-to";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { EstablecimientoTo } from "src/app/modelo/establecimiento-to";
import { SucursalTo } from "src/app/modelo/sucursal-to";
import { BodegaTo } from "src/app/modelo/bodega-to";
import { ProductoTo } from "src/app/modelo/producto-to";
import { CatalogoTo } from "src/app/modelo/catalogo-to";
import { ConfirmationService } from "primeng/api";
import { CatalogosService } from "src/app/servicios/catalogos.service";
import { ProductoService } from "src/app/servicios/producto.service";
import { ReporteTo } from "src/app/modelo/reporte-to";
@Component({
  selector: "app-gestionar-inventario",
  templateUrl: "./gestionar-inventario.component.html",
  styleUrls: ["./gestionar-inventario.component.css"]
})
export class GestionarInventarioComponent implements OnInit {
  costoInventario = 0;
  registroReporte: ReporteTo[] = [];
  usuario: UsuarioTo;
  establecimiento: EstablecimientoTo;
  sucursales: SucursalTo[];
  sucursalSeleccionada: SucursalTo;
  sucursalSeleccionadaReporte: SucursalTo;
  bodegas: BodegaTo[];
  bodegasReporte: BodegaTo[];
  bodegaSeleccionada: BodegaTo;
  bodegaSeleccionadaReporte: BodegaTo;
  mostrarDialogo = false;
  catalogosTipo: CatalogoTo[];
  catalogoSeleccionado: CatalogoTo;
  productoNuevo: ProductoTo;
  catalogos: Map<string, CatalogoTo>;
  productos: ProductoTo[];
  productosReporte: ProductoTo[];

  cols = [
    { field: "codigo", header: "Código" },
    { field: "marca", header: "Marca" },
    { field: "nombre", header: "Nombre" },
    { field: "cantidad", header: "Cantidad" },
    { field: "tipoDescripcion", header: "Tipo" },
    { field: "", header: "Acciones" }
  ];
  columnasReporte = [
    { field: "codigo", header: "Código" },
    { field: "tipo", header: "Tipo" },
    { field: "cantidad", header: "Cantidad" },
    { field: "costoUnitario", header: "Costo Unitario" },
    { field: "costoTotal", header: "Total" }
  ];
  constructor(
    private confirmacionServicio: ConfirmationService,
    private route: Router,
    private gestionServicio: GestionInventarioService,
    private catalogoServicio: CatalogosService,
    private productoServicio: ProductoService,
    private mensajeServicio: MessageService
  ) {
    this.productoNuevo = new ProductoTo();
    this.usuario = JSON.parse(sessionStorage.getItem("usuario"));
    if (!this.usuario) {
      this.route.navigateByUrl("/login");
      return;
    }
    if (this.usuario.nombrePerfil != "Administrador") {
      sessionStorage.clear();
      this.route.navigateByUrl("/login");
      return;
    }
    this.establecimiento = new EstablecimientoTo();
    this.sucursales = [];
  }

  ngOnInit() {
    this.cargarDatosEstablecimiento();
    this.cargarCatalogosProducto();
  }

  private cargarDatosEstablecimiento() {
    this.gestionServicio
      .obtenerInformacionEstablecimiento(this.usuario)
      .subscribe(
        resultado => {
          if (resultado.respuesta.codigo == "1") {
            this.establecimiento = resultado;
            this.gestionServicio.establecimiento = this.establecimiento;
            this.cargarSucursales();
          } else {
            this.mensajeServicio.add({
              severity: "warn",
              summary: "Información",
              detail: resultado.respuesta.mensaje
            });
          }
        },
        error => {
          this.mensajeServicio.add({
            severity: "error",
            summary: "Error",
            detail: error
          });
        }
      );
  }

  private cargarCatalogosProducto() {
    this.catalogos = new Map();
    this.catalogoServicio.listarTipoProductoActivos().subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.catalogosTipo = resultado.listaCatalogos;
          resultado.listaCatalogos.forEach(catalogo => {
            this.catalogos.set(catalogo.idCatalogo, catalogo);
          });
        } else {
          this.mensajeServicio.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
          });
        }
      },
      error => {
        this.mensajeServicio.add({
          severity: "error",
          summary: "Error",
          detail: error
        });
      }
    );
  }

  private cargarSucursales() {
    this.gestionServicio.listarSucursales(this.establecimiento).subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.sucursales = resultado.listaSucursales;
          this.sucursales.forEach(sucursal => {
            sucursal.nombre = sucursal.codigoSRI + " - " + sucursal.nombre;
          });
          this.gestionServicio.sucursales = this.sucursales;
        } else {
          this.mensajeServicio.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
          });
        }
      },
      error => {
        this.mensajeServicio.add({
          severity: "error",
          summary: "Error",
          detail: error
        });
      }
    );
  }
  cargarBodegasReporte() {
    this.gestionServicio
      .listarBodegas(this.sucursalSeleccionadaReporte)
      .subscribe(
        resultado => {
          if (resultado.respuesta.codigo == "1") {
            this.bodegasReporte = resultado.listaBodegas;
            this.bodegasReporte.forEach(bodega => {
              bodega.nombre = bodega.codigo + " - " + bodega.nombre;
            });
          } else {
            this.mensajeServicio.add({
              severity: "warn",
              summary: "Información",
              detail: resultado.respuesta.mensaje
            });
          }
        },
        error => {
          this.mensajeServicio.add({
            severity: "error",
            summary: "Error",
            detail: error
          });
        }
      );
  }
  cargarBodegasAdministracion() {
    this.gestionServicio.listarBodegas(this.sucursalSeleccionada).subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.bodegas = resultado.listaBodegas;
          this.bodegas.forEach(bodega => {
            bodega.nombre = bodega.codigo + " - " + bodega.nombre;
          });
        } else {
          this.mensajeServicio.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
          });
        }
      },
      error => {
        this.mensajeServicio.add({
          severity: "error",
          summary: "Error",
          detail: error
        });
      }
    );
  }

  cargarProductosBodegaReporte() {
    this.productoServicio
      .listarProductosBodega(this.bodegaSeleccionadaReporte)
      .subscribe(
        resultado => {
          if (resultado.respuesta.codigo == "1") {
            this.productosReporte = resultado.listaProductos;
            this.generarReporteSimple();
          } else {
            this.mensajeServicio.add({
              severity: "warn",
              summary: "Información",
              detail: resultado.respuesta.mensaje
            });
          }
        },
        error => {
          this.mensajeServicio.add({
            severity: "error",
            summary: "Error",
            detail: error
          });
        }
      );
  }
  cargarProductosBodegaAdministracion() {
    this.productoServicio
      .listarProductosBodega(this.bodegaSeleccionada)
      .subscribe(
        resultado => {
          if (resultado.respuesta.codigo == "1") {
            this.productos = resultado.listaProductos;
            this.productos.forEach(producto => {
              producto.tipoDescripcion = producto.tipo.descripcion;
            });
          } else {
            this.mensajeServicio.add({
              severity: "warn",
              summary: "Información",
              detail: resultado.respuesta.mensaje
            });
          }
        },
        error => {
          this.mensajeServicio.add({
            severity: "error",
            summary: "Error",
            detail: error
          });
        }
      );
  }
  crearProducto() {
    this.productoNuevo = new ProductoTo();
    this.catalogoSeleccionado = null;
    this.mostrarDialogo = true;
  }
  actualizarProducto(producto: ProductoTo) {
    this.productoNuevo = producto;
    this.catalogoSeleccionado = this.catalogos.get(producto.tipo.idCatalogo);
    this.mostrarDialogo = true;
  }
  eliminarProducto(producto: ProductoTo) {
    this.confirmacionServicio.confirm({
      message: "Está seguro de eliminar el producto?",
      accept: () => {
        producto.estado = "false";
        this.registrarProducto(producto);
      }
    });
  }

  guardarProducto() {
    if (this.validarProductoNuevo()) {
      this.mensajeServicio.add({
        severity: "error",
        summary: "Error",
        detail: "Ingrese los campos obligatorios"
      });
      return;
    }
    this.productoNuevo.idBodega = this.bodegaSeleccionada.idBodega;
    this.productoNuevo.tipo = this.catalogoSeleccionado;
    this.productoNuevo.estado = "true";
    this.registrarProducto(this.productoNuevo);
  }
  private registrarProducto(producto: ProductoTo) {
    this.productoServicio.guardar(producto).subscribe(
      resultado => {
        if (resultado.respuesta.codigo == "1") {
          this.cargarProductosBodegaAdministracion();
          this.mensajeServicio.add({
            severity: "success",
            summary: "Información",
            detail: "Registro guardado exitosamente!"
          });
          this.mostrarDialogo = false;
        } else {
          this.mensajeServicio.add({
            severity: "warn",
            summary: "Información",
            detail: resultado.respuesta.mensaje
          });
        }
      },
      error => {
        this.mensajeServicio.add({
          severity: "error",
          summary: "Error",
          detail: error
        });
      }
    );
  }
  cancelar() {
    this.mostrarDialogo = false;
  }

  validarProductoNuevo(): boolean {
    let presentoError = false;
    if (!this.productoNuevo.codigo) {
      presentoError = true;
    }
    if (!this.productoNuevo.marca) {
      presentoError = true;
    }
    if (!this.productoNuevo.nombre) {
      presentoError = true;
    }

    if (!this.productoNuevo.cantidad) {
      presentoError = true;
    }
    if (!this.productoNuevo.precioUnitario) {
      presentoError = true;
    }
    if (!this.catalogoSeleccionado) {
      presentoError = true;
    }
    return presentoError;
  }

  generarReporteSimple() {
    if (!this.productosReporte) {
      this.mensajeServicio.add({
        severity: "info",
        summary: "Información",
        detail: "No existen registros disponibles"
      });
      return;
    }
    this.registroReporte = new Array();
    this.costoInventario = 0;
    this.productosReporte.forEach(producto => {
      const item = new ReporteTo();
      item.codigo = producto.codigo;
      item.tipo = producto.tipo.descripcion;
      item.cantidad = Number(producto.cantidad);
      item.costoUnitario = Number(producto.precioUnitario);
      item.costoTotal = item.cantidad * item.costoUnitario;
      this.registroReporte.push(item);
      this.costoInventario += item.costoTotal;
    });
  }
  salir() {
    sessionStorage.clear();
    this.route.navigateByUrl("/login");
  }
}
