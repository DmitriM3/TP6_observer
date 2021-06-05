package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.exceptions.CantidadLitrosInvalidaException;
import modelo.exceptions.FechaInvalidaException;
import modelo.exceptions.RegistroDeVentasException;

public class EstacionServicio extends Observable {

	private RegistroDeVentas miRegistro;
	private Map<String, Combustible> misCombustibles = new HashMap<String, Combustible>();

	public EstacionServicio(RegistroDeVentas miRegistro, List<Observer> sujetos) {
		this.miRegistro = miRegistro;
		this.misCombustibles.put("Comun", new Comun());
		this.misCombustibles.put("Super", new Super());
		for (Observer observer : sujetos) {
			this.agregarObservador(observer);
		}
	}

	public void realizarVenta(String litros, String tipoCombustible, LocalDateTime fecha, String emailObserver)
			throws RegistroDeVentasException, CantidadLitrosInvalidaException {

		double monto = this.calcularMonto(litros, tipoCombustible, fecha);
		Venta miVenta = new Venta(litros, monto, fecha);
		miRegistro.registrarVenta(miVenta);
		this.notificar(miVenta.obtenerDetalleVenta(), emailObserver);

	}

	public double calcularMonto(String litros, String tipoCombustible, LocalDateTime fecha)
			throws CantidadLitrosInvalidaException {
		if (!litrosMayorACero(litros)) {
			throw new CantidadLitrosInvalidaException("La cantidad de litros debe ser mayor a 0");
		} else {
			Combustible tipo = this.misCombustibles.get(tipoCombustible);
			return tipo.calcularMontoTotalAPagar(litros, fecha);
		}
	}

	public ArrayList<Venta> obtenerMisVentas() throws RegistroDeVentasException {
		return miRegistro.obtenerRegistroDeVentas();

	}

	public ArrayList<Venta> obtenerMisVentasConFiltroFechaInicioFin(String fechaInicio, String fechaFin)
			throws RegistroDeVentasException {
		return miRegistro.obtenerRegistroDeVentasEntreFechas(fechaInicio, fechaFin);
	}

	public void validacionDeFechas(String fechaI, String fechaF) throws FechaInvalidaException {
		if (!validarFecha(fechaI)) {
			throw new FechaInvalidaException(
					"La fecha debe ingresarse de la siguiente forma: mm/dd/yyyy" + "\n" + "Ejemplo : 01/25/2021");
		}
		if (!validarFecha(fechaF)) {
			throw new FechaInvalidaException(
					"La fecha debe ingresarse de la siguiente forma: mm/dd/yyyy" + "\n" + "Ejemplo : 01/25/2021");
		}
	}

	private boolean validarFecha(String fecha) {
		String regex = "\\d{2}/\\d{2}/\\d{4}";
		return fecha.matches(regex);
	}

	private boolean litrosMayorACero(String litrosCargados) {
		if (Integer.parseInt(litrosCargados) > 0) {
			return true;
		} else
			return false;
	}

	public String obtenerDetalleVentaEnMemoria() {
		return this.miRegistro.verContenido();
	}

}
