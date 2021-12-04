package logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ucn.*;

public class App {
	
	
	/**
	 * procedure that reads the course text file
	 * @param system
	 * @throws IOException
	 */
	
	public static void leerAsignaturas(SystemI system) throws FileNotFoundException {
		Scanner s = new Scanner(new File("Asignaturas.txt"));
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
		}
		system.añadirCodeToAsignatura();
		s.close();
	}
	
	/**
	 * procedure that reads the Students text file
	 * @param system
	 * @throws IOException
	 */
	public static void leerEstudiantes(SystemI system) throws FileNotFoundException{
		Scanner s = new Scanner(new File("Estudiantes.txt"));
		while(s.hasNextLine()) {
			String linea = s.nextLine();
			String [] partes = linea.split(",");
			String rut = partes[0];
			String correo = partes[1];			
			int nivelAlumno = Integer.parseInt(partes[2]);
			String contraseña = partes[3];
			try {
				boolean ingresoEstudiante = system.ingresarEstudiante(rut, correo, contraseña, nivelAlumno);
				if(ingresoEstudiante) {
					linea = s.nextLine();
					int cantAsignaturasCursadas = Integer.parseInt(linea);
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
					}
					linea = s.nextLine();
					int cantAsigInscritas = Integer.parseInt(linea);
					for(int j=0;j<cantAsigInscritas;j++) {
						linea = s.nextLine();
						String [] partes5 = linea.split(",");
						String codigoAsignatura =partes5[0];
						int numeroParalelo = Integer.parseInt(partes5[1]);
						try {
							boolean ingresoInscrita = system.ingresarAsociarAsignaturaInscrita(rut, codigoAsignatura, numeroParalelo);
							if(!ingresoInscrita) {
								System.out.println("No se pudo ingresar la asignatura Inscrita");
							}
						}catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}catch (Exception ex) {
				System.out.println("\t"+ex.getMessage());
			}
		}
		s.close();
	}
	

	
	/**
	 * procedure that reads the teachers text file
	 * @param system
	 * @throws IOException
	 */
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
	
	
	/**
	 * procedure that reads the parallels text file
	 * @param system
	 * @throws IOException
	 */
	public static void leerParalelos(SystemI system) throws IOException {
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
		}
		s.close();
	}
	
	/**
	 * procedure used to login
	 * @param system
	 * return true if the password is "GHI_789" or if the password belongs to an account
	 * else return false
	 */
	
	public static boolean inicioSesion(SystemI system) {
		System.out.println("BIENVENIDO AL GESTOR DE ACTIVIDADES UCR");
		System.out.println();
		System.out.print("Ingrese el correo: ");
		String nombreCorreo = leer.nextLine();
		if(nombreCorreo.equalsIgnoreCase("Admin")) {
			System.out.print("Ingrese la contraseña: ");
			String contraseña = leer.nextLine();
			if(contraseña.equalsIgnoreCase("GHI_789")) {
				menuAdmin(system);
				return true;
			}else {
				System.out.println("Contraseña incorrecta....");
				return false;
			}
		}
		else{
			boolean existeLaCuenta = system.existeCorreo(nombreCorreo);
			if(existeLaCuenta) {
				String [] tipoCuenta = nombreCorreo.split("@");
				if(tipoCuenta[1].equals("alumnos.ucn.cl")) {
					System.out.print("Ingrese la contraseña: ");
					String contraseña = leer.nextLine();
					
					boolean contraCorrect = system.contraseñaCorrecta(nombreCorreo, contraseña);
					if(contraCorrect) {
						System.out.print("Ingrese la fecha (dia/mes): ");
						String fecha = leer.nextLine();
						String rut = system.obtenerRutPersona(nombreCorreo,contraseña);
						menuAlumno(system, rut,fecha);
						return true;
					}
					else {
						System.out.println("Contraseña incorrecta...");
						return false;
					}
				}
				if(tipoCuenta[1].equals("ucn.cl")) {
					
					System.out.print("Ingrese la contraseña: ");
					String contraseña = leer.nextLine();
					boolean contraCorrect = system.contraseñaCorrecta(nombreCorreo, contraseña);
					
					if(contraCorrect) {
						System.out.print("Ingrese la fecha: ");
						String fecha = leer.nextLine();
						String rut = system.obtenerRutPersona(nombreCorreo,contraseña);
						menuProfesor(system, rut,fecha);
						return true;
					}
					else {
						System.out.println("Contraseña incorrecta...");
						return false;
					}
				}
			}					
		}
		return false;
	}
	
	/**
	 * This procedure shows all the options available for the student when you log in.
	 * @param system
	 * @param rut
	 * @param fecha
	 */
	public static void menuAlumno(SystemI system,String rut,String fecha) {
		boolean cierre = true;
		System.out.println();
		System.out.println("Bienvenido al Menu Estudiante");
		System.out.println("");
		
		
		while(cierre) {
			String [] partes = fecha.split("/");	
			int dia = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			
			//Vacaciones
			if((mes<=3&& dia<8)||(mes>=7&&dia>26)) {
				System.out.println("Disfrute sus vacaciones");	
				System.out.println("");
			}
			//Inicio Semestre
			if((mes>=3 && dia>=8)||(mes<=5&&dia<=2)) {
				System.out.println("MENU INICIO DE SEMESTRE");
				System.out.println();
				System.out.println("A) Inscribir Asignaturas");
				System.out.println("B) Eliminar Asignaturas");
				System.out.print("¿Que accion desea realizar?:");
				String respuesta = leer.nextLine();
				System.out.println();
				if(respuesta.equalsIgnoreCase("A")) {				
					//
					System.out.println("Asignaturas Obligatorias Disponibles");
					System.out.println("");
					System.out.println(system.obtenerAsignaturasObligatoriasDisponibles(rut));	
					System.out.println("");
					System.out.println("Asignaturas Opcionales Disponibles");
					System.out.println("");
					System.out.println(system.obtenerAsignaturasOpcionalesDisponibles(rut));		
					System.out.println("");
					System.out.print("Que asignatura desea inscribir?: ");
					String codigoAsignatura = leer.nextLine();
					System.out.println("El o los paralelos disponibles para esta asignatura son:");
					System.out.println(system.obtenerParalelosDisponibles(codigoAsignatura));
					System.out.println("");
					int numeroParalelo = leer.nextInt();
					try {
						boolean inscrito = system.inscribirAsignatura(rut,codigoAsignatura,numeroParalelo);
						if(inscrito) {
							System.out.println("Asignatura inscrita con exito");
							System.out.println("");
						}
					}catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("");
					}
				}
				if(respuesta.equalsIgnoreCase("B")) {
					
					System.out.println("Asignaturas Inscritas");
					System.out.println("");
					System.out.println(system.obtenerAsignaturasInscritas(rut));
					System.out.println("");
					System.out.println("¿Que asignatura desea eliminar?: ");
					String codigoAsignatura = leer.nextLine();
					try {
						boolean eliminado = system.eliminarAsignaturaInscrita(rut,codigoAsignatura);
						System.out.println("Asignatura eliminada con exito");
						System.out.println("");
					}catch(Exception e) {
						System.out.println(e.getMessage());
						System.out.println("");
					}
				}
			}
			//Mitad Semestre
			if((mes>=5&& dia>=3)||(mes<=7&&dia<=17)) {
				System.out.println("Asignaturas Inscritas");
				System.out.println("");
				System.out.println(system.obtenerAsignaturasInscritas(rut));
				System.out.println("");
				System.out.println("¿Que asignatura desea eliminar?: ");
				String codigoAsignatura = leer.nextLine();
				try {
					boolean eliminado = system.eliminarAsignaturaInscrita(rut,codigoAsignatura);
					System.out.println("Asignatura eliminada con exito");
					System.out.println("");
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("");
				}
			}
			//Final Semestre
			if((mes>=7&&dia>=12)||(mes<=7&&dia<=25)) {
				System.out.println("No hay acciones disponibles");
				System.out.println("");
			}
			//Cierre Semestre
			if(mes==7&&dia==26) {
				System.out.println("No hay acciones disponibles");
				System.out.println("");
			}
			
			
			System.out.print("queri cerrar la wea?");
			String respuesta = leer.nextLine();
			if(respuesta.equals("si")){
				cierre = false;
			}
		}
	}
	
	/**
	 * This procedure shows all the options available for the teachers when you log in.
	 * @param system
	 * @param rutProfesor
	 * @param fecha
	 */
	public static void menuProfesor(SystemI system,String rutProfesor,String fecha) {
		boolean cierre = true;
		System.out.println();
		System.out.println("Bienvenido al Menu de Profesores");
		System.out.println("");
		
		while(cierre) {

			String [] partes = fecha.split("/");	
			int dia = Integer.parseInt(partes[0]);
			int mes = Integer.parseInt(partes[1]);
			
			//Vacaciones
			if((mes<=3&& dia<8)||(mes>=7&&dia>26)) {
				System.out.println("Disfrute sus vacaciones");	
				System.out.println("");
			}
			//Inicio Semestre
			if((mes>=3 && dia>=8)||(mes<=5&&dia<=2)) {
				System.out.println("Paralelos que dicta:");
				System.out.println("");
				System.out.println(system.obtenerParalelosDictados(rutProfesor));
				System.out.println("");
				System.out.println("¿Que paralelo desea seleccionar?");
				int numeroParalelo = leer.nextInt();
				System.out.println("");
				System.out.println("Alumnos del Paralelo:");
				System.out.println("");
				System.out.println(system.obtenerAlumnosDeParalelo(numeroParalelo,rutProfesor));
				//System.out.println(e.getMessage());
				System.out.println("");
					
			}
			//Mitad Semestre
			if((mes>=5&& dia>=3)||(mes<=7&&dia<=17)) {
				System.out.println("No hay acciones disponibles");
				System.out.println("");
			}
			//Final Semestre
			if((mes>=7&&dia>=12)||(mes<=7&&dia<=25)) {
				System.out.println("Paralelos:");
				System.out.println("");
				System.out.println(system.obtenerParalelosDictados(rutProfesor));
				System.out.println("");
				System.out.println("¿Que paralelo desea seleccionar?");
				int numeroParalelo = leer.nextInt();
				System.out.println("Ingrese el codigo de la Asignatura: ");
				String codigoAsignatura = leer.nextLine();
				System.out.println("");
				try {
					System.out.println("Alumnos del Paralelo:");
					System.out.println("");
					System.out.println(system.obtenerAlumnosDeParalelo(numeroParalelo,rutProfesor));
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("");
				}
				System.out.print("Ingrese el rut del Estudiante: ");
				String rutEstudiante = leer.nextLine();
				System.out.print("Ingrese la nota: ");
				int notaFinal = leer.nextInt();
				
				try {
					boolean notaIngresada = system.ingresarNotaFinal(numeroParalelo,codigoAsignatura,rutEstudiante,notaFinal);
				}catch(Exception e) {
					System.out.println(e.getMessage());
					System.out.println("");
				}	
			}
		}
	}
	
	/**
	 * This procedure shows all the options available for the Admin when you log in.
	 * @param system
	 */
	public static void menuAdmin(SystemI system) {
		System.out.println("Informacion del semestre consolidada...");
		System.out.println("Archivo de estudiantes egresados finalizado con exito");
		//sobreescribir
	}
	
	
	
	public static Scanner leer = new Scanner(System.in);
	
	
	/**
	 * main function
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SystemI system = new SystemImpl();
		leerAsignaturas(system);
		leerProfesores(system);
		leerParalelos(system);
		leerEstudiantes(system);
		while(true) {
			boolean usuario = inicioSesion(system);
			if(usuario) {
				System.out.println("Desea cerrar el sistema? (Si) o (No)");
				String resp = leer.nextLine();
				while(!resp.equalsIgnoreCase("Si") && !resp.equalsIgnoreCase("No")) {
					System.out.println("Desea cerrar el sistema? (Si) o (No)");
					resp = leer.nextLine();
				}
				if(resp.equalsIgnoreCase("Si")) {
					break;
				}
			}	
		}
		leer.close();

		sobreescribir(system);
	}
	/**
	 * It will overwrite the new data in the corresponding txt
	 * @param system
	 * @throws IOException
	 */
	public static void sobreescribir(SystemI system) throws IOException {
		ArchivoSalida arch = new ArchivoSalida("Egresados.txt");
		Registro registroSalida = new Registro(1);
		registroSalida.agregarCampo(system.estudiantesEliminados());
		arch.writeRegistro(registroSalida);
		arch.close();
	}
	

}
