package dominio;

public class Persona {
	private String rut;
	private String correo;
	private String contraseña;
	
	public Persona(String rut,String correo,String contraseña) {
		this.rut = rut;
		this.correo = correo;
		this.contraseña = contraseña;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	

}
 