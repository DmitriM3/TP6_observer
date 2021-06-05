package main;

import java.util.List;
import java.util.Properties;

import modelo.EstacionServicio;
import observers.SendEmail;
import persistencia.RegistroEnDisco;
import ui.VentanaPrincipal;

public class MainDisco {

	public static void main(String[] args) {

		// Configuracion de ruta del archivo en disco
		Properties propsEnDisco = new Properties();
		propsEnDisco.setProperty("ruta", "./resources/registroVentas.txt");

//		VentanaPrincipal ui2 = new VentanaPrincipal(new EstacionServicio(new RegistroEnDisco(propsEnDisco)));
//		ui2.iniciarVentana();

		new VentanaPrincipal(new EstacionServicio(new RegistroEnDisco(propsEnDisco), List.of(new SendEmail())))
				.iniciarVentana();

	}

}
