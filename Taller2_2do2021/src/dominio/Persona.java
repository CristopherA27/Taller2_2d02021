package dominio;

public class Persona {
	private String rut;
	private String correo;
	private String contrase�a;
	
	public Persona(String rut,String correo,String contrase�a) {
		this.rut = rut;
		this.correo = correo;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	

}
 