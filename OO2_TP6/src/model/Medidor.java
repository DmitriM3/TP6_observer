package model;

import java.util.List;

public class Medidor extends Observable {
	private String temperatura;
	private ClimaOnline clima;

	public Medidor(ClimaOnline clima, List<Observer> sujetos) {
		this.clima = clima;
		for (Observer observer : sujetos) {
			this.agregarObservador(observer);
		}
	}

	public void leerTemperatura() {
		// leo la temperatura del servicio web
		this.temperatura = this.clima.temperatura();
		this.notificar(this.temperatura);
	}

}
