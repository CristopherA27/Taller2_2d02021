package dominio;

public class Asignatura {
	private String codigoAsignatura;
	private String nombreAsignatura;
	private int cantCreditos;
	
	public Asignatura(String codigoAsignatura,String nombreAsignatura, int cantCreditos) {
		this.codigoAsignatura = codigoAsignatura;
		this.nombreAsignatura = nombreAsignatura;
		this.cantCreditos = cantCreditos;
	}

	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
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
	
	

}
