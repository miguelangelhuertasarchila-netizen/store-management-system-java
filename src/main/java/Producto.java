public class Producto {
    private int precio;
    private int stock;
    private int almacen;
    private int vendidos;

    public Producto(int precio, int stock, int almacen, int vendidos) {
        this.precio = precio;
        this.stock = stock;
        this.almacen = almacen;
        this.vendidos = vendidos;
    }

    public int getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAlmacen() {
        return almacen;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }
}
