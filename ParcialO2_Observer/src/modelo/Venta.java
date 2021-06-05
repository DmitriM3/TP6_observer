package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {

	private double montoFacturado;
	private int litrosCargados;
	private LocalDateTime fechaTime;

	public Venta(String litrosCargados, double monto, String fechaVenta, String horaVenta) {
		this.litrosCargados = Integer.parseInt(litrosCargados);
		this.montoFacturado = monto;
		this.fechaTime = generarFechaLocalDateTime(fechaVenta, horaVenta);
	}

	public Venta(String litrosCargados, double monto, LocalDateTime now) {
		this.litrosCargados = Integer.parseInt(litrosCargados);
		this.montoFacturado = monto;
		this.fechaTime = now;
	}

	public String obtenerDetalleVenta() {
		String fechaLitrosMontoFacturado = this.fechaTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ", "
				+ this.fechaTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ", " + this.litrosCargados + ", "
				+ this.montoFacturado + "\n";
		return fechaLitrosMontoFacturado;
	}

	public double consultarMontoTotal() {
		return this.montoFacturado;
	}

	public int obtenerCantidadLitrosCargados() {
		return this.litrosCargados;
	}

	private LocalDateTime generarFechaLocalDateTime(String fechaVenta, String horaVenta) {
		String splitFecha = "/";
		String splitHora = ":";

		String[] fechaSplit = fechaVenta.split(splitFecha);
		int dia = Integer.parseInt(fechaSplit[1]);
		int mes = Integer.parseInt(fechaSplit[0]);
		int año = Integer.parseInt(fechaSplit[2]);

		String[] horaSplit = horaVenta.split(splitHora);
		int hs = Integer.parseInt(horaSplit[0]);
		int min = Integer.parseInt(horaSplit[1]);
		int seg = Integer.parseInt(horaSplit[2]);

		LocalDateTime fechaCompleta = LocalDateTime.of(año, mes, dia, hs, min, seg);
		return fechaCompleta;

	}

	public String obtenerFechaVenta() {
		return this.fechaTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}

	public String obtenerHoraVenta() {
		return this.fechaTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	public boolean estaEntreFechaInicioFin(String fechaInicio, String fechaFin) {
		String splitFecha = "/";
		String[] fechaInicioSplit = fechaInicio.split(splitFecha);
		String[] fechaFinSplit = fechaFin.split(splitFecha);
		String[] fechaVentaSplit = this.obtenerFechaVenta().split(splitFecha);

		LocalDate fechaI = LocalDate.of(Integer.parseInt(fechaInicioSplit[2]), Integer.parseInt(fechaInicioSplit[0]),
				Integer.parseInt(fechaInicioSplit[1]));
		LocalDate fechaF = LocalDate.of(Integer.parseInt(fechaFinSplit[2]), Integer.parseInt(fechaFinSplit[0]),
				Integer.parseInt(fechaFinSplit[1]));
		LocalDate fechaV = LocalDate.of(Integer.parseInt(fechaVentaSplit[2]), Integer.parseInt(fechaVentaSplit[0]),
				Integer.parseInt(fechaVentaSplit[1]));
		if (fechaV.isAfter(fechaI) && fechaV.isBefore(fechaF)) {
			return true;
		} else
			return false;

	}

}
