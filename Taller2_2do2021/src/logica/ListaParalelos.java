package logica;
import dominio.*;


public class ListaParalelos {
	private int cant;
	private int max;
	private Paralelo [] listaParalelos;
	
	public ListaParalelos(int max) {
		cant =0;
		this.max = max;
		listaParalelos = new Paralelo[max];
	}
	
	public int getCant() {
		return cant;
	}
	
	public boolean ingresar(Paralelo paralelo) {
		if(cant<max) {
			listaParalelos[cant] = paralelo;
			cant++;
			return true;
		}
		return false;
	}
	
	public Paralelo buscar(int numeroParalelo) {
		int i;
	    for(i = 0; i < cant; i++){

	        if(listaParalelos[i].getNumeroParalelo() == numeroParalelo){
	            break;
	        }
	    }
	    if(i == cant){

	        return null;

	    }
	    else{
	    	//System.out.println(listaParalelos[i].getNumeroParalelo());
	        return listaParalelos[i];
	    }
		/*
		for(int i=0;i<cant;i++) {
			if(listaParalelos[i].getNumeroParalelo() == numeroParalelo) {
				return listaParalelos[i];
			}
		}*/

	}
	
	public boolean eliminar(int numeroParalelo) {
		int i;
		for(i=0;i<cant;i++) {
			if(listaParalelos[i].getNumeroParalelo() == numeroParalelo) {
				break;
			}
		}
		if(i==cant) {
			return false;
		}else {
			for(int k=i;k<cant;k++) {
				listaParalelos[k] = listaParalelos[k+1];
			}
			cant--;
			return true;
		}
	}
	
	public Paralelo getElementoI(int posicion) {
		return listaParalelos[posicion];
	}
	
	
	

}
