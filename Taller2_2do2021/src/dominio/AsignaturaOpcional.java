package dominio;

public class AsignaturaOpcional extends Asignatura{
	private String nombreAsignatura;
	private int cantCreditos;
	private int cantCreditosPrerrequisitos;

	public AsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int cantCreditosPrerrequisitos) {
		super(codigoAsignatura);
		this.nombreAsignatura = nombreAsignatura;
		this.cantCreditos = cantCreditos;
		this.cantCreditosPrerrequisitos = cantCreditosPrerrequisitos;
		
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


	public int getCantCreditosPrerrequisitos() {
		return cantCreditosPrerrequisitos;
	}

	public void setCantCreditosPrerrequisitos(int cantCreditosPrerrequisitos) {
		this.cantCreditosPrerrequisitos = cantCreditosPrerrequisitos;
	}
	

}
