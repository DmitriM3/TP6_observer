package main;

import java.util.List;

import Servicio.WeatherChannelService;
import model.Medidor;
import observers.ConsolePrint;
import observers.SaveOnFile;

public class Main {

	public static void main(String[] args) {

//		Medidor temp = new Medidor(new WeatherChannelService());
//		System.out.println(temp.leerTemperatura());

		new Medidor(new WeatherChannelService(), List.of(new ConsolePrint(), new SaveOnFile())).leerTemperatura();

	}

}
