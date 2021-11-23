package logica;

public interface SystemI {
	//ingresar
	
	public boolean ingresarEstudiante(String rut,String correo,String contraseña);
	public boolean ingresarProfesor(String rut,String correo,String contraseña,int salario);
	public boolean ingresarAsignaturaObligatoria(String codigoAsignatura,String nombreAsignatura,int cantCreditos,int nivelMalla,int cantAsignaturasPrerrequisito);
	public boolean ingresarAsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int cantCreditosPrerrequisitos);
	public boolean ingresarParalelo(int numeroParalelo);
	public boolean ingresarAsociarAsignatura(String rutEstudiante,String codigoAsignatura,double notaFinal);
	public boolean ingresarAsociarParaleloProfesorAsigntura(int numeroParalelo,String codigoAsignatura,String rutProfesor);
	
	public String obtenerAsignaturasDisponibles();
	public String obtenerParalelosDisponibles();
	
	public boolean inscribirAsignatura(String rutEstudiante,String codigoAsignatura,int numeroParalelo);
	
	public String obtenerAsignaturasInscritas(String rutEstudiante);
	
	public boolean eliminarAsignatura(String rutEstudiante,String codigoAsignatura);
	
	public String obtenerParalelosDictados(String rutProfesor);
	public String obtenerAlumnosDeParalelo(int numeroParalelo,String rutProfesor);
	
	public boolean ingresarNotaFinal(String codigoAsignatura,String rutEstudiante,double notaFinal);
	public boolean verificarAsignaturaInscritaACursada(String ruteEstudiante,String codigoAsignatura);
	
	public boolean eliminarEstudiante();
	
	
	

}
