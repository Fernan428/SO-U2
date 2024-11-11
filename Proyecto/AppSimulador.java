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
			
			procesos[i]= new Proceso((tiempoRestante=(int)(Math.random()*8)+3),(estadoActual=(int)(Math.random()*3)+1),((int)(Math.random()*4)+1),i+1);
			System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+"\t"+"\t"+"     "+procesos[i].getPrioridad());
			
		}
		
		if(opcionTipo==1){

			switch (opcionAlgoritmo) {
				case 1:
				RoundRobinApropiativo(procesos,quantum);
				System.out.println("*");
				System.out.println("Significado 'Estado actual': [(1) En ejecución] [(2) Listo] [(3) o (0) Bloqueado]"+"\n");
				System.out.println("\t"+"\t"+"\t"+"Tabla de control de procesos (PCB)");
				System.out.println("\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual"+"\t"+"Prioridad");
				System.out.println("\t"+" ejecución "+"\t  "+"de ejecución");
				for(int i=0; i<procesosEjecucion; i++){

					if(procesos[i]!= null){
						System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+
								"\t"+"\t"+"     "+procesos[i].getPrioridad());
					}
				}
				for(int i=0; i<procesosEjecucion; i++){

					if(procesos[i]!= null){

						System.out.print("Entra Proceso ");
						if(procesos[i].getEstadoActual()==3 || procesos[i].getEstadoActual()==0){
							System.out.print((i+1)+", "+"no se ejecuta porque sigue bloqueado"+"\n");
						}
						else if(procesos[i].getEstadoActual()==1){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
						else if(procesos[i].getEstadoActual()==2){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
					}
					else if(procesos[i]== null){
						System.out.println("Entra Proceso "+(i+1)+", se ejecuta y termina");
					}
				}
					break;

				case 2:
				PrioridadesApropiativo(procesos,quantum, tiempoMonitoreoCPU);
					break;

				case 3:
				MúltiplesColasPrioridadApropiativo(procesos,quantum, tiempoMonitoreoCPU);
					break;

				case 4:
				ProcesoMasCortoPrimeroApropiativo(procesos,quantum, tiempoMonitoreoCPU);
					break;

				case 5:
				PlanificacionGarantizadaApropiativo();
				System.out.println("*");
				System.out.println("Significado 'Estado actual': [(1) En ejecución] [(2) Listo] [(3) o (0) Bloqueado]"+"\n");
				System.out.println("\t"+"\t"+"\t"+"Tabla de control de procesos (PCB)");
				System.out.println("\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual"+"\t"+"Prioridad");
				System.out.println("\t"+" ejecución "+"\t  "+"de ejecución");
				for(int i=0; i<procesosEjecucion; i++){

					if(procesos[i]!= null){
						System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+
								"\t"+"\t"+"     "+procesos[i].getPrioridad());
					}
				}
				for(int i=0; i<procesosEjecucion; i++){

					if(procesos[i]!= null){

						System.out.print("Entra Proceso ");
						if(procesos[i].getEstadoActual()==3 || procesos[i].getEstadoActual()==0){
							System.out.print((i+1)+", "+"no se ejecuta porque sigue bloqueado"+"\n");
						}
						else if(procesos[i].getEstadoActual()==1){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
						else if(procesos[i].getEstadoActual()==2){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
					}
					else if(procesos[i]== null){
						System.out.println("Entra Proceso "+(i+1)+", se ejecuta y termina");
					}
				}
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
				System.out.println("*");
				System.out.println(" Significado 'Estado actual': [(1) En ejecución] [(2) Listo] [(3) o (0)Bloqueado]"+"\n");
				System.out.println("\t"+"\t"+"\t"+"Tabla de control de procesos (PCB)");
				System.out.println("\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual"+"\t"+"Prioridad");
				System.out.println("\t"+" ejecución "+"\t  "+"de ejecución");
				for(int i=0; i<procesosEjecucion; i++){
					if(procesos[i]!= null && procesos[i].getTiempoRestante() != 0){
						System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+
								"\t"+"\t"+"     "+procesos[i].getPrioridad());
					}
				}
				for(int i=0; i<procesosEjecucion; i++){
					if(procesos[i]!= null){
						System.out.print("Entra Proceso ");
						if(procesos[i].getEstadoActual()==3 || procesos[i].getEstadoActual()==0){
							System.out.print((i+1)+", "+"no se ejecuta porque sigue bloqueado"+"\n");
						}
						else if(procesos[i].getEstadoActual()==1){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
						else if(procesos[i].getEstadoActual()==2){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
					}
					else if(procesos[i]== null){
						System.out.println("Entra Proceso "+(i+1)+", se ejecuta y termina");
					}
				}
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
				System.out.println("*");
				System.out.println("Significado 'Estado actual': [(1) En ejecución] [(2) Listo] [(3) o (0) Bloqueado]"+"\n");
				System.out.println("\t"+"\t"+"\t"+"Tabla de control de procesos (PCB)");
				System.out.println("\t"+"Procesos en"+"\t"+"Tiempo restante  "+"\t"+"Estado actual"+"\t"+"Prioridad");
				System.out.println("\t"+" ejecución "+"\t  "+"de ejecución");
				for(int i=0; i<procesosEjecucion; i++){
					if(procesos[i]!= null){
						System.out.println("\t"+"     "+(i+1)+"\t"+"\t"+"\t"+procesos[i].getTiempoRestante()+"\t"+"\t"+"     "+procesos[i].getEstadoActual()+
								"\t"+"\t"+"     "+procesos[i].getPrioridad());
					}
				}
				for(int i=0; i<procesosEjecucion; i++){
					if(procesos[i]!= null){
						System.out.print("Entra Proceso ");
						if(procesos[i].getEstadoActual()==3 || procesos[i].getEstadoActual()==0){
							System.out.print((i+1)+", "+"no se ejecuta porque sigue bloqueado"+"\n");
						}
						else if(procesos[i].getEstadoActual()==1){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
						else if(procesos[i].getEstadoActual()==2){
							System.out.print((i+1)+", "+"se ejecuta"+"\n");
						}
					}
					else if(procesos[i]== null){

						System.out.println("Entra Proceso "+(i+1)+", se ejecuta y termina");

					}
				}
					break;
					
				case 6:
				BoletosLoteriaNoApropiativo(procesos, quantum, tiempoMonitoreoCPU);
					break;

				case 7:
				ParticipacionEquitativaNoApropiativo(procesos, quantum, tiempoMonitoreoCPU);
					break;
			
				default:
					break;
			}

		}

	}
	
public static void RoundRobinApropiativo (Proceso [] procesos, int tiempoMonitoreoCPU, int quantum){
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		int copiaTimeMonit=tiempoMonitoreoCPU;
			
			for(int i=0; i<procesos.length; i++){

				if(procesos[i]!= null && copiaTimeMonit >= 1){

					if(procesos[i].getEstadoActual()==0 || procesos[i].getEstadoActual()==3){

						procesos[i].setEstadoActual(((int)(Math.random()*2)));

						if(procesos[i].getEstadoActual()==1){
							procesos[i].setEstadoActual(2);
						}
					}
					if(procesos[i].getEstadoActual()==1){

						if(quantum>procesos[i].getTiempoRestante()){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								quantum=copiaTimeMonit;
							}
							quantum=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-quantum);
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
							continue;
						}
					}
					else if(procesos[i].getEstadoActual()==2){
						procesos[i].setEstadoActual(1);
						if(quantum>procesos[i].getTiempoRestante()){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								quantum=copiaTimeMonit;
							}
							quantum=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-quantum);
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
							continue;
						}
					}
				}
				else{

					break;
				}
			}
			copiaTimeMonit-=quantum;
	}
	
	public static void RoundRobinNoApropiativo (Proceso [] procesos, int tiempoMonitoreoCPU, int quantum){
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		int tiempoSeguido=0, copiaTimeMonit=tiempoMonitoreoCPU;
		
		for(int i=0; i<procesos.length; i++){

			if(procesos[i]!= null && copiaTimeMonit >= 1){

				if(procesos[i].getEstadoActual()==0 || procesos[i].getEstadoActual()==3){

					procesos[i].setEstadoActual(((int)(Math.random()*2)));

					if(procesos[i].getEstadoActual()==1){
						procesos[i].setEstadoActual(2);
					}
				}
				if(procesos[i].getEstadoActual()==1){

					tiempoSeguido=((int)(Math.random()*3)+2)*quantum;
					System.out.println("+"+tiempoSeguido);

					if(tiempoSeguido>procesos[i].getTiempoRestante() ){
						if(copiaTimeMonit<procesos[i].getTiempoRestante()){
							tiempoSeguido=copiaTimeMonit;
						}
						else{
							tiempoSeguido=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						System.out.println("*"+copiaTimeMonit);
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						if(copiaTimeMonit==0){
							break;
						}
					}
					else if(procesos[i].getTiempoRestante()>copiaTimeMonit){
						
						tiempoSeguido=copiaTimeMonit;
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						System.out.println("*"+copiaTimeMonit);
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						if(copiaTimeMonit==0){
							break;
						}
					}
					else{
						
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						System.out.println("*"+copiaTimeMonit);
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						if(copiaTimeMonit==0){
							break;
						}
					}
					continue;
				}
				else if(procesos[i].getEstadoActual()==2){

					tiempoSeguido=((int)(Math.random()*3)+2)*quantum;
					System.out.println("++"+tiempoSeguido);
					
					if(tiempoSeguido>procesos[i].getTiempoRestante()){
						if(copiaTimeMonit<procesos[i].getTiempoRestante()){
							tiempoSeguido=copiaTimeMonit;
						}
						else{
							tiempoSeguido=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						procesos[i].setEstadoActual(1);
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						if(copiaTimeMonit==0){
							break;
						}
					}
					else if(procesos[i].getTiempoRestante()>copiaTimeMonit){
						
						tiempoSeguido=copiaTimeMonit;
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						procesos[i].setEstadoActual(1);
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						if(copiaTimeMonit==0){
							break;
						}
					}
					else {

						procesos[i].setEstadoActual(1);
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
						copiaTimeMonit-=tiempoSeguido;
						System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
						}
						System.out.println("**"+copiaTimeMonit);
						if(copiaTimeMonit==0){
							break;
						}
						
					}	
					continue;
				}
			}
			else{
				
				continue;
			}
			
			if(procesos[i].getTiempoRestante()!=0){
				
				System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
			}
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

            Proceso temp = procesos[i];
			procesos[i] = procesos[mayor];
			procesos[mayor] = temp;
        }

		System.out.println();

		ProcesoApropiativoGeneral(procesos, quantum, tiempoMonitoreoCPU);

	}

	

	public static void ProcesoNoApropiativoGeneral(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {
		
	}

	public static void PrioridadesNoApropiativo (){


	}

	public static void MúltiplesColasPrioridadApropiativo (Proceso [] procesos, int quantum, int tiempoMonitoreoCPU){

		int n = procesos.length;

        for (int i = 0; i < n - 1; i++) {
            int mayor = i;

            for (int j = i + 1; j < n; j++) {
                if (procesos[j].getPrioridad() > procesos[mayor].getPrioridad()) {
                    mayor = j;
                }
            }

            Proceso temp = procesos[i];
			procesos[i] = procesos[mayor];
			procesos[mayor] = temp;
        }

		System.out.println();

		ProcesoApropiativoGeneral(procesos, quantum, tiempoMonitoreoCPU);


	}

	public static void MúltiplesColasPrioridadNoApropiativo (){


	}

	public static void ProcesoMasCortoPrimeroApropiativo (Proceso [] procesos, int quantum, int tiempoMonitoreoCPU){

		int n = procesos.length;

		for (int i = 0; i < n - 1; i++) {
			if (procesos[i] == null) continue;  

			int menor = i;

			for (int j = i + 1; j < n; j++) {
				if (procesos[j] != null && procesos[j].getTiempoRestante() < procesos[menor].getTiempoRestante()) {
					menor = j;
				}
			}

			if (menor != i) {
				Proceso temp = procesos[i];
				procesos[i] = procesos[menor];
				procesos[menor] = temp;
			}
		}

		System.out.println();

		ProcesoApropiativoGeneral(procesos, quantum, tiempoMonitoreoCPU);


	
}

	public static void ProcesoMasCortoPrimeroNoApropiativo (){


	}

	public static int PlanificacionGarantizadaApropiativo (Proceso [] procesos, int tiempoMonitoreoCPU, int quantum){
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		int copiaTimeMonit=tiempoMonitoreoCPU, promesa=tiempoMonitoreoCPU/procesos.length;
			
			for(int i=0; i<procesos.length; i++){

				if(procesos[i]!= null && copiaTimeMonit >= 1){

					if(procesos[i].getEstadoActual()==0 || procesos[i].getEstadoActual()==3){

						procesos[i].setEstadoActual(((int)(Math.random()*2)));

						if(procesos[i].getEstadoActual()==1){
							procesos[i].setEstadoActual(2);
						}
					}
					if(procesos[i].getEstadoActual()==1){

						if(procesos[i].getTiempoRestante()<promesa){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								promesa=copiaTimeMonit;
							}
							promesa=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-promesa);	
						if(procesos[i].getTiempoRestante()==0){
							procesos[i]=null;
							continue;
						}
					}
					else if(procesos[i].getEstadoActual()==2){

						procesos[i].setEstadoActual(1);
						if(procesos[i].getTiempoRestante()<promesa){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								promesa=copiaTimeMonit;
							}
							promesa=procesos[i].getTiempoRestante();
						}
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-promesa);	
						if(procesos[i].getTiempoRestante()==0){

							procesos[i]=null;
							continue;
						}
					}
				}
				else{

					break;
				}
			}
			copiaTimeMonit-=quantum;
			return promesa;
	}
	public static void PlanificacionGarantizadaNoApropiativo (Proceso [] procesos, int tiempoMonitoreoCPU, int quantum){
//		[(1) En ejecución] [(2) Listo] [(3) Bloqueado]
		int tiempoSeguido=0, copiaTimeMonit=tiempoMonitoreoCPU, promesa=tiempoMonitoreoCPU/procesos.length;
			
			for(int i=0; i<procesos.length; i++){

				if(procesos[i]!= null && copiaTimeMonit >= 1){

					if(procesos[i].getEstadoActual()==0 || procesos[i].getEstadoActual()==3){

						procesos[i].setEstadoActual(((int)(Math.random()*2)));

						if(procesos[i].getEstadoActual()==1){
							procesos[i].setEstadoActual(2);
						}
					}
					if(procesos[i].getEstadoActual()==1){

						tiempoSeguido=((int)(Math.random()*3)+2)*promesa;
						System.out.println("+"+tiempoSeguido);
				
						if(tiempoSeguido>procesos[i].getTiempoRestante() ){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								tiempoSeguido=copiaTimeMonit;
							}
							else{
								tiempoSeguido=procesos[i].getTiempoRestante();
							}
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							System.out.println("*"+copiaTimeMonit);
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							if(copiaTimeMonit==0){
								break;
							}
						}
						else if(procesos[i].getTiempoRestante()>copiaTimeMonit){
							
							tiempoSeguido=copiaTimeMonit;
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							System.out.println("*"+copiaTimeMonit);
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							if(copiaTimeMonit==0){
								break;
							}
						}
						else{
							
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							System.out.println("*"+copiaTimeMonit);
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							if(copiaTimeMonit==0){
								break;
							}
						}
						continue;
					}
					else if(procesos[i].getEstadoActual()==2){

						tiempoSeguido=((int)(Math.random()*3)+2)*promesa;
						System.out.println("++"+tiempoSeguido);
						
						if(tiempoSeguido>procesos[i].getTiempoRestante()){
							if(copiaTimeMonit<procesos[i].getTiempoRestante()){
								tiempoSeguido=copiaTimeMonit;
							}
							else{
								tiempoSeguido=procesos[i].getTiempoRestante();
							}
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							procesos[i].setEstadoActual(1);
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							if(copiaTimeMonit==0){
								break;
							}
						}
						else if(procesos[i].getTiempoRestante()>copiaTimeMonit){
							
							tiempoSeguido=copiaTimeMonit;
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							procesos[i].setEstadoActual(1);
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							if(copiaTimeMonit==0){
								break;
							}
						}
						else {

							procesos[i].setEstadoActual(1);
							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante()-tiempoSeguido);
							copiaTimeMonit-=tiempoSeguido;
							System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
							if(procesos[i].getTiempoRestante()==0){
								procesos[i]=null;
							}
							System.out.println("**"+copiaTimeMonit);
							if(copiaTimeMonit==0){
								break;
							}
						}	
						continue;
					}
				}
				else{
					
					continue;
				}
				if(procesos[i].getTiempoRestante()!=0){
					System.out.println("\n"+"Proceso "+(i+1)+" quedará con tiempo restante de ejecución de: "+procesos[i].getTiempoRestante());
				}
			}
	}

	public static void BoletosLoteriaApropiativo (Proceso [] procesos, int quantum, int tiempoMonitoreoCPU){
		int boletos [] = new int [procesos.length];
		int contUnidades [] = new int [procesos.length];
		int tiempoRest = tiempoMonitoreoCPU, cantCambios = 0;
		for (int i = 0; i < procesos.length; i++) {
			switch(procesos[i].getPrioridad()) {
			case 1:
				boletos[i]=6;
				break;
			case 2: 
				boletos[i]=8;
				break;
			case 3: 
				boletos[i]=10;
				break;
			case 4: 
				boletos[i]=12;
				break;
			default:
				boletos[i]=4;
			}
		}
		int totalBoletos = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i].getTiempoRestante() > 0) {
				totalBoletos += boletos[i];
			}
		}
		int loteria [] = new int[totalBoletos];
		for (int i = 0; i < boletos.length; i++) {
			int cant = boletos[i];
			for (int j = 0; j < cant; j++) {
				int numBol;
				do {
					numBol = (int) (Math.random() * loteria.length);
				} while (loteria[numBol] != 0); 
				loteria[numBol] = i; 
			}
		}
		while (tiempoRest > 0) {
	        int bolGan = (int) (Math.random() * loteria.length);
	        int pos = loteria[bolGan];
	        if (procesos[pos] == null || procesos[pos].getTiempoRestante() == 0 || loteria[bolGan] == 0) {
	            continue;
	        }
	        if (procesos[pos].getEstadoActual() == 3) {
	            int ejecuta = (int) (Math.random() * 2);
	            if (ejecuta == 1) {
	                procesos[pos].setEstadoActual(2);
	            } else continue;
	        }
	        if (tiempoRest < quantum) {
	            procesos[pos].setTiempoRestante(procesos[pos].getTiempoRestante() - tiempoRest);
	            contUnidades[pos] += tiempoRest;
	            tiempoRest = 0;
	            cantCambios++;
	        } else {
	            tiempoRest -= quantum;
	            if (procesos[pos].getTiempoRestante() - quantum < 0) {
	                int cant = procesos[pos].getTiempoRestante();
	                procesos[pos].setTiempoRestante(0);
	                procesos[pos] = null;
	                contUnidades[pos] += cant;
	                cantCambios++;
	            } else {
	                procesos[pos].setTiempoRestante(procesos[pos].getTiempoRestante() - quantum);
	                contUnidades[pos] += quantum;
	                cantCambios++;
	            }
	        }
			int procTerm = 0, bolUsados = 0;
			for (int i = 0; i < procesos.length; i++) {
				if (procesos[i] == null) procTerm++;
			}
			for (int i = 0; i < loteria.length; i++) {
				if (loteria[i] == 0) bolUsados++;
			}
			if (procTerm == procesos.length || bolUsados == loteria.length) tiempoRest = 0;
			loteria[bolGan] = 0;
		}
		System.out.println("Informe de uso de procesador con: Boletos de Lotería");
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null) {
				System.out.println("Entra Proceso " + (i + 1) + ", se ejecuta " + (contUnidades[i])+ " unidades y termina");
			} else {
				System.out.print("Entra Proceso ");
				if (procesos[i].getEstadoActual() == 3 || procesos[i].getEstadoActual() == 0) {
					System.out.print((i + 1) + ", no se ejecuto porque sigue bloqueado\n");
				} else if (contUnidades[i] == 0){
					System.out.print((i + 1) + ", no se ejecuto \n");
				} else System.out.print((i + 1) + ", se ejecuta " + (contUnidades[i]) + " unidades \n");				
			}
		}
		System.out.println("Informe final de ejecucion:");
		System.out.print("Que procesos terminaron:");
		int cont = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null) {
				System.out.print(" " + (i+1));
				cont++;
			}
		}
		if (cont == 0) {
			System.out.print(" ninguno.");
		}
		System.out.println();
		System.out.print("Cuales no entraron nunca en ejecucion:");
		int aux = 0;
		for (int i = 0; i < contUnidades.length; i++) {
			if (contUnidades[i] == 0) {
				System.out.print(" " + (i+1));
				aux++;
			}
		}
		if (aux == 0) {
			System.out.print(" ninguno.");
		}
		System.out.println();
		System.out.print("Cuantos siguen en proceso:");
		int j = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null ) continue;
			if (procesos[i].getEstadoActual() == 2 || procesos[i].getEstadoActual() == 1) {
				System.out.print(" " + (i+1));
				j++;
			}
		}
		if (j == 0) {
			System.out.print(" ninguno.");
		}
		System.out.println();
		System.out.println("Cantidad de cambios de procesos: " + cantCambios);
		int totalEjecutado = 0;
		for (int unidad : contUnidades) {
			totalEjecutado += unidad;
		}
		System.out.println("Total de unidades ejecutadas: " + totalEjecutado);
		System.out.println("Tiempo monitoreado del CPU: " + tiempoMonitoreoCPU);
	}

	public static void BoletosLoteriaNoApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {
		int boletos[] = new int[procesos.length];
		int contUnidades[] = new int[procesos.length];
		int tiempoRest = tiempoMonitoreoCPU;
		int cantCambios = 0;
		for (int i = 0; i < procesos.length; i++) {
			switch (procesos[i].getPrioridad()) {
			case 1:
				boletos[i] = 6;
				break;
			case 2:
				boletos[i] = 8;
				break;
			case 3:
				boletos[i] = 10;
				break;
			case 4:
				boletos[i] = 12;
				break;
			default:
				boletos[i] = 4;
			}
		}
		int totalBoletos = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i].getTiempoRestante() > 0) {
				totalBoletos += boletos[i];
			}
		}
		int loteria[] = new int[totalBoletos];
		for (int i = 0; i < boletos.length; i++) {
			int cant = boletos[i];
			for (int j = 0; j < cant; j++) {
				int numBol;
				do {
					numBol = (int) (Math.random() * loteria.length);
				} while (loteria[numBol] != 0);
				loteria[numBol] = i;
			}
		}
		while (tiempoRest > 0) {
			int bolGan = (int) (Math.random() * loteria.length);
			int pos = loteria[bolGan];
			if (procesos[pos] == null || procesos[pos].getTiempoRestante() == 0 || loteria[bolGan] == 0) {
				continue;
			}
			int tiempoUso = (int) ((Math.random() * 2) + 2);
			if (tiempoUso > procesos[pos].getTiempoRestante()) {
				tiempoUso = procesos[pos].getTiempoRestante();
			}
			if (tiempoUso > tiempoRest) {
				tiempoUso = tiempoRest;
			}
			procesos[pos].setTiempoRestante(procesos[pos].getTiempoRestante() - tiempoUso);
			contUnidades[pos] += tiempoUso;
			tiempoRest -= tiempoUso;
			cantCambios++;
			int nuevoEstado = (int) (Math.random() * 3) + 1;
			procesos[pos].setEstadoActual(nuevoEstado);

			if (procesos[pos].getTiempoRestante() == 0) {
				procesos[pos] = null;
			}
			int procTerm = 0;
			for (int i = 0; i < procesos.length; i++) {
				if (procesos[i] == null) procTerm++;
			}
			if (procTerm == procesos.length) tiempoRest = 0;
			loteria[bolGan] = 0;
		}
		System.out.println("Informe de uso de procesador con: Boletos de Lotería (No Apropiativo)");
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null) {
				System.out.println("Entra Proceso " + (i + 1) + ", se ejecuta " + (contUnidades[i])+ " unidades y termina");
			} else {
				System.out.print("Entra Proceso ");
				if (procesos[i].getEstadoActual() == 3 || procesos[i].getEstadoActual() == 0) {
					System.out.print((i + 1) + ", no se ejecuto porque sigue bloqueado\n");
				} else if (contUnidades[i] == 0){
					System.out.print((i + 1) + ", no se ejecuto \n");
				} else System.out.print((i + 1) + ", se ejecuta " + (contUnidades[i]) + " unidades \n");				
			}
		}
		System.out.println("Informe final de ejecucion:");
		System.out.print("Que procesos terminaron:");
		int cont = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null) {
				System.out.print(" " + (i+1));
				cont++;
			}
		}
		if (cont == 0) {
			System.out.print(" ninguno.");
		}
		System.out.println();
		System.out.print("Cuales no entraron nunca en ejecucion:");
		int aux = 0;
		for (int i = 0; i < contUnidades.length; i++) {
			if (contUnidades[i] == 0) {
				System.out.print(" " + (i+1));
				aux++;
			}
		}

		if (aux == 0) {
			System.out.println(" ninguno.");
		}
		System.out.println();
		System.out.print("Cuantos siguen en proceso:");
		int j = 0;
		for (int i = 0; i < procesos.length; i++) {
			if (procesos[i] == null ) continue;
			if (procesos[i].getEstadoActual() == 2 || procesos[i].getEstadoActual() == 1) {
				System.out.print(" " + (i+1));
				j++;
			}
		}
		if (j == 0) {
			System.out.println(" ninguno.");
		}
		System.out.println();
		System.out.println("Cantidad de cambios de procesos: " + cantCambios);
		int totalEjecutado = 0;
		for (int unidad : contUnidades) {
			totalEjecutado += unidad;
		}
		System.out.println("Total de unidades ejecutadas: " + totalEjecutado);
		System.out.println("Tiempo monitoreado del CPU: " + tiempoMonitoreoCPU);

	}
	public static void ParticipacionEquitativaApropiativo (){


	}

	public static void ParticipacionEquitativaNoApropiativo (){


	}

	public static void ProcesoApropiativoGeneral(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {
		boolean ProcesosPendientes;
		int tiempoNecesario;
	
		do {
			ProcesosPendientes = false;
	
			for (int i = 0; i < procesos.length; i++) {
				if (procesos[i] != null && procesos[i].getTiempoRestante() > 0) {
					ProcesosPendientes = true;

					if (procesos[i].getTiempoRestante() < quantum) {
						tiempoNecesario = procesos[i].getTiempoRestante();
					} else {
						tiempoNecesario = quantum;
					}

					if (tiempoMonitoreoCPU < tiempoNecesario) {
						System.out.println("No hay suficiente tiempo de monitoreo para ejecutar el proceso " + procesos[i].getID() +"\n"+"Fin de la simulacion");
						return; 
					}
	
					switch (procesos[i].getEstadoActual()) {
						case 1: 
							procesos[i].setEstadoActual(2);

							procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
							tiempoMonitoreoCPU -= tiempoNecesario; 
							System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
	
							if (procesos[i].getTiempoRestante() == 0) {
								procesos[i] = null; 
								System.out.println(" y termina");
							}
							break;
						
						case 2: 
						
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                        tiempoMonitoreoCPU -= tiempoNecesario; 
                        System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");

                        if (procesos[i].getTiempoRestante() == 0) {
                            procesos[i] = null; 
                            System.out.println(" y termina");
                        }
							break;
						
						case 3: 
						int nuevoEstado = (int) (Math.random() * 2) + 1;
						if (nuevoEstado == 2) {
							procesos[i].setEstadoActual(1); 
							
						procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                        tiempoMonitoreoCPU -= tiempoNecesario; 
                        System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");

                        if (procesos[i].getTiempoRestante() == 0) {
                            procesos[i] = null; 
                            System.out.println(" y termina");
                        }
						} else {
							System.out.println("Entra Proceso "+procesos[i].getID()+", no se ejecuta porque sigue bloqueado");
							continue; 
						}
							break;
					}
	
				}
			}
	
		} while (ProcesosPendientes && tiempoMonitoreoCPU > 0);
	}

}
