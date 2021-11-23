package dominio;

public class AsignaturaOpcional extends Asignatura{
	private int cantCreditosPrerrequisitos;

	public AsignaturaOpcional(String codigoAsignatura, String nombreAsignatura, int cantCreditos,int cantCreditosPrerrequisitos) {
		super(codigoAsignatura, nombreAsignatura, cantCreditos);
		this.cantCreditosPrerrequisitos = cantCreditosPrerrequisitos;
		
	}

	public int getCantCreditosPrerrequisitos() {
		return cantCreditosPrerrequisitos;
	}

	public void setCantCreditosPrerrequisitos(int cantCreditosPrerrequisitos) {
		this.cantCreditosPrerrequisitos = cantCreditosPrerrequisitos;
	}
	

}
