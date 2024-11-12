package Proyecto;

public class Proceso {

	private int tiempoRestante;
	private int estadoActual;
	private int prioridad;
	private int ID;
	private boolean entra;
	private int usuario;
	
	public Proceso (){
		
	}
	
	public Proceso (int tiempoRestante, int estadoActual, int prioridad, int ID,boolean entra, int usuario){

		this.tiempoRestante=tiempoRestante;
		this.estadoActual=estadoActual;
		this.prioridad=prioridad;
		this.ID=ID;
		this.entra=entra;
		this.usuario=usuario;
		
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

	public boolean isEntra() {
		return entra;
	}

	public void setEntra(boolean entra) {
		this.entra = entra;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Proceso "+ID+"[tiempoRestante=" + tiempoRestante + ", estadoActual=" + estadoActual + ", prioridad="
				+ prioridad + "]";
	}

	
	
}