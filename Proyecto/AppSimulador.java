package Proyecto;
import java.util.*;

public class AppSimulador {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tiempoRestante=0;
		int estadoActual=0;
		
		System.out.println("\t"+"\t"+"Elige el valor númerico que representa la opción");
		System.out.println("\t"+"---------------------------------------------------------------");
		
		System.out.println("Tipo de algoritmo de planificación a usar -> [ '1' Apropiativos] [ '2' No-apropiativos]:<-");
		int opcionTipo=sc.nextInt();
		System.out.println("Algoritmo a usar:");
		System.out.println("\t"+"[ '1' Round-robin]");
		System.out.println("\t"+"[ '2' Prioridades]");
		System.out.println("\t"+"[ '3' Múltiples colas de prioridad]");
		System.out.println("\t"+"[ '4' Proceso más corto primero]");
		System.out.println("\t"+"[ '5' Múltiples colas de prioridad]");
		System.out.println("\t"+"[ '6' Planificación garantizada]");
		System.out.println("\t"+"[ '7' Boletos de Lotería]");
		System.out.println("\t"+"[ '8' Participación equitativa]");
		int opcionAlgoritmo=sc.nextInt();
		
		System.out.println("\n"+"---------------------------------------------------------------------------------------------------------"+"\n");
		
		int tiempoMonitoreoCPU=(int)(Math.random()*11)+15;
		System.out.println("Tiempo a simular: "+ tiempoMonitoreoCPU);
		
		int quantum=(int)(Math.random()*3)+2;
		System.out.println("Quantum para cada proceso: "+quantum+"\n");
		
		System.out.println("Significado 'Estado actual': [(1) En ejecución] [(2) Listo] [(3) Bloqueado]"+"\n");
		System.out.println("\t"+"\t"+"Tabla de control de procesos (PCB)");
		System.out.println("\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual");
		System.out.println("\t"+" ejecución "+"\t  "+"de ejecución");
		
		int procesosEjecucion=(int)(Math.random()*10)+1;

		Proceso [] procesos = new Proceso [procesosEjecucion];
		
		for(int i=0; i<procesosEjecucion; i++){
			
			procesos[i]= new Proceso((tiempoRestante=(int)(Math.random()*8)+3),(estadoActual=(int)(Math.random()*3)+1));
			System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual());
			
		}
		
		if(opcionTipo==1){
			
			
		}

	}
	

	public static void RoundRobin (){
		
		
	}

}
