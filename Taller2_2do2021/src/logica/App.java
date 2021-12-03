package logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ucn.*;

public class App {
	
	public static void leerAsignaturas(SystemI system) throws FileNotFoundException {
		Scanner s = new Scanner(new File("Asignaturas.txt"));
		//System.out.println("Leyendo asignaturas");
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
						for(int i=6;i<6+cantAsignaturasPrerrequisito;i++){
							String codigoBuscado = partes[i];
							try {
								boolean ingresarCodigo = system.asociarCodigosToAsignaturaObligatoria(codigoAsignatura, codigoBuscado);
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
		system.añadirCodeToAsignatura();
		s.close();
	}
	/*
	public static String rutMejorado(String rutNormal){
		String remplazado = rutNormal.replace(".","");
		remplazado = remplazado.replace("-","");
		remplazado = remplazado.replace("K","k");
		return remplazado;
	}
	
	public static String correoMejorado(String correo) {
		String remplazo = correo.replace("@","");
		remplazo = correo.replace(".","");
		return remplazo;
	}*/
	
	public static void leerEstudiantes(SystemI system) throws FileNotFoundException{
		//System.out.println("Leyendo Estudiantes");
		Scanner s = new Scanner(new File("Estudiantes.txt"));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String [] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];			
			int nivelAlumno = Integer.parseInt(partes[2]);
			String contraseña = partes[3];
			//System.out.println(linea);
			try {
				boolean ingresoEstudiante = system.ingresarEstudiante(rut, correo, contraseña, nivelAlumno);
				if(ingresoEstudiante) {
					linea = s.nextLine();
					int cantAsignaturasCursadas = Integer.parseInt(linea);
					//System.out.println(linea);
					for(int i=0;i<cantAsignaturasCursadas;i++) {
						linea= s.nextLine();
						String [] partes3 = linea.split(",");
						String codigo = partes3[0];
						double notaFinal = Double.parseDouble(partes3[1]);
						try {
							boolean ingresoCursada = system.ingresarAsociarAsignaturaCursada(rut, codigo, notaFinal);
							if(!ingresoCursada) {
								System.out.println("No se pudo ingresar la asignatura cursada");
							}
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
						//System.out.println(linea);
					}
					linea = s.nextLine();
					int cantAsigInscritas = Integer.parseInt(linea);
					//System.out.println(linea);
					for(int j=0;j<cantAsigInscritas;j++) {
						linea = s.nextLine();
						String [] partes5 = linea.split(",");
						String codigoAsignatura =partes5[0];
						int numeroParalelo = Integer.parseInt(partes5[1]);
						try {
							boolean ingresoInscrita = system.ingresarAsociarAsignaturaInscrita(rut, codigoAsignatura, numeroParalelo);
							if(!ingresoInscrita) {
								System.out.println("No se pudo ingresar la asignatura Inscrita+-");
							}
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
						//System.out.println(linea);
					}
				}
			}catch (Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
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
		//System.out.println("Leyendo paralelos");
		Scanner s = new Scanner(new File("Paralelos.txt"));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String [] partes = linea.split(",");
			int numeroParalelo = Integer.parseInt(partes[0]);
			String codigoAsignatura = partes[1];
			String rutProfesor = partes[2];
			try {
				boolean ingresoParalelo = system.ingresarParalelo(numeroParalelo, codigoAsignatura, rutProfesor);
				if(!ingresoParalelo) {
					System.out.println("c");
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

			//System.out.println(linea);
		}
		s.close();
	}

	
	public static void main(String[] args) throws IOException {
		SystemI system = new SystemImpl();
		leerAsignaturas(system);
		leerProfesores(system);
		leerParalelos(system);
		leerEstudiantes(system);
		String rutP = "123456789";
		String rutE = "123456k";
		//System.out.println(system.obtenerParalelosDictados(rutP));
		system.a();
		//system.b();
	}
	
	public static void sobreescribir(SystemI system) throws IOException {
		ArchivoSalida arch = new ArchivoSalida("Egresados.txt");
		Registro registroSalida = new Registro(1);
		registroSalida.agregarCampo(false);
		arch.writeRegistro(registroSalida);
		arch.close();
	}
	

}
