package biblioteca;

public class Socio implements Comparable<Socio> {
    private int numeroAsociado;
    private String dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String telefono;
    private String estado;

    // Constructor
    public Socio(int numeroAsociado, String dni, String nombre, String apellido, String domicilio, String telefono) {
        this.numeroAsociado = numeroAsociado;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = "ok"; // Estado inicial al dar de alta a un socio
    }

    // Métodos de acceso (getters y setters)
    public int getNumeroAsociado() {
        return numeroAsociado;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método compareTo para ordenar por número de asociado
    @Override
    public int compareTo(Socio otroSocio) {
        return Integer.compare(this.numeroAsociado, otroSocio.getNumeroAsociado());
    }

	@Override
	public String toString() {
		return "Socio [numeroAsociado=" + numeroAsociado + ", dni=" + dni + ", nombre=" + nombre + ", apellido="
				+ apellido + ", domicilio=" + domicilio + ", telefono=" + telefono + ", estado=" + estado + "]";
	}
    

    
}
