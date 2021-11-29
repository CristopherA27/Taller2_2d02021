package dominio;

import logica.*;

public class AsignaturaObligatoria extends Asignatura{
	private String nombreAsignatura;
	private int cantCreditos;
	private int nivelMalla;
	private int cantAsignaturasPrerrequisito;
	private String [] lcodigosPrerrequisitos;
	private int preRequisitos = 0;
	

	public AsignaturaObligatoria(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int nivelMalla,int cantAsignaturasPrerrequisito) {
		super(codigoAsignatura);
		this.nombreAsignatura = nombreAsignatura;
		this.cantCreditos = cantCreditos;
		this.nivelMalla = nivelMalla;
		this.cantAsignaturasPrerrequisito = cantAsignaturasPrerrequisito;
		lcodigosPrerrequisitos = new String [cantAsignaturasPrerrequisito];
	}
	
	public void aņadirCodigo(String code) {
		lcodigosPrerrequisitos[preRequisitos]= code;
		preRequisitos++;
	}
	
	public String[] getLcodigosPrerrequisitos() {
		return lcodigosPrerrequisitos;
	}

	public void setLcodigosPrerrequisitos(String[] lcodigosPrerrequisitos) {
		this.lcodigosPrerrequisitos = lcodigosPrerrequisitos;
	}

	public int getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(int preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}


	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}


	public int getCantCreditos() {
		return cantCreditos;
	}


	public void setCantCreditos(int cantCreditos) {
		this.cantCreditos = cantCreditos;
	}


	public int getNivelMalla() {
		return nivelMalla;
	}

	public void setNivelMalla(int nivelMalla) {
		this.nivelMalla = nivelMalla;
	}
	

	public int getCantAsignaturasPrerrequisito() {
		return cantAsignaturasPrerrequisito;
	}

	public void setCantAsignaturasPrerrequisito(int cantAsignaturasPrerrequisito) {
		this.cantAsignaturasPrerrequisito = cantAsignaturasPrerrequisito;
	}
	
}
