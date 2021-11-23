package dominio;

public class Profesor extends Persona{
	private int salario;
	
	public Profesor(String rut, String correo, String contraseña,int salario) {
		super(rut, correo, contraseña);
		salario = 0;
		this.salario = salario;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}
	

}
