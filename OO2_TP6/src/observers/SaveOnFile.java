package observers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Observer;

public class SaveOnFile implements Observer {

	private File file = new File("./Files/miArchivo.txt");

	@Override
	public void actualizar(String valor) {
		String tempFecha = "Temperatura : " + valor + " | " + "Fecha : "
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss")) + "\n";
		try {
			Files.write(Paths.get(file.getPath()), tempFecha.getBytes(), StandardOpenOption.APPEND);
//			System.out.println("Listado guardado en disco..");
		} catch (IOException e) {
			throw new RuntimeException("Error guardando la lista en el archivo...", e);
		}

	}

}
