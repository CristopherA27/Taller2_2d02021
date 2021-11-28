package logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ucn.*;

public class App {
	
	public static void leerProfesores(SystemI system) throws FileNotFoundException {
		Scanner s = new Scanner(new File("Profesores.txt"));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String [] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];
			String contraseña = partes[2];
			int salario = Integer.parseInt(partes[3]);
			try {
				boolean ingresoProfe = system.ingresarProfesor(rut, correo, contraseña, salario);
				if(!ingresoProfe) {
					System.out.println("No se pudo ingresar al profesor por que no hay mas espacio");
				}
			}catch(Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
			
		}
	}
	
	public static void leerParalelos(SystemI system) throws IOException {
		System.out.println("Leyendo paralelos");
		ArchivoEntrada arch = new ArchivoEntrada("Paralelos.txt");
		while(!arch.isEndFile()) {
			Registro reg = arch.getRegistro();
			int numeroParalelo = reg.getInt();
			String codigoAsignatura = reg.getString();
			String rutProfesor = reg.getString();
			try {
				boolean ingresoAsocia = system.ingresarAsociarParaleloProfesorAsigntura(numeroParalelo, codigoAsignatura, rutProfesor);
				if(!ingresoAsocia) {
					System.out.println("No existe espacio para ingresar mas paralelos");
				}
			}catch(Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		SystemI system = new SystemImpl();

	}
	
	

}
