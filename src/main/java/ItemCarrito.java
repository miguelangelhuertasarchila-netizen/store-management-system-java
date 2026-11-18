public class ItemCarrito {
    private String nombre;
    private int cantidad;
    private int subtotal;

    public ItemCarrito(String nombre, int cantidad, int subtotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
