package Servicio;

import java.util.Random;

import model.ClimaOnline;

public class WeatherChannelService implements ClimaOnline {
	@Override
	public String temperatura() {
		int temp = new Random().nextInt(50);
		return temp + " c";
	}

}
