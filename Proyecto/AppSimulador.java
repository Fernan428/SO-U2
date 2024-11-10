package Proyecto;
import java.util.*;

public class AppSimulador{

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tiempoRestante=0;
		int estadoActual=0;
		int prioridad=0;
		
		System.out.println("******************************************************");
		System.out.println("   Seleccione el tipo de algoritmo de planificación");
		System.out.println("******************************************************");
		System.out.println("[ '1' Apropiativos]");
		System.out.println("[ '2' No-apropiativos]");
		System.out.println("*******************************************************");

		System.out.print("Opcion: ");
		int opcionTipo=sc.nextInt();
		System.out.println();

		System.out.println("*************************************************");
		System.out.println("        Seleccione el algoritmo a simular");
		System.out.println("*************************************************");
		System.out.println("\t"+"[ '1' Round-robin]");
		System.out.println("\t"+"[ '2' Prioridades]");
		System.out.println("\t"+"[ '3' Múltiples colas de prioridad]");
		System.out.println("\t"+"[ '4' Proceso más corto primero]");
		System.out.println("\t"+"[ '5' Planificación garantizada]");
		System.out.println("\t"+"[ '6' Boletos de Lotería]");
		System.out.println("\t"+"[ '7' Participación equitativa]");
		System.out.println("*************************************************");

		System.out.print("Opcion: ");
		int opcionAlgoritmo=sc.nextInt();
		System.out.println();
				
		int tiempoMonitoreoCPU=(int)(Math.random()*11)+15;
		System.out.println("Tiempo a simular: "+ tiempoMonitoreoCPU);
		
		int quantum=(int)(Math.random()*3)+2;
		System.out.println("Quantum para cada proceso: "+quantum+"\n");
		
		System.out.println("'Estado actual': [(1) En ejecución] [(2) Listo] [(3) Bloqueado]"+"\n");
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.println("|                   Tabla de control de procesos (PCB)                      |");
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.println("|"+"\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual"+"\t"+"Prioridad"+"   |");
		System.out.println("|"+"\t"+" ejecución "+"\t  "+"de ejecución                                   "+"   |");
		System.out.println("+---------------------------------------------------------------------------+");
		
		int procesosEjecucion=(int)(Math.random()*10)+1;

		Proceso [] procesos = new Proceso [procesosEjecucion];
		
		for(int i=0; i<procesosEjecucion; i++){
			
			procesos[i]= new Proceso((tiempoRestante=(int)(Math.random()*8)+3),(estadoActual=(int)(Math.random()*3)+1),((int)(Math.random()*4)+1));
			System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+"\t"+"\t"+"     "+procesos[i].getPrioridad());
			
		}
		
		if(opcionTipo==1){

			switch (opcionAlgoritmo) {
				case 1:
				RoundRobinApropiativo(procesos,quantum);
					break;

				case 2:
				PrioridadesApropiativo(procesos,quantum, tiempoMonitoreoCPU);
					break;

				case 3:
				MúltiplesColasPrioridadApropiativo();
					break;

				case 4:
				ProcesoMasCortoPrimeroApropiativo();
					break;

				case 5:
				PlanificacionGarantizadaApropiativo();
					break;
					
				case 6:
				BoletosLoteriaApropiativo();
					break;

				case 7:
				ParticipacionEquitativaApropiativo();
					break;
			
				default:
					break;
			}
				
		}else if(opcionTipo==2){

			switch (opcionAlgoritmo) {
				case 1:
				RoundRobinNoApropiativo(procesos,quantum);
					break;

				case 2:
				PrioridadesNoApropiativo();
					break;

				case 3:
				MúltiplesColasPrioridadNoApropiativo();
					break;

				case 4:
				ProcesoMasCortoPrimeroNoApropiativo();
					break;

				case 5:
				PlanificacionGarantizadaNoApropiativo();
					break;
					
				case 6:
				BoletosLoteriaNoApropiativo();
					break;

				case 7:
				ParticipacionEquitativaNoApropiativo();
					break;
			
				default:
					break;
			}

		}

	}
	

	public static void RoundRobinApropiativo (Proceso [] procesos, int quantum){
	
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		
		for(int i=0; i<procesos.length; i++){
			
			if (procesos[i].getEstadoActual()==1){
				
				procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-quantum);
				
			}
			
			if(procesos[i].getTiempoRestante()==0){
				
				procesos[i]=null;
			}
		}
		
	}
	
	public static void RoundRobinNoApropiativo (Proceso [] procesos, int quantum){
		
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		int pos=0;
		
		for(int i=0; i<procesos.length; i++){
			
			if (procesos[i].getEstadoActual()==1){
				
				procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-quantum);
				
			}
			
			pos=i;
			
		}

		if(procesos[pos].getTiempoRestante()==0){
			
			procesos[pos]=null;
		}
	}
	
	public static void PrioridadesApropiativo (Proceso [] procesos, int quantum, int tiempoMonitoreoCPU){

		int n = procesos.length;

        for (int i = 0; i < n - 1; i++) {
            int mayor = i;

            for (int j = i + 1; j < n; j++) {
                if (procesos[j].getPrioridad() > procesos[mayor].getPrioridad()) {
                    mayor = j;
                }
            }

            int x = procesos[i].getPrioridad();
            procesos[i].setPrioridad(procesos[mayor].getPrioridad());
            procesos[mayor].setPrioridad(x);;
        }

		for(int i = 0; i<procesos.length; i++){
			System.out.println(procesos[i].toString());
		}

	}

	public static void ProcesoApropiativoGeneral(Proceso [] procesos, int quantum, int tiempoMonitoreoCPU){

		int aux;
		for (int i=0; i < procesos.length; i++)
		{
			if (procesos[i].getTiempoRestante() < quantum)
			{
				aux = quantum;

				aux = procesos[i].getTiempoRestante();
				procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-aux);

				if (procesos[i].getTiempoRestante() == 0)
				{
					procesos[i] = null;
	
				}
	
				tiempoMonitoreoCPU = tiempoMonitoreoCPU - aux;


			}else{

				procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-quantum);

				if (procesos[i].getTiempoRestante() == 0)
				{
					procesos[i] = null;

				}

				tiempoMonitoreoCPU = tiempoMonitoreoCPU - quantum;
		}
		}

	}

	public static void PrioridadesNoApropiativo (){


	}

	public static void MúltiplesColasPrioridadApropiativo (){


	}

	public static void MúltiplesColasPrioridadNoApropiativo (){


	}

	public static void ProcesoMasCortoPrimeroApropiativo (){


	}

	public static void ProcesoMasCortoPrimeroNoApropiativo (){


	}

	public static void PlanificacionGarantizadaApropiativo (){


	}

	public static void PlanificacionGarantizadaNoApropiativo (){


	}

	public static void BoletosLoteriaApropiativo (){


	}

	public static void BoletosLoteriaNoApropiativo (){


	}

	public static void ParticipacionEquitativaApropiativo (){


	}

	public static void ParticipacionEquitativaNoApropiativo (){


	}

	

}