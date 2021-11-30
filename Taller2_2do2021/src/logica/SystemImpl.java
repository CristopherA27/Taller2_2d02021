package logica;
import java.util.Scanner;

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
		Asignatura asignaturaobligatoria = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, cantCreditos, nivelMalla,cantAsignaturasPrerrequisito);
		boolean ingresado = lasignaturas.ingresar(asignaturaobligatoria);
		return ingresado;
	
	}
	
	public boolean asociarCodigosToAsignaturaObligatoria(String codigoAsignatura,String codigoBuscado) {
		Asignatura asig = lasignaturas.buscar(codigoAsignatura);
		if(asig != null && asig instanceof AsignaturaObligatoria) {
			AsignaturaObligatoria asigObligatoria = (AsignaturaObligatoria)asig;
			boolean a =asigObligatoria.añadirCodigo(codigoBuscado);
			return true;
		}else {
			throw new NullPointerException("La asignatura "+asig+" no existe");
		}
	}
	
	public void añadirCodeToAsignatura() {
		for(int i=0;i<lasignaturas.getCant();i++) {
			Asignatura asig = lasignaturas.getElementoI(i);
			if(asig instanceof AsignaturaObligatoria) {
				AsignaturaObligatoria asigO = (AsignaturaObligatoria)asig;
				String [] codigos = asigO.getLcodigosPrerrequisitos();
				for(int j=0;j<codigos.length;j++) {
					Asignatura asi = lasignaturas.buscar(codigos[j]);
					if(asi != null) {
						asigO.getListaAsignaturas().ingresar(asi);
					}
				}
			}
		}
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
			Estudiante estudiante = (Estudiante) persona;
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				estudiante.getAsignaturasCursadas().ingresar(asig);
				estudiante.getAsignaturasCursadas().buscar(codigoAsignatura).setNotaFinal(notaFinal);
				return true;
			}else {
				throw new NullPointerException("La "+asig+" no existe");
			}
		}else {
			throw new NullPointerException("El estudiante "+persona+" no existe");
		}
	}
	
	public boolean ingresarAsociarAsignaturaInscrita (String rutEstudiante,String codigoAsignatura,int numeroParalelo) {
		Persona persona = lpersonas.buscar(rutEstudiante);
		if(persona != null  && persona instanceof Estudiante) {
			Estudiante estudiante = (Estudiante) persona;
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				estudiante.getAsignaturasInscritas().ingresar(asig);
				Paralelo numParalelo = lparalelos.buscar(numeroParalelo);
				if(numeroParalelo != 0) {
					estudiante.getAsignaturasInscritas().buscar(codigoAsignatura).setNumeroParalelo(numParalelo);
					numParalelo.getListaPersonas().ingresar(estudiante);
					return true;
				}else {
					throw new NullPointerException("El "+numeroParalelo+" no existe");
				}
			}else {
				throw new NullPointerException("La "+asig+" no existe");
			}
		}else {
			throw new NullPointerException("El estudiante "+persona+" no existe");
		}
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
					return true;
				}else {
					throw new NullPointerException("");
				}
			}else {
				throw new NullPointerException("La asignatura "+asig+" no existe");
			}
		}else {
			throw new NullPointerException("El paralelo "+paralelo+" no existe");
		}
	}

	public String obtenerAsignaturasDisponibles(String rut) {
		String dato = "";
		Persona p = lpersonas.buscar(rut);
		if(p != null && p instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)p;
			for(int i=0;i<lasignaturas.getCant();i++) {
				Asignatura asig = lasignaturas.getElementoI(i);
				if(asig instanceof AsignaturaObligatoria) {
					AsignaturaObligatoria asigObli = (AsignaturaObligatoria)asig;
					for(int j=0;j<estudiante.getAsignaturasCursadas().getCant();j++) {
						Asignatura asigEstudiante = estudiante.getAsignaturasCursadas().getElementoI(j);
						if(asigEstudiante.getCodigoAsignatura() == asigObli.getCodigoAsignatura() && asigEstudiante.getNotaFinal() <=3.95 ) {
							dato += asigObli.getCodigoAsignatura()+" "+asigObli.getNombreAsignatura()+" "+asigObli.getCantCreditos()+" "+asigObli.getCantAsignaturasPrerrequisito();
							for(int a=0;a<asigObli.getCantAsignaturasPrerrequisito();a++) {
								dato+="\t"+asigObli.getLcodigosPrerrequisitos();
							}
						}
						if(asigObli.getNivelMalla() == estudiante.getNivelAlumno() ) {
							if(asigEstudiante.getCodigoAsignatura() == asigObli.getCodigoAsignatura() && asigEstudiante.getNotaFinal() <=3.95 ) {
								dato += asigObli.getCodigoAsignatura()+" "+asigObli.getNombreAsignatura()+" "+asigObli.getCantCreditos()+" "+asigObli.getCantAsignaturasPrerrequisito();
								for(int a=0;a<asigObli.getCantAsignaturasPrerrequisito();a++) {
									dato+="\t"+asigObli.getLcodigosPrerrequisitos();
								}
							}
						}
					}
					for(int m=0;m<estudiante.getAsignaturasInscritas().getCant();m++) {
						Asignatura asigEstu = estudiante.getAsignaturasInscritas().getElementoI(m);
						if(asigEstu.getCodigoAsignatura() != asigObli.getCodigoAsignatura()) {
							dato += asigObli.getCodigoAsignatura()+" "+asigObli.getNombreAsignatura()+" "+asigObli.getCantCreditos()+" "+asigObli.getCantAsignaturasPrerrequisito();
							for(int a=0;a<asigObli.getCantAsignaturasPrerrequisito();a++) {
								dato+="\t"+asigObli.getLcodigosPrerrequisitos();
							}
						}
					}
				//aca me quede	
				}else {
					AsignaturaOpcional asigOpcional = (AsignaturaOpcional)asig;
					
					for(int k=0;k<estudiante.getAsignaturasCursadas().getCant();k++) {
						if(estudiante.getAsignaturasCursadas().getElementoI(k).getCodigoAsignatura() != asigOpcional.getCodigoAsignatura()) {
							dato += asigOpcional.getCodigoAsignatura()+" "+asigOpcional.getNombreAsignatura()+" "+asigOpcional.getCantCreditos()+" "+asigOpcional.getCantCreditosPrerrequisitos();
						}
					}
				}
			}
			for(int k=0;k<estudiante.getAsignaturasCursadas().getCant();k++) {
				if(estudiante.getAsignaturasCursadas().getElementoI(k).getNotaFinal() <3.95) {
					dato += estudiante.getAsignaturasCursadas().getElementoI(k).getCodigoAsignatura();
				}
			}
		}
		return null;
	}
	
	
	//esta dudoso

	/*
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
					for(int k=0)
					dato += asigOpcional.getCodigoAsignatura()+" "+asigOpcional.getNombreAsignatura()+" "+asigOpcional.getCantCreditos()+" "+asigOpcional.getCantCreditosPrerrequisitos();
				}
			}
		}
		return dato;
	}*/
	
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
		String dato = "";
		Paralelo paralelo = lparalelos.buscar(numeroParalelo);
		 if(paralelo != null) {
			dato +="Los estudiantes inscritos son:"+"\n";
			for(int i=0;i<lpersonas.getCant();i++) {
				Persona p = lpersonas.getElemento(i);
				if(p instanceof Estudiante) {
					Estudiante estudiante = (Estudiante)p;
					ListaAsignaturas la = estudiante.getAsignaturasInscritas();
					for(int j=0;j<la.getCant();j++) {
						Asignatura asig = la.getElementoI(j);
						if(asig.getNumeroParalelo().equals(numeroParalelo)) {
							dato +="\t"+estudiante.getRut()+"\n";
						}
					}
				}
			}
		}
		return dato;
	}
	
	public String obtenerAsignaturasProfesor(String rutProfesor) {
		String dato ="";
		Persona p = lpersonas.buscar(rutProfesor);
		if( p != null) {
			if( p instanceof Profesor) {
				Profesor profe = (Profesor)p;
				ListaAsignaturas laProfe = profe.getListaAsignaturas();
				for(int i=0;i<laProfe.getCant();i++) {
					Asignatura asig = laProfe.getElementoI(i);
					dato +=asig.getCodigoAsignatura()+" "+asig.getNumeroParalelo()+"\n";
				}
			}
		}else {
			throw new NullPointerException("La persona "+p+"no existe");
		}
		return dato;
	}

	@Override
	public boolean ingresarNotaFinal(String codigoAsignatura, String rutEstudiante, double notaFinal) {
		Asignatura asig = lasignaturas.buscar(codigoAsignatura);
		if(asig != null) {
			Persona p = lpersonas.buscar(rutEstudiante);
			if( p != null && p instanceof Estudiante) {
				Estudiante estudiante = (Estudiante)p;
				if(estudiante.getAsignaturasInscritas().buscar(codigoAsignatura).equals(asig)) {
					asig.setNotaFinal(notaFinal);
				}
			}else {
				throw new NullPointerException("La persona "+p+" no existe");
			}
		}else {
			throw new NullPointerException("La asignatura "+asig+" no existe");
		}
		
		return false;
	}

	@Override
	public boolean verificarAsignaturaInscritaACursada(String rutEstudiante, String codigoAsignatura) {
		Persona p = lpersonas.buscar(rutEstudiante);
		if(p != null) {
			if(p instanceof Estudiante) {
				Estudiante estudiante = (Estudiante)p;
				ListaAsignaturas linscritas = estudiante.getAsignaturasInscritas();
				for(int i=0;i<linscritas.getCant();i++) {
					Asignatura asig = linscritas.getElementoI(i);
					if(asig.getNotaFinal()  >= 3.95) {
						linscritas.eliminar(asig.getCodigoAsignatura());
						estudiante.getAsignaturasCursadas().ingresar(asig);
					}
				}
					
			}
		}
		return false;
	}

	@Override
	public boolean eliminarEstudiante() {
		
		return false;
	}
	

}
