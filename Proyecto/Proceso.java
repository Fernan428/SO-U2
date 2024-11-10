package Proyecto;

public class Proceso {

	private int tiempoRestante;
	private int estadoActual;
	private int prioridad;
	
	public Proceso (){
		
	}
	
	public Proceso (int tiempoRestante, int estadoActual, int prioridad){

		this.tiempoRestante=tiempoRestante;
		this.estadoActual=estadoActual;
		this.prioridad=prioridad;
		
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

	@Override
	public String toString() {
		return "Proceso [tiempoRestante=" + tiempoRestante + ", estadoActual=" + estadoActual + ", prioridad="
				+ prioridad + "]";
	}

	
	
	
}