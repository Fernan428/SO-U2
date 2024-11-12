package Proyecto;

public class Proceso {

	private int tiempoRestante;
	private int estadoActual;
	private int prioridad;
	private int ID;
	private boolean entra;
	
	public Proceso (){
		
	}
	
	public Proceso (int tiempoRestante, int estadoActual, int prioridad, int ID,boolean entra){

		this.tiempoRestante=tiempoRestante;
		this.estadoActual=estadoActual;
		this.prioridad=prioridad;
		this.ID=ID;
		this.entra=entra;
		
	}

	public int getTiempoRestante() {
		return tiempoRestante;
	}

	public void setTiempoRestante(int tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}

	public int getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(int estadoActual) {
		this.estadoActual = estadoActual;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean getEntra() {
		return entra;
	}

	public void setEntra(boolean entra) {
		this.entra = entra;
	}

	@Override
	public String toString() {
		return "Proceso "+ID+"[tiempoRestante=" + tiempoRestante + ", estadoActual=" + estadoActual + ", prioridad="
				+ prioridad + "]";
	}
	
}