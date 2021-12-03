package logica;
import java.util.Scanner;

import dominio.*;
        
public class SystemImpl implements SystemI{
	
	private ListaPersonas lpersonas;
	private ListaAsignaturas lasignaturas;
	private ListaParalelos lparalelos;
	private ListaPersonas estudiantesEliminados;
	
	public SystemImpl() {
		lpersonas = new ListaPersonas(100);
		lasignaturas = new ListaAsignaturas(100);
		lparalelos = new ListaParalelos(100);
		estudiantesEliminados = new ListaPersonas(20);
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
	public boolean ingresarParalelo(int numeroParalelo,String codigoAsignatura,String rutProfesor) {
		Paralelo paralelo = new Paralelo(numeroParalelo, codigoAsignatura, rutProfesor);
		boolean ingresado = lparalelos.ingresar(paralelo);
		for(int i=0;i<lpersonas.getCant();i++) {
			Persona p = lpersonas.getElemento(i);
			if(p instanceof Profesor) {
				if( p.getRut().equals(rutProfesor)) {
					ListaParalelos lp = ((Profesor) p).getLparalelos();
					lp.ingresar(paralelo);	
					return true;
				}
			}else {
				throw new NullPointerException("El profesor "+p+" no existe");
			}
		}
		return ingresado;
		
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
					estudiante.getAsignaturasInscritas().buscar(codigoAsignatura).setNumeroParalelo(numeroParalelo);
					if(numParalelo.getListaEstudiantes().getCant()< 100) {
						numParalelo.getListaEstudiantes().ingresar(estudiante);
					}
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
	
	public String obtenerAsignaturasOpcionalesDisponibles(String rut) {
		String dato = "";
		Persona p = lpersonas.buscar(rut);
		if(p != null && p instanceof Estudiante) {
			Estudiante estudiante =(Estudiante)p;
			for(int i=0;i<lasignaturas.getCant();i++) {
				Asignatura asig = lasignaturas.getElementoI(i);
				if(asig instanceof AsignaturaOpcional) {
					AsignaturaOpcional asigOp = (AsignaturaOpcional)asig;
					int k;
					for(k=0;k<estudiante.getAsignaturasCursadas().getCant();k++) {
						Asignatura asigCur = estudiante.getAsignaturasCursadas().getElementoI(k);
						if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNotaFinal() <3.95 ) {
							dato += asigOp.getCodigoAsignatura()+","+asigOp.getNombreAsignatura()+","+asigOp.getCantCreditos()+","+asigOp.getCantCreditosPrerrequisitos()+"\n";
							break;
						}
						if(asigOp.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) &&asigCur.getNotaFinal() >=3.95 ) {
							break;
						}
					}
					if(k==estudiante.getAsignaturasCursadas().getCant()) {
						boolean reconocida = true;
						for(int j=0;j<estudiante.getAsignaturasInscritas().getCant();j++) {
							Asignatura asigEstudi = estudiante.getAsignaturasInscritas().getElementoI(j);
							if(asigOp.getCodigoAsignatura().equals(asigEstudi.getCodigoAsignatura())) {
								reconocida = false;
								break;
							}
						}
						if (reconocida) {
							dato += asigOp.getCodigoAsignatura()+","+asigOp.getNombreAsignatura()+","+asigOp.getCantCreditos()+","+asigOp.getCantCreditosPrerrequisitos()+"\n";
						}
					}	
				}
			}
		}
		return dato;
	}
	
