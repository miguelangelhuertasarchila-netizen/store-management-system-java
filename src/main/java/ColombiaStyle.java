import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ColombiaStyle extends Application {
    private LinkedHashMap<String, Producto> productos = new LinkedHashMap<>();
    private int dineroTotal = 0;
    private ArrayList<ItemCarrito> carrito = new ArrayList<>();
    private final String PIN_DUENO = "1234";
    private boolean pinCorrecto = false;

    private Stage ventana;
    private Stage ventanaCarrito;
    private ScrollPane scrollableFrame;
    private Label titulo;
    private Label tituloCliente;
    private Button btnCliente;
    private Button btnDueno;
    private Button btnSalir;
    private Button btnCarrito;
    private GridPane listaProductos;
    private VBox frameCliente;

    private final String AZUL = "#003893";
    private final String AMARILLO = "#FCD116";
    private final String ROJO = "#CE1126";
    private final String VERDE = "#1B7A43";
    private final String FONDO = "#F1F3F8";
    private final String TEXTO = "#1A1D2E";
    private final String GRIS = "#6B7085";
    private final String BORDE = "#DDE1EA";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ventana = stage;
        ventana.setTitle("Colombia Style");
        cargarProductos();
        ventana.setScene(new Scene(new VBox(), 1100, 760));
        crearInterfaz();
        ventana.show();
    }

    private void cargarProductos() {
        productos.put("Camiseta", new Producto(45000, 15, 30, 0));
        productos.put("Pantalón", new Producto(85000, 10, 20, 0));
        productos.put("Zapatos", new Producto(180000, 8, 10, 0));
        productos.put("Gorra", new Producto(35000, 20, 25, 0));
        productos.put("Chaqueta", new Producto(160000, 5, 10, 0));
        productos.put("Sudadera", new Producto(120000, 12, 15, 0));
        productos.put("Bufanda", new Producto(40000, 10, 10, 0));
        productos.put("Tenis", new Producto(220000, 6, 12, 0));
        productos.put("Reloj", new Producto(250000, 4, 5, 0));
        productos.put("Lentes", new Producto(90000, 15, 10, 0));
    }

    public void crearInterfaz() {
        titulo = new Label("Colombia Style");
        titulo.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        Label subtitulo = new Label("Tienda de ropa y accesorios");
        subtitulo.setStyle("-fx-font-size: 18; -fx-text-fill: " + GRIS + ";");

        Label pregunta = new Label("¿Cómo deseas ingresar?");
        pregunta.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        btnCliente = crearBoton("Ingresar como cliente", AZUL);
        btnDueno = crearBoton("Ingresar como dueño", AMARILLO);
        btnSalir = crearBoton("Salir", "blanco");
        btnCliente.setPrefWidth(260);
        btnDueno.setPrefWidth(260);
        btnSalir.setPrefWidth(260);

        btnCliente.setOnAction(e -> ingresarCliente());
        btnDueno.setOnAction(e -> abrirPanelDueno());
        btnSalir.setOnAction(e -> ventana.close());

        VBox tarjeta = new VBox(14, crearBandera(90, 60), titulo, subtitulo, new Region(),
                pregunta, btnCliente, btnDueno, btnSalir);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setPadding(new Insets(40, 60, 40, 60));
        tarjeta.setMaxWidth(460);
        tarjeta.setMaxHeight(Region.USE_PREF_SIZE);
        tarjeta.setStyle(estiloTarjeta());

        StackPane inicio = new StackPane(tarjeta);
        inicio.setStyle(estiloFondo());
        cambiarPantalla(inicio);
    }

    public void volverInicio() {
        if (ventanaCarrito != null) {
            ventanaCarrito.close();
        }
        crearInterfaz();
    }

    public void ingresarCliente() {
        Button btnIrDueno = crearBoton("Ingresar como dueño", "blanco");
        btnIrDueno.setOnAction(e -> abrirPanelDueno());
        Button btnInicio = crearBoton("Inicio", "blanco");
        btnInicio.setOnAction(e -> volverInicio());
        btnCarrito = crearBoton("Carrito (0)", AZUL);
        btnCarrito.setOnAction(e -> mostrarCarrito());

        Label lblBanner = new Label("Ropa con sabor\ncolombiano");
        lblBanner.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");
        VBox franjaAmarilla = new VBox(lblBanner);
        franjaAmarilla.setPadding(new Insets(16, 24, 12, 24));
        franjaAmarilla.setStyle("-fx-background-color: " + AMARILLO + "; -fx-background-radius: 10 10 0 0;");

        Label lblTexto = new Label("Camisetas, chaquetas, tenis y accesorios. Elige tus favoritos y arma tu carrito.");
        lblTexto.setStyle("-fx-text-fill: white; -fx-font-size: 13;");
        Button btnVerCatalogo = crearBoton("Ver catálogo", AMARILLO);
        btnVerCatalogo.setOnAction(e -> scrollableFrame.setVvalue(1.0));
        HBox franjaAzul = new HBox(10, lblTexto, espacio(), btnVerCatalogo);
        franjaAzul.setAlignment(Pos.CENTER_LEFT);
        franjaAzul.setPadding(new Insets(12, 24, 12, 24));
        franjaAzul.setStyle("-fx-background-color: " + AZUL + ";");

        Label lblStock = new Label("Stock limitado en cada referencia");
        lblStock.setStyle("-fx-text-fill: white; -fx-font-size: 11;");
        VBox franjaRoja = new VBox(lblStock);
        franjaRoja.setPadding(new Insets(5, 24, 5, 24));
        franjaRoja.setStyle("-fx-background-color: " + ROJO + "; -fx-background-radius: 0 0 10 10;");

        VBox banner = new VBox(franjaAmarilla, franjaAzul, franjaRoja);

        tituloCliente = new Label("Catálogo de productos");
        tituloCliente.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        listaProductos = new GridPane();
        listaProductos.setHgap(16);
        listaProductos.setVgap(16);

        VBox contenido = new VBox(20, banner, tituloCliente, listaProductos);
        contenido.setPadding(new Insets(20, 40, 30, 40));
        contenido.setMaxWidth(900);

        StackPane centrado = new StackPane(contenido);
        centrado.setStyle(estiloFondo());
        scrollableFrame = new ScrollPane(centrado);
        scrollableFrame.setFitToWidth(true);
        scrollableFrame.setStyle("-fx-background: " + FONDO + "; -fx-background-color: " + FONDO + ";");
        VBox.setVgrow(scrollableFrame, Priority.ALWAYS);

        frameCliente = new VBox(crearEncabezado(btnInicio, btnIrDueno, btnCarrito), scrollableFrame);
        actualizarLista();
        cambiarPantalla(frameCliente);
    }

    public void actualizarLista() {
        if (listaProductos != null) {
            listaProductos.getChildren().clear();
            int i = 0;
            for (String nombre : productos.keySet()) {
                Producto p = productos.get(nombre);

                listaProductos.add(crearTarjetaProducto(nombre, p, i), i % 3, i / 3);
                i++;
            }
        }
        if (btnCarrito != null) {
            btnCarrito.setText("Carrito (" + unidadesEnCarrito() + ")");
        }
    }

    private VBox crearTarjetaProducto(String nombre, Producto p, int posicion) {
        String[] colores = {"#FBE7A1", "#CAD6F2", "#F4C5CB"};
        Label lblImagen = new Label(nombre);
        lblImagen.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");
        StackPane imagen = new StackPane(lblImagen);
        imagen.setPrefSize(260, 100);
        imagen.setStyle("-fx-background-color: " + colores[posicion % 3] + "; -fx-background-radius: 10 10 0 0;");

        if (p.getStock() == 0) {
            imagen.getChildren().add(crearEtiqueta("Agotado"));
        } else if (p.getStock() <= 5) {
            imagen.getChildren().add(crearEtiqueta("Quedan " + p.getStock()));
        }

        Label lblNombre = new Label(nombre);
        lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-fill: " + TEXTO + ";");
        Label lblPrecio = new Label(formatoPrecio(p.getPrecio()));
        lblPrecio.setStyle("-fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");
        Label lblStock = new Label("Stock: " + p.getStock());
        lblStock.setStyle("-fx-font-size: 11; -fx-text-fill: " + GRIS + ";");

        Button btnAgregar = crearBoton("Agregar al carrito", AZUL);
        btnAgregar.setMaxWidth(Double.MAX_VALUE);
        btnAgregar.setDisable(p.getStock() == 0);
        btnAgregar.setOnAction(e -> comprarProducto(nombre));

        VBox info = new VBox(4, lblNombre, lblPrecio, lblStock, btnAgregar);
        info.setPadding(new Insets(10, 12, 12, 12));
        VBox.setMargin(btnAgregar, new Insets(6, 0, 0, 0));

        VBox tarjeta = new VBox(imagen, info);
        tarjeta.setPrefWidth(260);
        tarjeta.setStyle(estiloTarjeta());
        return tarjeta;
    }

    private Label crearEtiqueta(String texto) {
        Label etiqueta = new Label(texto);
        etiqueta.setStyle("-fx-background-color: " + ROJO + "; -fx-text-fill: white; -fx-font-size: 10; "
                + "-fx-font-weight: bold; -fx-padding: 2 7; -fx-background-radius: 4;");
        StackPane.setAlignment(etiqueta, Pos.TOP_LEFT);
        StackPane.setMargin(etiqueta, new Insets(6));
        return etiqueta;
    }

    public void comprarProducto(String nombre) {
        Producto p = productos.get(nombre);

        int disponibles = p.getStock() - cantidadEnCarrito(nombre);
        if (disponibles <= 0) {
            mostrarMensaje("Sin unidades", "Ya tienes en el carrito todas las unidades disponibles de " + nombre + ".");
            return;
        }

        Optional<Integer> respuesta = elegirCantidad("Agregar al carrito", nombre + " - " + formatoPrecio(p.getPrecio()),
                "¿Cuántas unidades deseas agregar?", disponibles);
        if (respuesta.isPresent()) {
            int cantidad = respuesta.get();
            ItemCarrito item = buscarEnCarrito(nombre);
            if (item == null) {
                carrito.add(new ItemCarrito(nombre, cantidad, cantidad * p.getPrecio()));
            } else {
                cambiarCantidad(item, item.getCantidad() + cantidad);
            }
            actualizarLista();
            refrescarCarrito();
        }
    }

    public void quitarProducto(ItemCarrito item) {
        Optional<Integer> respuesta = elegirCantidad("Quitar producto", item.getNombre(),
                "¿Cuántas unidades deseas retirar?", item.getCantidad());
        if (respuesta.isPresent()) {
            cambiarCantidad(item, item.getCantidad() - respuesta.get());
            actualizarLista();
            refrescarCarrito();
        }
    }

    private void cambiarCantidad(ItemCarrito item, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            carrito.remove(item);
        } else {
            int precio = productos.get(item.getNombre()).getPrecio();
            item.setCantidad(nuevaCantidad);
            item.setSubtotal(nuevaCantidad * precio);
        }
    }

    public void mostrarCarrito() {
        if (ventanaCarrito == null) {
            ventanaCarrito = new Stage();
            ventanaCarrito.setTitle("Tu carrito - Colombia Style");
            ventanaCarrito.initOwner(ventana);
            ventanaCarrito.setScene(new Scene(new VBox(), 390, 640));
        }
        refrescarCarrito();
        ventanaCarrito.show();
        ventanaCarrito.toFront();
    }

    private void refrescarCarrito() {
        if (ventanaCarrito == null) {
            return;
        }
        Label lblTitulo = new Label("Tu carrito");
        lblTitulo.setStyle("-fx-font-size: 26; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        VBox listaItems = new VBox(10);
        listaItems.setPadding(new Insets(14));
        listaItems.setStyle(estiloTarjeta());

        if (carrito.isEmpty()) {
            Label vacio = new Label("Tu carrito está vacío.\nAgrega productos desde el catálogo.");
            vacio.setStyle("-fx-text-fill: " + GRIS + ";");
            listaItems.getChildren().add(vacio);
        }

        for (int i = 0; i < carrito.size(); i++) {
            ItemCarrito item = carrito.get(i);
            int precio = productos.get(item.getNombre()).getPrecio();

            Label lblNombre = new Label(item.getNombre());
            lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-fill: " + TEXTO + ";");
            Label lblSubtotal = new Label(formatoPrecio(item.getSubtotal()));
            lblSubtotal.setStyle("-fx-font-weight: bold; -fx-font-size: 14; -fx-text-fill: " + TEXTO + ";");
            Label lblUnidad = new Label(formatoPrecio(precio) + " c/u");
            lblUnidad.setStyle("-fx-text-fill: " + GRIS + ";");

            Button btnMenos = crearBoton("-", "blanco");
            btnMenos.setOnAction(e -> {
                cambiarCantidad(item, item.getCantidad() - 1);
                actualizarLista();
                refrescarCarrito();
            });
            Label lblCantidad = new Label(String.valueOf(item.getCantidad()));
            lblCantidad.setMinWidth(24);
            lblCantidad.setAlignment(Pos.CENTER);

            Button btnMas = crearBoton("+", "blanco");
            btnMas.setOnAction(e -> {
                if (cantidadEnCarrito(item.getNombre()) < productos.get(item.getNombre()).getStock()) {
                    cambiarCantidad(item, item.getCantidad() + 1);
                    actualizarLista();
                    refrescarCarrito();
                } else {
                    mostrarMensaje("Sin unidades", "No hay más unidades de " + item.getNombre() + " en stock.");
                }
            });

            Button btnQuitar = crearBoton("Quitar", "blanco");
            btnQuitar.setStyle(btnQuitar.getStyle() + "-fx-text-fill: " + ROJO + ";");
            btnQuitar.setOnAction(e -> quitarProducto(item));

            HBox filaNombre = new HBox(lblNombre, espacio(), lblSubtotal);
            HBox filaBotones = new HBox(8, btnMenos, lblCantidad, btnMas, espacio(), btnQuitar);
            filaBotones.setAlignment(Pos.CENTER_LEFT);
            listaItems.getChildren().addAll(filaNombre, lblUnidad, filaBotones);

            if (i < carrito.size() - 1) {
                Region linea = new Region();
                linea.setPrefHeight(1);
                linea.setStyle("-fx-background-color: " + BORDE + ";");
                listaItems.getChildren().add(linea);
            }
        }

        ScrollPane scrollItems = new ScrollPane(listaItems);
        scrollItems.setFitToWidth(true);
        scrollItems.setStyle("-fx-background: " + FONDO + "; -fx-background-color: transparent;");
        VBox.setVgrow(scrollItems, Priority.ALWAYS);

        Label lblTotal = new Label("Total a pagar");
        lblTotal.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");
        Label lblValor = new Label(formatoPrecio(totalCarrito()));
        lblValor.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");
        HBox filaTotal = new HBox(lblTotal, espacio(), lblValor);

        Button btnFinalizar = crearBoton("Finalizar compra", VERDE);
        btnFinalizar.setMaxWidth(Double.MAX_VALUE);
        btnFinalizar.setStyle(btnFinalizar.getStyle() + "-fx-font-size: 15; -fx-padding: 12;");
        btnFinalizar.setDisable(carrito.isEmpty());
        btnFinalizar.setOnAction(e -> finalizarCompra());

        VBox cuerpo = new VBox(14, lblTitulo, scrollItems, filaTotal, btnFinalizar);
        cuerpo.setPadding(new Insets(16));
        VBox.setVgrow(cuerpo, Priority.ALWAYS);

        VBox raiz = new VBox(crearEncabezado(), cuerpo);
        raiz.setStyle(estiloFondo());
        ventanaCarrito.getScene().setRoot(raiz);
    }

    public void finalizarCompra() {
        if (carrito.isEmpty()) {
            return;
        }
        VBox recibo = new VBox(8);
        int total = 0;
        for (ItemCarrito item : carrito) {
            Producto p = productos.get(item.getNombre());
            p.setStock(p.getStock() - item.getCantidad());
            p.setVendidos(p.getVendidos() + item.getCantidad());
            total += item.getSubtotal();

            Label lblItem = new Label(item.getNombre() + " ×" + item.getCantidad());
            Label lblValor = new Label(formatoPrecio(item.getSubtotal()));
            HBox fila = new HBox(lblItem, espacio(), lblValor);
            fila.setStyle("-fx-font-size: 14; -fx-padding: 0 0 6 0; -fx-border-color: transparent transparent "
                    + BORDE + " transparent;");
            recibo.getChildren().add(fila);
        }
        dineroTotal += total;

        carrito.clear();
        ventanaCarrito.close();
        actualizarLista();

        Label lblTotal = new Label("Total pagado");
        Label lblValor = new Label(formatoPrecio(total));
        HBox filaTotal = new HBox(lblTotal, espacio(), lblValor);
        filaTotal.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        recibo.getChildren().add(filaTotal);

        Stage ventanaRecibo = crearVentanaPequena("Compra exitosa");
        Button btnListo = crearBoton("Listo", AZUL);
        btnListo.setPrefWidth(100);
        btnListo.setOnAction(e -> ventanaRecibo.close());

        mostrarVentanaPequena(ventanaRecibo, "Compra exitosa", recibo, btnListo);
    }

    public void abrirPanelDueno() {
        pinCorrecto = false;
        Stage ventanaPin = crearVentanaPequena("PIN administrativo");

        Label lblIndicacion = new Label("Ingresa el PIN para entrar al panel.");
        lblIndicacion.setStyle("-fx-text-fill: " + GRIS + ";");
        PasswordField campoPin = new PasswordField();
        campoPin.setPromptText("PIN");
        campoPin.setStyle("-fx-background-color: " + FONDO + "; -fx-border-color: " + BORDE + "; "
                + "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 10; -fx-font-size: 14;");
        Label lblError = new Label("");
        lblError.setStyle("-fx-text-fill: " + ROJO + ";");

        Button btnCancelar = crearBoton("Cancelar", "blanco");
        Button btnEntrar = crearBoton("Entrar", AZUL);
        btnCancelar.setOnAction(e -> ventanaPin.close());
        btnEntrar.setOnAction(e -> {
            if (verificarPin(campoPin.getText())) {
                pinCorrecto = true;
                ventanaPin.close();
            } else {
                lblError.setText("PIN incorrecto, intenta de nuevo.");
                campoPin.clear();
            }
        });
        btnEntrar.setDefaultButton(true);

        VBox contenido = new VBox(10, lblIndicacion, campoPin, lblError);

        mostrarVentanaPequena(ventanaPin, "PIN administrativo", contenido, btnCancelar, btnEntrar);

        if (pinCorrecto) {
            mostrarPanelDueno();
        }
    }

    private boolean verificarPin(String pin) {
        return pin.equals(PIN_DUENO);
    }

    private void mostrarPanelDueno() {
        Button btnVolver = crearBoton("Volver a la tienda", "blanco");
        btnVolver.setOnAction(e -> ingresarCliente());

        Label lblTitulo = new Label("Panel administrativo");
        lblTitulo.setStyle("-fx-font-size: 26; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        Label lblCaja = new Label("Dinero total en caja");
        lblCaja.setStyle("-fx-text-fill: white; -fx-font-size: 13;");
        Label lblDinero = new Label(formatoPrecio(dineroTotal));
        lblDinero.setStyle("-fx-text-fill: white; -fx-font-size: 36; -fx-font-weight: bold;");
        VBox cajaDinero = new VBox(2, lblCaja, lblDinero);
        cajaDinero.setPadding(new Insets(14, 20, 14, 20));
        cajaDinero.setStyle("-fx-background-color: " + AZUL + "; -fx-background-radius: 10;");

        GridPane tabla = new GridPane();
        tabla.setStyle(estiloTarjeta());

        String[] encabezados = {"Producto", "Stock actual", "En almacén", "Unidades vendidas"};
        for (int c = 0; c < encabezados.length; c++) {
            Label lbl = celda(encabezados[c], c);
            lbl.setStyle(lbl.getStyle() + "-fx-font-weight: bold; -fx-text-fill: " + GRIS + "; -fx-font-size: 11;");
            tabla.add(lbl, c, 0);
        }

        int fila = 1;
        for (String nombre : productos.keySet()) {
            Producto p = productos.get(nombre);
            Label lblStock = celda(String.valueOf(p.getStock()), 1);
            if (p.getStock() <= 3) {
                lblStock.setStyle(lblStock.getStyle() + "-fx-text-fill: " + ROJO + "; -fx-font-weight: bold;");
            }
            tabla.add(celda(nombre, 0), 0, fila);
            tabla.add(lblStock, 1, fila);
            tabla.add(celda(String.valueOf(p.getAlmacen()), 2), 2, fila);
            tabla.add(celda(String.valueOf(p.getVendidos()), 3), 3, fila);
            fila++;
        }

        Button btnCerrarSesion = crearBoton("Cerrar sesión", "blanco");
        btnCerrarSesion.setOnAction(e -> volverInicio());

        VBox contenido = new VBox(16, lblTitulo, cajaDinero, tabla, btnCerrarSesion);
        contenido.setPadding(new Insets(24, 40, 30, 40));
        contenido.setMaxWidth(900);
        StackPane centrado = new StackPane(contenido);
        centrado.setAlignment(Pos.TOP_CENTER);

        ScrollPane scroll = new ScrollPane(centrado);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: " + FONDO + "; -fx-background-color: " + FONDO + ";");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        VBox panel = new VBox(scroll);
        cambiarPantalla(panel);
    }

    private Label celda(String texto, int columna) {
        Label lbl = new Label(texto);
        lbl.setPrefWidth(205);
        lbl.setStyle("-fx-padding: 8 12; -fx-font-size: 13; -fx-border-color: transparent transparent "
                + BORDE + " transparent;");
        if (columna > 0) {
            lbl.setAlignment(Pos.CENTER_RIGHT);
        }
        return lbl;
    }

    private int cantidadEnCarrito(String nombre) {
        ItemCarrito item = buscarEnCarrito(nombre);
        if (item == null) {
            return 0;
        }
        return item.getCantidad();
    }

    private ItemCarrito buscarEnCarrito(String nombre) {
        for (ItemCarrito item : carrito) {
            if (item.getNombre().equals(nombre)) {
                return item;
            }
        }
        return null;
    }

    private int unidadesEnCarrito() {
        int unidades = 0;
        for (ItemCarrito item : carrito) {
            unidades += item.getCantidad();
        }
        return unidades;
    }

    private int totalCarrito() {
        int total = 0;
        for (ItemCarrito item : carrito) {
            total += item.getSubtotal();
        }
        return total;
    }

    private String formatoPrecio(int valor) {
        return "$" + String.format("%,d", valor).replace(',', '.');
    }

    private Optional<Integer> elegirCantidad(String tituloVentana, String encabezado, String texto, int maximo) {
        ArrayList<Integer> opciones = new ArrayList<>();
        for (int i = 1; i <= maximo; i++) {
            opciones.add(i);
        }
        ChoiceDialog<Integer> dialogo = new ChoiceDialog<>(1, opciones);
        dialogo.initOwner(ventana);
        dialogo.setTitle(tituloVentana);
        dialogo.setHeaderText(encabezado);
        dialogo.setContentText(texto);
        return dialogo.showAndWait();
    }

    private void mostrarMensaje(String tituloVentana, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.initOwner(ventana);
        alerta.setTitle(tituloVentana);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private Stage crearVentanaPequena(String tituloVentana) {
        Stage ventanaPequena = new Stage();
        ventanaPequena.setTitle(tituloVentana);
        ventanaPequena.initOwner(ventana);
        ventanaPequena.initModality(Modality.APPLICATION_MODAL);
        ventanaPequena.setResizable(false);
        return ventanaPequena;
    }

    private void mostrarVentanaPequena(Stage ventanaPequena, String tituloTexto, VBox contenido, Button... botones) {
        Label lblTitulo = new Label(tituloTexto);
        lblTitulo.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        HBox filaBotones = new HBox(10, botones);
        filaBotones.setAlignment(Pos.CENTER_RIGHT);

        Region relleno = new Region();
        VBox.setVgrow(relleno, Priority.ALWAYS);

        VBox raiz = new VBox(14, lblTitulo, contenido, relleno, filaBotones);
        raiz.setPadding(new Insets(22));
        raiz.setStyle("-fx-background-color: white; -fx-font-family: 'Segoe UI';");

        ventanaPequena.setScene(new Scene(raiz, 340, 300));
        ventanaPequena.showAndWait();
    }

    private HBox crearEncabezado(Button... botones) {
        Label lblNombre = new Label("Colombia Style");
        lblNombre.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-text-fill: " + TEXTO + ";");

        HBox encabezado = new HBox(10, crearBandera(24, 16), lblNombre, espacio());
        encabezado.getChildren().addAll(botones);
        encabezado.setAlignment(Pos.CENTER_LEFT);
        encabezado.setPadding(new Insets(10, 20, 10, 20));
        encabezado.setStyle("-fx-background-color: white; -fx-border-color: transparent transparent "
                + BORDE + " transparent;");
        return encabezado;
    }

    private VBox crearBandera(double ancho, double alto) {
        Rectangle amarillo = new Rectangle(ancho, alto / 2, Color.web(AMARILLO));
        Rectangle azul = new Rectangle(ancho, alto / 4, Color.web(AZUL));
        Rectangle rojo = new Rectangle(ancho, alto / 4, Color.web(ROJO));
        VBox bandera = new VBox(amarillo, azul, rojo);
        bandera.setMaxSize(ancho, alto);
        return bandera;
    }

    private Button crearBoton(String texto, String color) {
        Button boton = new Button(texto);
        String estilo = "-fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; "
                + "-fx-padding: 8 16; -fx-cursor: hand; ";
        if (color.equals("blanco")) {
            estilo += "-fx-background-color: white; -fx-border-color: " + BORDE + "; -fx-text-fill: " + TEXTO + "; ";
        } else if (color.equals(AMARILLO)) {
            estilo += "-fx-background-color: " + AMARILLO + "; -fx-text-fill: " + TEXTO + "; ";
        } else {
            estilo += "-fx-background-color: " + color + "; -fx-text-fill: white; ";
        }
        boton.setStyle(estilo);
        return boton;
    }

    private Region espacio() {
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        return region;
    }

    private String estiloTarjeta() {
        return "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: " + BORDE
                + "; -fx-border-radius: 10;";
    }

    private String estiloFondo() {
        return "-fx-background-color: " + FONDO + "; -fx-font-family: 'Segoe UI';";
    }

    private void cambiarPantalla(Parent pantalla) {
        pantalla.setStyle(pantalla.getStyle() + "-fx-font-family: 'Segoe UI';");
        ventana.getScene().setRoot(pantalla);
    }
}
