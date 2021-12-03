package dominio;

public class Asignatura {
	private String codigoAsignatura;
	private double notaFinal;
	private int numeroParalelo;
	
	public Asignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
		notaFinal = 0;
		numeroParalelo = 0;
	}
	public double getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	public int getNumeroParalelo() {
		return numeroParalelo;
	}
	public void setNumeroParalelo(int numeroParalelo) {
		this.numeroParalelo = numeroParalelo;
	}
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}
	
}
