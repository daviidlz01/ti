package biblioteca;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;
import java.util.Date;

public class Prestamo {
    private static int contadorPrestamos = 0;

    private int numeroIdentificador;
    private String codigoLibro;
    private int numeroSocio;
    private Date fechaPrestamo;
    private int plazo;
    private Date fechaVencimiento;
    private String estado;
    private Date fechaDevolucion;
    private double multa;

    // Constructor
    public Prestamo(String codigoLibro, int numeroSocio, int plazo) {
        this.numeroIdentificador = ++contadorPrestamos;
        this.codigoLibro = codigoLibro;
        this.numeroSocio = numeroSocio;
        this.fechaPrestamo = new Date(); // Fecha actual
        this.plazo = plazo;
        this.estado = "vigente";
        this.fechaDevolucion = null;
        this.multa = 0.0;

        // Llamada al método para calcular la fecha de vencimiento
        calcularFechaVencimiento();
    }

    // Métodos de acceso (getters y setters)
    public int getNumeroIdentificador() {
        return numeroIdentificador;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public int getPlazo() {
        return plazo;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    private void calcularFechaVencimiento() {
        LocalDate fechaPrestamoLocal = fechaPrestamo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaVencimientoLocal = fechaPrestamoLocal.plus(plazo, ChronoUnit.DAYS);
        
        // Convierte LocalDate a Date
        fechaVencimiento = Date.from(fechaVencimientoLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
