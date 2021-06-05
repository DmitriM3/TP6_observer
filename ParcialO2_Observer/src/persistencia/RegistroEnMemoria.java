package persistencia;

import java.util.ArrayList;

import modelo.RegistroDeVentas;
import modelo.Venta;
import modelo.exceptions.RegistroDeVentasException;

public class RegistroEnMemoria implements RegistroDeVentas {

	private String registro;

	@Override
	public void registrarVenta(Venta unaVenta) throws RegistroDeVentasException {
		this.registro = unaVenta.obtenerDetalleVenta();

	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentas() throws RegistroDeVentasException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentasEntreFechas(String fechaInicio, String fechaFin)
			throws RegistroDeVentasException {
		// TODO Auto-generated method stub
		return null;
	}

	public String verContenido() {
		return this.registro;
	}

}
