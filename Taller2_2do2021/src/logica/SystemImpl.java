package logica;
import dominio.*;
import sun.jvm.hotspot.ui.action.FindClassesAction;          

public class SystemImpl implements SystemI{
	
	private ListaPersonas lpersonas;
	private ListaAsignaturas lasignaturas;
	private ListaParalelos lparalelos;
	
	public SystemImpl() {
		lpersonas = new ListaPersonas(100);
		lasignaturas = new ListaAsignaturas(100);
		lparalelos = new ListaParalelos(100);
	}

	@Override
	public boolean ingresarEstudiante(String rut, String correo, String contraseña,int nivelAlumno) {
		Persona estudiante = new Estudiante(rut, correo, contraseña, nivelAlumno);
		boolean ingresado = lpersonas.ingresar(estudiante);
		return ingresado;
	}

	@Override
	public boolean ingresarProfesor(String rut, String correo, String contraseña, int salario) {
		Persona profesor = new Profesor(rut, correo, contraseña, salario);
		boolean ingresado = lpersonas.ingresar(profesor);
		return ingresado;
	}

	@Override
	public boolean ingresarAsignaturaObligatoria(String codigoAsignatura, String nombreAsignatura, int cantCreditos,
			int nivelMalla, int cantAsignaturasPrerrequisito) {
		
		return false;
	}

	@Override
	public boolean ingresarAsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,
			int cantCreditosPrerrequisitos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarParalelo(int numeroParalelo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarAsociarAsignatura(String rutEstudiante, String codigoAsignatura, double notaFinal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ingresarAsociarParaleloProfesorAsigntura(int numeroParalelo, String codigoAsignatura,
			String rutProfesor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obtenerAsignaturasDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerParalelosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inscribirAsignatura(String rutEstudiante, String codigoAsignatura, int numeroParalelo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obtenerAsignaturasInscritas(String rutEstudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarAsignatura(String rutEstudiante, String codigoAsignatura) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String obtenerParalelosDictados(String rutProfesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtenerAlumnosDeParalelo(int numeroParalelo, String rutProfesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ingresarNotaFinal(String codigoAsignatura, String rutEstudiante, double notaFinal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verificarAsignaturaInscritaACursada(String ruteEstudiante, String codigoAsignatura) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarEstudiante() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
