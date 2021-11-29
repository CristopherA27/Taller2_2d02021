package logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ucn.*;

public class App {
	
	public static void leerAsignaturas(SystemI system) throws FileNotFoundException {
		Scanner s = new Scanner(new File("Asignaturas.txt"));
		System.out.println("Leyendo asignaturas");
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String codigoAsignatura = partes[0];
			String nombreAsignatura = partes[1];
			int cantCreditos = Integer.parseInt(partes[2]);
			String tipoAsignatura = partes[3];
			if(tipoAsignatura.equalsIgnoreCase("Obligatoria")) {
				int nivelMalla = Integer.parseInt(partes[4]);
				int cantAsignaturasPrerrequisito = Integer.parseInt(partes[5]);
				try {
					boolean ingreso=system.ingresarAsignaturaObligatoria(codigoAsignatura, nombreAsignatura, cantCreditos, nivelMalla, cantAsignaturasPrerrequisito);
					if(ingreso) {
						for(int i=6;i<cantAsignaturasPrerrequisito;i++){
							String codigoBuscado = partes[i];
							try {
								boolean ingresarCodigo = system.asociarCodigosToAsignaturaObligatoria(codigoAsignatura, cantAsignaturasPrerrequisito, codigoBuscado);
								if(!ingresarCodigo) {
									System.out.println("No se ingreso el codigo a la lista de asignaturas de la asignatura obligatoria");
								}
							}catch (Exception e) {
								System.out.println("\t"+e.getMessage());
							}
						}
					}
				}catch (Exception ex) {
					System.out.println("\t"+ex.getMessage());
				}
			}else if(tipoAsignatura.equalsIgnoreCase("Opcional")) {
				int cantCreditosPrerrequisitos = Integer.parseInt(partes[4]);
				try {
					boolean ingresada = system.ingresarAsignaturaOpcional(codigoAsignatura, nombreAsignatura, cantCreditos, cantCreditosPrerrequisitos);
					if(!ingresada) {
						System.out.println("No se pudo ingresar la asignatura opcional");
					}
				}catch (Exception e) {
					System.out.println("\t"+e.getMessage());
				}
			}
			//System.out.println(line);
		}
		s.close();
	}
	
	
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
		s.close();
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
		arch.close();
	}

	
	public static void main(String[] args) throws IOException {
		SystemI system = new SystemImpl();
		leerAsignaturas(system);
		System.out.println();

	}
	
	

}
