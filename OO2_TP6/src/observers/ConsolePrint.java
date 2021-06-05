package observers;

import model.Observer;

public class ConsolePrint implements Observer {

	@Override
	public void actualizar(String valor) {
		System.out.println(valor);
		if (temperaturaMenor12(valor)) {
			System.out.println("Hace frio, se encenderá la caldera");
		} else if (temperaturaMayor17(valor)) {
			System.out.println("Hace calor, se encenderá el aire acondicionado");
		} else
			System.out.println("La temperatura esta bien");
	}

	private boolean temperaturaMayor17(String valor) {
		if (Integer.parseInt(valor.split(" ")[0]) > 17) {
			return true;
		} else
			return false;
	}

	private boolean temperaturaMenor12(String valor) {
		if (Integer.parseInt(valor.split(" ")[0]) < 12) {
			return true;
		} else
			return false;
	}

}
