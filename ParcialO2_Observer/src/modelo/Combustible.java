package modelo;

import java.time.LocalDateTime;

public abstract class Combustible {

	private double precioXLitro;

	public Combustible(double precioXLitro) {
		this.precioXLitro = precioXLitro;
	}

	public double calcularMontoTotalAPagar(String litros) {
		return 0;
	}

	public double obtenerPrecioPorCantidadDeLitros(int litros) {
		return (this.precioXLitro * litros);
	}

	public double calcularMontoTotalAPagar(String litros, LocalDateTime fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

}
