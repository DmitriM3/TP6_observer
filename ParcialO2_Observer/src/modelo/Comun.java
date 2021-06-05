package modelo;

import java.time.LocalDateTime;

public class Comun extends Combustible {

	private final double descuento5 = 0.95; // 5% de descuento

	public Comun() {
		super(70.0);
	}

	public double calcularMontoTotalAPagar(String litros, LocalDateTime fecha) {
		int litrosCargados = Integer.parseInt(litros);
		if (horarioEntre8y10AM(fecha)) {
			return (obtenerPrecioPorCantidadDeLitros(litrosCargados) * descuento5);
		} else
			return obtenerPrecioPorCantidadDeLitros(litrosCargados);
	}

	private boolean horarioEntre8y10AM(LocalDateTime fecha) {
		int horaDelDia = fecha.getHour();
		if ((horaDelDia >= 8) && (horaDelDia <= 10)) {
			return true;
		} else {
			return false;
		}
	}

}
