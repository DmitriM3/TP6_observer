package modelo;

import java.util.ArrayList;

import modelo.exceptions.RegistroDeVentasException;

public interface RegistroDeVentas {

	void registrarVenta(Venta unaVenta) throws RegistroDeVentasException;

	ArrayList<Venta> obtenerRegistroDeVentas() throws RegistroDeVentasException;

	ArrayList<Venta> obtenerRegistroDeVentasEntreFechas(String fechaInicio, String fechaFin)
			throws RegistroDeVentasException;

	String verContenido();
}
