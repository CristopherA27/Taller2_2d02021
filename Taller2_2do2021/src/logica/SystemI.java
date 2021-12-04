package logica;

public interface SystemI {
	//ingresar
	public boolean ingresarEstudiante(String rut,String correo,String contraseña,int nivelAlumno);
	public boolean ingresarProfesor(String rut,String correo,String contraseña,int salario);
	public boolean ingresarAsignaturaObligatoria(String codigoAsignatura,String nombreAsignatura,int cantCreditos,int nivelMalla,int cantAsignaturasPrerrequisito);
	public boolean ingresarAsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int cantCreditosPrerrequisitos);
	public boolean ingresarParalelo(int numeroParalelo,String codigoAsignatura,String rutProfesor);
	public boolean ingresarAsociarAsignaturaCursada(String rutEstudiante,String codigoAsignatura,double notaFinal);
	public boolean ingresarAsociarAsignaturaInscrita(String rutEstudiante,String codigoAsignatura,int numeroParalelo);
	
	//Asociar codigos de asignatura a ASIGNATURAOBLIGATORAIA
	public boolean asociarCodigosToAsignaturaObligatoria(String codigoAsignatura,String codigoBuscado);
	public void añadirCodeToAsignatura();
	
	//INICIO MENUS 
	//RF4 ESTUDIANTE
	public String obtenerAsignaturasObligatoriasDisponibles(String rut);
	public String obtenerAsignaturasOpcionalesDisponibles(String rut);
	
	public String obtenerParalelosDisponibles(String codigoAsignatura);
	public boolean inscribirAsignatura(String rutEstudiante,String codigoAsignatura,int numeroParalelo);
	
	//RF4 PROFE
	public String obtenerParalelosDictados(String rutProfesor);
	public String obtenerAlumnosDeParalelo(int numeroParalelo,String rutProfesor);
	
	//RF5
	public String obtenerAsignaturasInscritas(String rutEstudiante);
	public boolean eliminarAsignaturaInscrita(String rutEstudiante,String codigoAsignatura);
	
	//RF6
	//OBTENERPARALELOSDICTADOS
	public boolean ingresarNotaFinal(int numeroParalelo,String codigoAsignatura,String rutEstudiante,double notaFinal);
	public boolean eliminarEstudiante();
	
	//SOBREESCRIBIR
	public String estudiantesEliminados();
	
	//
	public boolean existeCorreo(String correo);
	public boolean contraseñaCorrecta(String nombreCorreo, String contraseña);
	public String obtenerRutPersona(String nombreCorreo, String contraseña);
	
	
}
