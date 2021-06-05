package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import modelo.RegistroDeVentas;
import modelo.Venta;
import modelo.exceptions.RegistroDeVentasException;

public class RegistroEnBaseDeDatos implements RegistroDeVentas {

	Properties props;

	public RegistroEnBaseDeDatos(Properties properties) {
		this.props = properties;
	}

	public void registrarVenta(Venta unaVenta) throws RegistroDeVentasException {
		Connection conn = null;
		try {
			Class.forName(props.getProperty("driver"));
			conn = DriverManager.getConnection(props.getProperty("dburl"), props.getProperty("user"),
					props.getProperty("password"));
			String insert = "INSERT INTO registro_venta (fecha, litros_cargados, monto_total, hora) VALUES (?, ?, ?, ?);";
			PreparedStatement prepStm = (PreparedStatement) conn.prepareStatement(insert);
			prepStm.setString(1, unaVenta.obtenerFechaVenta());
			prepStm.setString(4, unaVenta.obtenerHoraVenta());
			prepStm.setInt(2, unaVenta.obtenerCantidadLitrosCargados());
			prepStm.setDouble(3, unaVenta.consultarMontoTotal());
			prepStm.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			throw new RegistroDeVentasException("NO JDBC driver", e);
		} catch (SQLException e) {
			throw new RegistroDeVentasException("No se pudo conectar a la BD. ", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al insertar la venta", e);
		}

	}

	public ArrayList<Venta> obtenerRegistroDeVentas() throws RegistroDeVentasException {
		ArrayList<Venta> misVentas = new ArrayList<Venta>();
		Connection conn = null;
		try {
			Class.forName(props.getProperty("driver"));
			conn = DriverManager.getConnection(props.getProperty("dburl"), props.getProperty("user"),
					props.getProperty("password"));
			String query = "Select * FROM registro_venta";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				misVentas.add(new Venta(rs.getString(3), rs.getDouble(4), rs.getString(2), rs.getString(5)));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			throw new RegistroDeVentasException("NO JDBC driver", e);
		} catch (SQLException e) {
			throw new RegistroDeVentasException("No se pudo conectar a la BD. ", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al obtener las ventas", e);
		}
		return misVentas;
	}

	@Override
	public ArrayList<Venta> obtenerRegistroDeVentasEntreFechas(String fechaInicio, String fechaFin)
			throws RegistroDeVentasException {
		ArrayList<Venta> misVentas = new ArrayList<Venta>();
		Connection conn = null;
		Venta miVenta;
		try {
			Class.forName(props.getProperty("driver"));
			conn = DriverManager.getConnection(props.getProperty("dburl"), props.getProperty("user"),
					props.getProperty("password"));
			String query = "Select * FROM registro_venta";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				miVenta = new Venta(rs.getString(3), rs.getDouble(4), rs.getString(2), rs.getString(5));
				if (siVentaEntreFechas(miVenta, fechaInicio, fechaFin)) {
					misVentas.add(miVenta);
				}
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			throw new RegistroDeVentasException("NO JDBC driver", e);
		} catch (SQLException e) {
			throw new RegistroDeVentasException("No se pudo conectar a la BD. ", e);
		} catch (Exception e) {
			throw new RegistroDeVentasException("Error al obtener las ventas", e);
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