	public String obtenerAsignaturasObligatoriasDisponibles(String rut) {
		String dato = "";
		Persona p = lpersonas.buscar(rut);
		if(p != null && p instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)p;
			for(int i=0;i<lasignaturas.getCant();i++) {
				Asignatura asig = lasignaturas.getElementoI(i);
				if(asig instanceof AsignaturaObligatoria ) {
					AsignaturaObligatoria asigOb = (AsignaturaObligatoria)asig;
					int j;
					boolean reconocida = true;
					for(j=0;j<estudiante.getAsignaturasInscritas().getCant();j++) {
						Asignatura asigIns = estudiante.getAsignaturasInscritas().getElementoI(j);
						if(asigOb.getCodigoAsignatura().equals(asigIns.getCodigoAsignatura())) {
							reconocida = false;
							break;
						}
					}
					if(reconocida && asigOb.getNivelMalla()<= estudiante.getNivelAlumno() ) {
						dato+= asigOb.getCodigoAsignatura()+","+asigOb.getNombreAsignatura()+","+asigOb.getCantCreditos()+","+asigOb.getNivelMalla()+","+asigOb.getCantAsignaturasPrerrequisito()+", ";
						for(int k=0;k<asigOb.getListaAsignaturas().getCant();k++) {
							dato+= asigOb.getListaAsignaturas().getElementoI(k).getCodigoAsignatura()+"";
						}
						//System.out.println(estudiante.getNivelAlumno());
					}
					if(j==estudiante.getAsignaturasInscritas().getCant()) {
						for(int a=0;a<estudiante.getAsignaturasCursadas().getCant();a++) {
							Asignatura asigCur = estudiante.getAsignaturasCursadas().getElementoI(a);
							if(asigOb.getNivelMalla()<= estudiante.getNivelAlumno()) {
								if(asigOb.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNotaFinal() <3.95) {
									dato+=asigOb.getCodigoAsignatura()+" "+asigOb.getNombreAsignatura()+","+asigOb.getCantCreditos()+","+asigOb.getNivelMalla()+","+asigOb.getCantAsignaturasPrerrequisito()+",";
									for(int k=0;k<asigOb.getListaAsignaturas().getCant();k++) {
										dato+= asigOb.getListaAsignaturas().getElementoI(k).getCodigoAsignatura()+",";
									}
									break;
								}
								if(asigOb.getCodigoAsignatura().equals(asigCur.getCodigoAsignatura()) && asigCur.getNotaFinal() >=3.95) {
									break;
								}
							}
						}
					}
				}
			}
		}
		return dato;
	}
	
	public String obtenerParalelosDisponibles(String codigoAsignatura) {
		String dato = "";
		Asignatura asig = lasignaturas.buscar(codigoAsignatura);
		if(asig != null) {
			for(int i=0;i<lparalelos.getCant();i++) {
				Paralelo paralelo = lparalelos.getElementoI(i);
				if(paralelo.getAsignatura().equals(codigoAsignatura) && paralelo.getCantEstudiantes()<100 ) {
					dato += paralelo.getNumeroParalelo()+" "+paralelo.getRutProfesor()+"\n";
				}
			}
		}
		return dato;
	}

	@Override
	public boolean inscribirAsignatura(String rutEstudiante, String codigoAsignatura, int numeroParalelo) {
		Persona p = lpersonas.buscar(rutEstudiante);
		if(p != null && p instanceof Estudiante) {
			Estudiante e = (Estudiante)p;
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				Paralelo paralelo = lparalelos.buscar(numeroParalelo);
				if(paralelo != null) {
					 if(asig instanceof AsignaturaObligatoria) {
						 AsignaturaObligatoria asigOb = (AsignaturaObligatoria)asig;
						 int aprobadas =0;
						 for(int k=0;k<e.getAsignaturasCursadas().getCant();k++) {
							 for(int i=0;i<asigOb.getLcodigosPrerrequisitos().length;i++) {
								 String codigo = asigOb.getCodigoPre(i);
								 if(e.getAsignaturasCursadas().getElementoI(k).getCodigoAsignatura().equals(codigo) && e.getAsignaturasCursadas().getElementoI(k).getNotaFinal() >=3.95) {
									 aprobadas++;
								 }
							 }
						 }
						 if(aprobadas == asigOb.getCantAsignaturasPrerrequisito()) {
							 if(e.getCreditos() < 40 ) {
								 e.setCreditos(e.getCreditos()+asigOb.getCantCreditos());
								 e.getAsignaturasInscritas().ingresar(asigOb);
								 paralelo.getListaEstudiantes().ingresar(e);
								 paralelo.setCantEstudiantes(paralelo.getCantEstudiantes()+1);
								 asigOb.setNumeroParalelo(numeroParalelo);
								 return true;
							 }
						 } 
					 }else {
						 AsignaturaOpcional asigOp =(AsignaturaOpcional)asig;
						 if(e.getCreditos() > asigOp.getCantCreditosPrerrequisitos() && e.getCreditos() < 40) {
							 e.getAsignaturasInscritas().ingresar(asigOp);
							 paralelo.getListaEstudiantes().ingresar(e);
							 e.setCreditos(e.getCreditos()+asigOp.getCantCreditos());
							 asigOp.setNumeroParalelo(numeroParalelo);
							 return true;
						 }
					 }
				}else {
					throw new NullPointerException("El paralelo "+paralelo+" no existe");
				}
			}else {
				throw new NullPointerException("La asignatura "+asig+" no existe" );
			}
		}else {
			throw new NullPointerException("El estudiante "+p+" no existe");
		}
		return false;
	}
	

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
	@Override
	public boolean eliminarAsignaturaInscrita(String rutEstudiante, String codigoAsignatura) {
		Persona persona = lpersonas.buscar(rutEstudiante);
		if(persona != null && persona instanceof Estudiante) {
			Estudiante estudiante = (Estudiante)persona;
			Asignatura asig = estudiante.getAsignaturasInscritas().buscar(codigoAsignatura);
			if(asig != null) {
				for(int i=0;i<lparalelos.getCant();i++) {
					Paralelo paralelo = lparalelos.getElementoI(i);
					ListaPersonas le = paralelo.getListaEstudiantes();
					for(int j=0;j<le.getCant();j++) {
						if(le.getElemento(j).getRut().equals(rutEstudiante)) {
							le.eliminar(rutEstudiante);
							estudiante.getAsignaturasInscritas().eliminar(codigoAsignatura);
							paralelo.setCantEstudiantes(paralelo.getCantEstudiantes()-1);
							return true;
						}
					}
					
				}
			}else {
				throw new NullPointerException("La asignatura "+asig+" no existe en la lista de asignaturas inscritas del estudiante");
			}
		}else {
			throw new NullPointerException("El estudiante "+persona+" no existe");
		} 
		return false;
	}
	
	
	public String obtenerParalelosDictados(String rutProfesor) {
		String dato = "";
		Persona p = lpersonas.buscar(rutProfesor);
		if(p != null && p instanceof Profesor) {
			Profesor profe = (Profesor)p;
			ListaParalelos lp = profe.getLparalelos();
			for(int i=0;i<lp.getCant();i++) {
				Paralelo paralelo = lp.getElementoI(i);
				dato += paralelo.getNumeroParalelo()+" "+paralelo.getAsignatura()+"\n";
			}
		}
		return dato;
	}

	@Override
	public String obtenerAlumnosDeParalelo(int numeroParalelo, String rutProfesor) {
		String dato = "";
		Persona p = lpersonas.buscar(rutProfesor);
		if(p != null && p instanceof Profesor) {
			Profesor profe = (Profesor)p;
			Paralelo paralelo = profe.getLparalelos().buscar(numeroParalelo);
			if(paralelo != null) {
				dato+= "Los alumnos del paralelo son: "+"\n";
				ListaPersonas le = paralelo.getListaEstudiantes();
				for(int i=0;i<le.getCant();i++) {
					Persona personaParalelo = le.getElemento(i);
					if(personaParalelo instanceof Estudiante) {
						Estudiante e = (Estudiante)personaParalelo;
						dato+= "\tRut Estudiante: "+e.getRut()+" Correo: "+e.getCorreo()+"\n";
					}
				}
			}else {
				throw new NullPointerException("El paralelo "+paralelo+" no existe");
			}
		}else {
			throw new NullPointerException("El profesor "+p+" no existe");
		}
		return dato;
	}
	
	
	public boolean ingresarNotaFinal(int numeroParalelo,String codigoAsignatura, String rutEstudiante, double notaFinal) {
		Paralelo pa = lparalelos.buscar(numeroParalelo);
		if(pa != null) {
			Asignatura asig = lasignaturas.buscar(codigoAsignatura);
			if(asig != null) {
				if(pa.getAsignatura().equals(codigoAsignatura)) {
					ListaPersonas lper = pa.getListaEstudiantes();
					for(int i=0;i<lper.getCant();i++) {
						Persona p = lper.getElemento(i);
						if(p instanceof Estudiante) {
							Estudiante e = (Estudiante)p;
							if(e.getRut().equals(rutEstudiante)) {
								ListaAsignaturas lae = e.getAsignaturasInscritas();
								for(int k=0;k<lae.getCant();k++) {
									Asignatura a = lae.getElementoI(k);
									if(a.getCodigoAsignatura().equals(codigoAsignatura)) {
										a.setNotaFinal(notaFinal);
										if(notaFinal >=3.95) {
											e.getAsignaturasInscritas().eliminar(a.getCodigoAsignatura());
											e.getAsignaturasCursadas().ingresar(a);
											pa.getListaEstudiantes().eliminar(e.getRut());
											pa.setCantEstudiantes(pa.getCantEstudiantes()-1);
											return true;
										}else {
											e.getAsignaturasInscritas().eliminar(a.getCodigoAsignatura());
											e.getAsignaturasCursadas().ingresar(a);
											pa.getListaEstudiantes().eliminar(e.getRut());
											pa.setCantEstudiantes(pa.getCantEstudiantes()-1);
											return true;
										}
									}
								}
							}
						}else {
							throw new NullPointerException("El estudiante "+p+" no existe");
						}
					}
				}
			}else {
				throw new NullPointerException("La asignatura "+asig+" no existe");
			}
		}else {
			throw new NullPointerException("El paralelo "+pa+" no existe");
		}
		
		return false;
	}

	@Override
	public boolean eliminarEstudiante() {
		for(int i=0;i<lpersonas.getCant();i++) {
			Persona p = lpersonas.getElemento(i);
			if(p instanceof Estudiante) {
				Estudiante e = (Estudiante)p;
				if(e.getNivelAlumno() == 10) {
					//lpersonas.eliminar(e.getRut());
					for(int k=0;k<lparalelos.getCant();k++) {
						Paralelo paralelo = lparalelos.getElementoI(k);
						ListaPersonas lp = paralelo.getListaEstudiantes();
						for(int a=0;a<lp.getCant();a++) {
							if(lp.getElemento(a).getRut().equals(e.getRut())) {
								paralelo.getListaEstudiantes().eliminar(e.getRut());
								paralelo.setCantEstudiantes(paralelo.getCantEstudiantes()-1);
								estudiantesEliminados.ingresar(e);
								lpersonas.eliminar(e.getRut());
								return true;
							}
						}
					}
				}
			}else {
				throw new NullPointerException("El estudiante "+p+" no existe");
			}
		}
		return false;
	}
	
	public void a() {
		for(int i=0;i<estudiantesEliminados.getCant();i++) {
			Persona p = estudiantesEliminados.getElemento(i);
			System.out.println(p.getRut());
		}
	}
	
}
