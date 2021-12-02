package dominio;
import logica.*;

public class Profesor extends Persona{
	private int salario;
	private ListaParalelos listaParalelos;
	private ListaAsignaturas listaAsignaturas;
	
	
	public Profesor(String rut, String correo, String contrase�a,int salario) {
		super(rut, correo, contrase�a);
		listaAsignaturas = new ListaAsignaturas(100);
		listaParalelos = new ListaParalelos(100);
		this.salario = salario;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public ListaParalelos getListaParalelos() {
		return listaParalelos;
	}

	public ListaAsignaturas getListaAsignaturas() {
		return listaAsignaturas;
	}

	

}
