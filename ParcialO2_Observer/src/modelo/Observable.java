package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

	private List<Observer> observadores;

	public Observable() {
		observadores = new ArrayList<Observer>();
	}

	public void agregarObservador(Observer obs) {
		this.observadores.add(obs);
	}

	protected void notificar(String valor, String emailObserver) {
		for (Observer observer : observadores) {
			observer.actualizar(valor, emailObserver);
		}
	}
}