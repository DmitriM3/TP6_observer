package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Properties;

import modelo.RegistroDeVentas;
import modelo.Venta;
import modelo.exceptions.RegistroDeVentasException;

public class RegistroEnDisco implements RegistroDeVentas {

	Properties props;

	public RegistroEnDisco(Properties properties) {
		this.props = properties;
	}

	@SuppressWarnings("resource")
	public void registrarVenta(Venta unaVenta) throws RegistroDeVentasException {
		try {
			if (new FileReader(new File(props.getProperty("ruta"))) != null) {
				String registro = unaVenta.obtenerDetalleVenta();
				Files.write(Paths.get(props.getProperty("ruta")), registro.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (FileNotFoundException e) {
			throw new RegistroDeVentasException("La ruta especificada al archivo no se encuentra.", e);
		} catch (IOException e) {
			throw new RegistroDeVentasException("No se pudo persistir", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al insertar la venta", e);
		}
	}

	public ArrayList<Venta> obtenerRegistroDeVentas() throws RegistroDeVentasException {
		ArrayList<Venta> misVentas = new ArrayList<Venta>();
		BufferedReader br = null;
		try {
			File archivo = new File(props.getProperty("ruta"));
			br = new BufferedReader(new FileReader(archivo));
			String split = ", ";
			String texto = br.readLine();
			while (texto != null) {
				String[] texto1 = texto.split(split);
				misVentas.add(new Venta(texto1[2], Double.parseDouble(texto1[3]), texto1[0], texto1[1]));
				texto = br.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new RegistroDeVentasException("La ruta especificada al archivo no se encuentra.", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al obtener el registro de ventas.", e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
				throw new RegistroDeVentasException("Error al finalizar.", e);
			}
		}

		return misVentas;
	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentasEntreFechas(String fechaInicio, String fechaFin)
			throws RegistroDeVentasException {
		ArrayList<Venta> misVentas = new ArrayList<Venta>();
		Venta miVenta;
		BufferedReader br = null;
		try {
			File archivo = new File(props.getProperty("ruta"));
			br = new BufferedReader(new FileReader(archivo));
			String split = ", ";
			String texto = br.readLine();
			while (texto != null) {
				String[] texto1 = texto.split(split);
				miVenta = new Venta(texto1[2], Double.parseDouble(texto1[3]), texto1[0], texto1[1]);
				if (siVentaEntreFechas(miVenta, fechaInicio, fechaFin)) {
					misVentas.add(miVenta);
				}
				texto = br.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new RegistroDeVentasException("La ruta especificada al archivo no se encuentra.", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al obtener el registro de ventas.", e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
				throw new RegistroDeVentasException("Error al finalizar.", e);
			}
		}

		return misVentas;
	}

	private boolean siVentaEntreFechas(Venta venta, String fechaInicio, String fechaFin) {
		return venta.estaEntreFechaInicioFin(fechaInicio, fechaFin);

	}

	@Override
	public String verContenido() {
		// TODO Auto-generated method stub
		return null;
	}
}
