package logica;
import dominio.*;
        

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
	public boolean ingresarEstudiante(String rut, String correo, String contraseņa,int nivelAlumno) {
		Persona estudiante = new Estudiante(rut, correo, contraseņa, nivelAlumno);
		boolean ingresado = lpersonas.ingresar(estudiante);
		return ingresado;
	}

	@Override
	public boolean ingresarProfesor(String rut, String correo, String contraseņa, int salario) {
		Persona profesor = new Profesor(rut, correo, contraseņa, salario);
		boolean ingresado = lpersonas.ingresar(profesor);
		return ingresado;
	}

	@Override
	public boolean ingresarAsignaturaObligatoria(String codigoAsignatura, String nombreAsignatura, int cantCreditos,
			int nivelMalla, int cantAsignaturasPrerrequisito) {
		Asignatura asignaturaobligatoria = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, cantCreditos, nivelMalla,cantAsignaturasPrerrequisito);
		boolean ingresado = lasignaturas.ingresar(asignaturaobligatoria);
		return ingresado;
	
	}
	
	public boolean asociarCodigosToAsignaturaObligatoria(String codigoAsignatura, int cantAsignaturasPrerrequisito,String codigoBuscado) {
		Asignatura asig = lasignaturas.buscar(codigoAsignatura);
		if(asig != null && asig instanceof AsignaturaObligatoria) {
			AsignaturaObligatoria asigObligatoria = (AsignaturaObligatoria)asig;
			Asignatura asigIngresar = lasignaturas.buscar(codigoBuscado);
			asigObligatoria.getListaAsignaturas().ingresar(asigIngresar);
			/*for(int i=0;i<cantAsignaturasPrerrequisito;i++) {
				Asignatura asigIngresar = lasignaturas.buscar(codigoBuscado);
				asigObligatoria.getListaAsignaturas().ingresar(asigIngresar);
			}*/
		}
		
		/*Asignatura asignatura = lasignaturas.buscar(codigoBuscado);
		if(asignatura != null) {
			for(int i=0;i<lasignaturas.getCant();i++) {
				Asignatura asig = lasignaturas.getElementoI(i);
				if(asig instanceof AsignaturaObligatoria && asig.getCodigoAsignatura().equalsIgnoreCase( codigoAsignatura)) {
					((AsignaturaObligatoria) asig).getListaAsignaturas().ingresar(asignatura);
				}
			}
		}*/
		return false;
	}

	public boolean ingresarAsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,
			int cantCreditosPrerrequisitos) {
		Asignatura asignaturaOpcional = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, cantCreditos, cantCreditosPrerrequisitos);
		boolean ingreso = lasignaturas.ingresar(asignaturaOpcional);
		return ingreso;
	}

	@Override
	public boolean ingresarParalelo(int numeroParalelo) {
		Paralelo paralelo = new Paralelo(numeroParalelo);
		boolean ingresado = lparalelos.ingresar(paralelo);
		return false;
	}

	@Override
	public boolean ingresarAsociarAsignaturaCursada(String rutEstudiante, String codigoAsignatura, double notaFinal) {
		Persona persona = lpersonas.buscar(rutEstudiante);
		if(persona != null && persona instanceof Estudiante) {
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				Estudiante estudiante = (Estudiante) persona;
				estudiante.getAsignaturasCursadas().ingresar(asig);
				estudiante.getAsignaturasCursadas().buscar(codigoAsignatura).setNotaFinal(notaFinal);
			}else {
				throw new NullPointerException("La "+asig+" no existe");
			}
		}
		return false;
	}
	
	public boolean ingresarAsociarAsignaturaInscrita (String rutEstudiante,String codigoAsignatura,int numeroParalelo) {
		Persona persona = lpersonas.buscar(rutEstudiante);
		if(persona != null  && persona instanceof Estudiante) {
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				Estudiante estudiante = (Estudiante) persona;
				estudiante.getAsignaturasInscritas().ingresar(asig);
				Paralelo numParalelo = lparalelos.buscar(numeroParalelo);
				if(numeroParalelo != 0) {
					estudiante.getAsignaturasInscritas().buscar(codigoAsignatura).setNumeroParalelo(numParalelo);
				}else {
					throw new NullPointerException("El "+numeroParalelo+" no existe");
				}
			}else {
				throw new NullPointerException("La "+asig+" no existe");
			}
		}
		
		return false;        
	}

	@Override
	public boolean ingresarAsociarParaleloProfesorAsigntura(int numeroParalelo, String codigoAsignatura,String rutProfesor) {
		Paralelo paralelo = lparalelos.buscar(numeroParalelo);
		if(paralelo != null) {
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				Persona persona = lpersonas.buscar(rutProfesor);
				if(persona != null && persona instanceof Profesor) {
					Profesor profe = (Profesor)persona;
					paralelo.setCodigoAsignatura(asig);
					paralelo.setRutProfesor(profe);
				}else {
					throw new NullPointerException("");
				}
			}else {
				throw new NullPointerException("La asignatura "+asig+" no existe");
			}
		}else {
			throw new NullPointerException("El paralelo "+paralelo+" no existe");
		}
		return false;
	}

	//esta dudoso
	public String obtenerAsignaturasDisponibles(String rut) {
		String dato = "";
		Persona p = lpersonas.buscar(rut);
		if(p != null && p instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)p;
			for(int i=0;i<lasignaturas.getCant();i++) {
				Asignatura asig = lasignaturas.getElementoI(i);
				if(asig instanceof AsignaturaObligatoria) {
					AsignaturaObligatoria asigObliga = (AsignaturaObligatoria)asig;
					if(asigObliga.getNivelMalla() == estudiante.getNivelAlumno()) {
						dato += asigObliga.getCodigoAsignatura()+" "+asigObliga.getNombreAsignatura()+""+asigObliga.getCantCreditos()+" "+asigObliga.getCantAsignaturasPrerrequisito()+"\n";
						for(int j=0;j<asigObliga.getCantAsignaturasPrerrequisito();j++) {
							dato +="\t"+asigObliga.getListaAsignaturas().getElementoI(j);
						}
					}
				}else {
					AsignaturaOpcional asigOpcional = (AsignaturaOpcional)asig;
					dato += asigOpcional.getCodigoAsignatura()+" "+asigOpcional.getNombreAsignatura()+" "+asigOpcional.getCantCreditos()+" "+asigOpcional.getCantCreditosPrerrequisitos();
				}
			}
		}
		return dato;
	}
	//me imagino que esta bien
	public String obtenerParalelosDisponibles(String codigoAsignatura) {
		String dato = "";
		Asignatura asig = lasignaturas.buscar(codigoAsignatura);
		if(asig != null) {
			for(int i=0;i<lparalelos.getCant();i++) {
				Paralelo paralelo = lparalelos.getElementoI(i);
				if(paralelo.getCodigoAsignatura().equals(codigoAsignatura)) {
					dato += paralelo.getNumeroParalelo()+" "+paralelo.getRutProfesor()+"\n";
				}
			}
		}
		return dato;
	}

	@Override
	public boolean inscribirAsignatura(String rutEstudiante, String codigoAsignatura, int numeroParalelo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//creo que esta bien
	public String obtenerAsignaturasInscritas(String rutEstudiante) {
		String dato = "";
		Persona p = lpersonas.buscar(rutEstudiante);
		if(p != null && p instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)p;
			ListaAsignaturas la = estudiante.getAsignaturasInscritas();
			for(int i=0;i<la.getCant();i++) {
				Asignatura a = la.getElementoI(i);
				dato += a.getCodigoAsignatura()+" "+a.getNumeroParalelo()+"\n";
			}
		}
		return dato;
	}
	//verificar
	@Override
	public boolean eliminarAsignatura(String rutEstudiante, String codigoAsignatura) {
		Persona persona = lpersonas.buscar(rutEstudiante);
		if(persona != null && persona instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)persona;
			estudiante.getAsignaturasInscritas().eliminar(codigoAsignatura);
		}
		return false;
	}

	//mas seguro que los otros
	public String obtenerParalelosDictados(String rutProfesor) {
		String dato = "";
		Persona p = lpersonas.buscar(rutProfesor);
		if(p != null && p instanceof Profesor) {
			Profesor profe = (Profesor)p;
			ListaParalelos lp = profe.getListaParalelos();
			for(int i=0;i<lp.getCant();i++) {
				Paralelo paralelo = lp.getElementoI(i);
				dato += paralelo.getNumeroParalelo()+" "+paralelo.getCodigoAsignatura()+"\n";
			}
		}
		return dato;
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
