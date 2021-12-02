package logica;

public interface SystemI {
	//ingresar
	
	public boolean ingresarEstudiante(String rut,String correo,String contraseña,int nivelAlumno);
	public boolean ingresarProfesor(String rut,String correo,String contraseña,int salario);
	public boolean ingresarAsignaturaObligatoria(String codigoAsignatura,String nombreAsignatura,int cantCreditos,int nivelMalla,int cantAsignaturasPrerrequisito);
	public boolean ingresarAsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int cantCreditosPrerrequisitos);
	public boolean ingresarParalelo(int numeroParalelo);
	public boolean ingresarAsociarAsignaturaCursada(String rutEstudiante,String codigoAsignatura,double notaFinal);
	public boolean ingresarAsociarAsignaturaInscrita(String rutEstudiante,String codigoAsignatura,int numeroParalelo);

	public boolean ingresarAsociarParaleloProfesorAsigntura(int numeroParalelo,String codigoAsignatura,String rutProfesor);
	
	//Asociar codigos de asignatura a ASIGNATURAOBLIGATORAIA
	public boolean asociarCodigosToAsignaturaObligatoria(String codigoAsignatura,String codigoBuscado);
	public void añadirCodeToAsignatura();
	
	//INICIO MENUS 
	public String obtenerAsignaturasObligatoriasDisponibles(String rut);
	public String obtenerAsignaturasOpcionalesDisponibles(String rut);
	
	
	
	public String obtenerParalelosDisponibles(String codigoAsignatura);
	
	
	public boolean inscribirAsignatura(String rutEstudiante,String codigoAsignatura,int numeroParalelo);
	
	public String obtenerAsignaturasInscritas(String rutEstudiante);
	public boolean eliminarAsignaturaInscrita(String rutEstudiante,String codigoAsignatura);
	
	
	public String obtenerParalelosDictados(String rutProfesor);
	public String obtenerAlumnosDeParalelo(int numeroParalelo,String rutProfesor);
	
	
	public String obtenerAsignaturasProfesor(String rutProfesor);
	public boolean ingresarNotaFinal(String codigoAsignatura,String rutEstudiante,double notaFinal);
	public boolean verificarAsignaturaInscritaACursada(String rutEstudiante,String codigoAsignatura);
	
	public boolean eliminarEstudiante();
	
	
	

}
