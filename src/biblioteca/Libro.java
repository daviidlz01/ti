package biblioteca;

public class Libro implements Comparable<Libro> {
    private String codigo;
    private String titulo;
    private String autor;
    private String editorial;
    private int añoPublicacion;
    private String tematica;
    private double precio;

    // Constructor
    public Libro(String codigo, String titulo, String autor, String editorial, int añoPublicacion, String tematica, double precio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.tematica = tematica;
        this.precio = precio;
    }

    // Métodos de acceso (getters y setters)
    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnoPublicacion() {
        return añoPublicacion;
    }

    public String getTematica() {
        return tematica;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método compareTo para ordenar por código
    @Override
    public int compareTo(Libro otroLibro) {
        return this.codigo.compareTo(otroLibro.getCodigo());
    }

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", añoPublicacion=" + añoPublicacion + ", tematica=" + tematica + ", precio=" + precio + "]";
	}
    

    
}
