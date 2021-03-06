package dominio;
import logica.*;

public class Profesor extends Persona{
	private int salario;
	private ListaParalelos lparalelos;
	
	public Profesor(String rut, String correo, String contraseņa,int salario) {
		super(rut, correo, contraseņa);
		lparalelos = new ListaParalelos(4);
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


}
