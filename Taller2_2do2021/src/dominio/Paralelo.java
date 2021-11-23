package dominio;

public class Paralelo {
	private int numeroParalelo;
	private Asignatura codigoAsignatura;
	private Profesor rut;
	
	public Paralelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
		codigoAsignatura = null;
		rut = null;
	}

	public int getNumeroParalelo() {
		return numeroParalelo;
	}

	public void setNumeroParalelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}

	public Asignatura getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(Asignatura codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}

	public Profesor getRut() {
		return rut;
	}

	public void setRut(Profesor rut) {
		this.rut = rut;
	}
	

}
