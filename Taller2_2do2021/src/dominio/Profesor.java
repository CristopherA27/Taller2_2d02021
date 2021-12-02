package dominio;
import logica.*;

public class Profesor extends Persona{
	private int salario;
	private ListaParalelos lparalelos;
	private ListaAsignaturas lasignaturas;
	
	public Profesor(String rut, String correo, String contraseña,int salario) {
		super(rut, correo, contraseña);
		lasignaturas = new ListaAsignaturas(100);
		lparalelos = new ListaParalelos(100);
		this.salario = salario;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public ListaParalelos getLparalelos() {
		return lparalelos;
	}

	public void setLparalelos(ListaParalelos lparalelos) {
		this.lparalelos = lparalelos;
	}

	public ListaAsignaturas getLasignaturas() {
		return lasignaturas;
	}

	public void setLasignaturas(ListaAsignaturas lasignaturas) {
		this.lasignaturas = lasignaturas;
	}


	

}
