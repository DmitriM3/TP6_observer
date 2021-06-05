package main;

import java.util.List;
import java.util.Properties;

import modelo.EstacionServicio;
import observers.SendEmail;
import persistencia.RegistroEnBaseDeDatos;
import ui.VentanaPrincipal;

public class MainBaseDeDatos {

	public static void main(String[] args) {

		// Configuracion de acceso a la base de datos con JDBC
		Properties propsEnBD = new Properties();
		propsEnBD.setProperty("driver", "com.mysql.jdbc.Driver");
		propsEnBD.setProperty("dburl", "jdbc:mysql://localhost:3306/parcial_objetos2");
		propsEnBD.setProperty("user", "root");
		propsEnBD.setProperty("password", "");

		VentanaPrincipal ui2 = new VentanaPrincipal(
				new EstacionServicio(new RegistroEnBaseDeDatos(propsEnBD), List.of(new SendEmail())));
		ui2.iniciarVentana();
	}

}
