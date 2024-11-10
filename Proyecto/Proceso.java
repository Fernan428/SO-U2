package Proyecto;

public class Proceso {

	private int tiempoRestante;
	private int estadoActual;
	
	public Proceso (){
		
	}
	
	public Proceso (int tiempoRestante, int estadoActual){

		this.tiempoRestante=tiempoRestante;
		this.estadoActual=estadoActual;
		
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
	
	
}
