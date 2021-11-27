package dominio;

public class Asignatura {
	private String codigoAsignatura;
	private double notaFinal;
	private Paralelo numeroParalelo;
	
	public Asignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
		notaFinal = 0;
		numeroParalelo = null;
	}
	public double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Paralelo getNumeroParalelo() {
		return numeroParalelo;
	}
	public void setNumeroParalelo(Paralelo numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
	
}
