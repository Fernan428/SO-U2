package Proyecto;

import java.util.*;

public class AppSimulador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tiempoRestante = 0;
        int estadoActual = 0;
        int prioridad = 0;
        int quantum = 0;

        System.out.println("******************************************************");
        System.out.println("   Seleccione el tipo de algoritmo de planificación");
        System.out.println("******************************************************");
        System.out.println("[ '1' Apropiativos]");
        System.out.println("[ '2' No-apropiativos]");
        System.out.println("*******************************************************");

        System.out.print("Opcion: ");
        int opcionTipo = sc.nextInt();
        System.out.println();

        System.out.println("*************************************************");
        System.out.println("        Seleccione el algoritmo a simular");
        System.out.println("*************************************************");
        System.out.println("\t" + "[ '1' Round-robin]");
        System.out.println("\t" + "[ '2' Prioridades]");
        System.out.println("\t" + "[ '3' Múltiples colas de prioridad]");
        System.out.println("\t" + "[ '4' Proceso más corto primero]");
        System.out.println("\t" + "[ '5' Planificación garantizada]");
        System.out.println("\t" + "[ '6' Boletos de Lotería]");
        System.out.println("\t" + "[ '7' Participación equitativa]");
        System.out.println("*************************************************");

        System.out.print("Opcion: ");
        int opcionAlgoritmo = sc.nextInt();
        System.out.println();

        int tiempoMonitoreoCPU = (int) (Math.random() * 11) + 15;
        System.out.println("Tiempo a simular: " + tiempoMonitoreoCPU);

        if (opcionTipo == 1) {
            quantum = (int) (Math.random() * 3) + 2;
            System.out.println("Quantum para cada proceso: " + quantum + "\n");
        }

        System.out.println("'Estado actual': [(1) En ejecución] [(2) Listo] [(3) Bloqueado]" + "\n");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                   Tabla de control de procesos (PCB)                      |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|" + "\t" + "Procesos en" + "\t" + "Tiempo restante  " + "\t" + "Estado actual" + "\t"
                + "Prioridad" + "   |");
        System.out.println(
                "|" + "\t" + " ejecución " + "\t  " + "de ejecución                                   " + "   |");
        System.out.println("+---------------------------------------------------------------------------+");

        int procesosEjecucion = (int) (Math.random() * 10) + 1;

        Proceso[] procesos = new Proceso[procesosEjecucion];

        for (int i = 0; i < procesosEjecucion; i++) {

            procesos[i] = new Proceso((tiempoRestante = (int) (Math.random() * 8) + 3),(estadoActual = (int) (Math.random() * 3) + 1), ((int) (Math.random() * 4) + 1), i + 1, false, 2);
            System.out.println("|" + "\t" + "    " + (i + 1) + "\t" + "\t" + "\t" + procesos[i].getTiempoRestante()
                    + "\t" + "\t" + "     " + procesos[i].getEstadoActual() + "\t" + "\t" + "    "
                    + procesos[i].getPrioridad() + "       |");

        }
        System.out.println("+---------------------------------------------------------------------------+");

        if (opcionTipo == 1) {

            switch (opcionAlgoritmo) {
                case 1:
                    RoundRobinApropiativo(procesos, tiempoMonitoreoCPU, quantum);
                    break;

                case 2:
                    PrioridadesApropiativo(procesos, quantum, tiempoMonitoreoCPU);
                    break;

                case 3:
                    MúltiplesColasPrioridadApropiativo(procesos, quantum, tiempoMonitoreoCPU);
                    break;

                case 4:
                    ProcesoMasCortoPrimeroApropiativo(procesos, quantum, tiempoMonitoreoCPU);
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

        } else if (opcionTipo == 2) {

            switch (opcionAlgoritmo) {
                case 1:
                    RoundRobinNoApropiativo(procesos, quantum);
                    break;

                case 2:
                    PrioridadesNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 3:
                    MúltiplesColasPrioridadNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 4:
                    ProcesoMasCortoPrimeroNoApropiativo(procesos, tiempoMonitoreoCPU);
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

    public static void RoundRobinApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU, int quantum) {

    }

    public static void RoundRobinNoApropiativo(Proceso[] procesos, int quantum) {

    }

    public static void PrioridadesApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {

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

    public static void PrioridadesNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

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

        ProcesoNoApropiativoGeneral(procesos, tiempoMonitoreoCPU);

    }

    public static void MúltiplesColasPrioridadApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {

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

    public static void MúltiplesColasPrioridadNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

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

        ProcesoNoApropiativoGeneral(procesos, tiempoMonitoreoCPU);

    }

    public static void ProcesoMasCortoPrimeroApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {

        int n = procesos.length;

        for (int i = 0; i < n - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < n; j++) {
                if (procesos[j].getTiempoRestante() < procesos[menor].getTiempoRestante()) {
                    menor = j;
                }
            }

            Proceso temp = procesos[i];
            procesos[i] = procesos[menor];
            procesos[menor] = temp;
        }

        System.out.println();

        ProcesoApropiativoGeneral(procesos, quantum, tiempoMonitoreoCPU);

    }

    public static void ProcesoMasCortoPrimeroNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

        int n = procesos.length;

        for (int i = 0; i < n - 1; i++) {
            int menor = i;

            for (int j = i + 1; j < n; j++) {
                if (procesos[j].getTiempoRestante() < procesos[menor].getTiempoRestante()) {
                    menor = j;
                }
            }

            Proceso temp = procesos[i];
            procesos[i] = procesos[menor];
            procesos[menor] = temp;
        }

        System.out.println();

        ProcesoNoApropiativoGeneral(procesos, tiempoMonitoreoCPU);

    }

    public static void PlanificacionGarantizadaApropiativo() {

    }

    public static void PlanificacionGarantizadaNoApropiativo() {

    }

    public static void BoletosLoteriaApropiativo() {

    }

    public static void BoletosLoteriaNoApropiativo() {

    }

    public static void ParticipacionEquitativaApropiativo() {

    }

    public static void ParticipacionEquitativaNoApropiativo() {

    }

    public static void ProcesoApropiativoGeneral(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {
        boolean ProcesosPendientes;
        int tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;

        do {
            ProcesosPendientes = false;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i].getTiempoRestante() > 0) {
                    ProcesosPendientes = true;

                    if (procesos[i].getTiempoRestante() < quantum) {
                        tiempoNecesario = procesos[i].getTiempoRestante();
                    } else {
                        tiempoNecesario = quantum;
                    }

                    if (tiempoMonitoreoCPU < tiempoNecesario) {

                        if (tiempoMonitoreoCPU == 0) {
                            System.out.println("No hay suficiente tiempo de monitoreo para ejecutar el proceso "
                                    + procesos[i].getID() + "\n" + "Fin de la simulacion");
                            salir = true;
                            break;
                        } else {
                            tiempoNecesario = tiempoMonitoreoCPU;
                        }
                    }

                    switch (procesos[i].getEstadoActual()) {
                        case 1:
                            procesos[i].setEntra(true);
                            procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                            tiempoMonitoreoCPU -= tiempoNecesario;
                            if (procesos[i].getTiempoRestante() == 0) {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                            }
                            totalEjecutado++;
                            break;

                        case 2:
                            procesos[i].setEstadoActual(1);
                            procesos[i].setEntra(true);
                            procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                            tiempoMonitoreoCPU -= tiempoNecesario;
                            if (procesos[i].getTiempoRestante() == 0) {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                            }
                            totalEjecutado++;
                            break;

                        case 3:
                            int nuevoEstado = (int) (Math.random() * 2);
                            if (nuevoEstado == 1) {
                                procesos[i].setEstadoActual(2);
                                procesos[i].setEntra(true);
                                procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                                tiempoMonitoreoCPU -= tiempoNecesario;
                                if (procesos[i].getTiempoRestante() == 0) {
                                    System.out
                                            .println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                                } else {
                                    System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                                }
                                totalEjecutado++;
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID()
                                        + ", no se ejecuta porque sigue bloqueado");
                                totalEjecutado++;
                                continue;
                            }
                            break;
                    }
                }

                if (procesos[i].getTiempoRestante() != 0) {
                    i -= 1;
                }

            }

            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        InformeFinal(procesos, totalEjecutado);

    }

    public static void ProcesoNoApropiativoGeneral(Proceso[] procesos, int tiempoMonitoreoCPU) {
        boolean ProcesosPendientes;
        int tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;

        do {
            ProcesosPendientes = false;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i].getTiempoRestante() > 0) {
                    ProcesosPendientes = true;

                    tiempoNecesario = procesos[i].getTiempoRestante();

                    if (tiempoMonitoreoCPU < tiempoNecesario) {

                        if (tiempoMonitoreoCPU == 0) {
                            System.out
                                    .println("No hay suficiente tiempo de monitoreo para ejecutar/terminar el proceso "
                                            + procesos[i].getID() + "\n" + "Fin de la simulacion");
                            salir = true;
                            break;
                        } else {
                            tiempoNecesario = tiempoMonitoreoCPU;
                        }
                    }

                    switch (procesos[i].getEstadoActual()) {
                        case 1:
                            procesos[i].setEntra(true);
                            procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                            tiempoMonitoreoCPU -= tiempoNecesario;
                            if (procesos[i].getTiempoRestante() == 0) {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                            }
                            totalEjecutado++;
                            break;

                        case 2:
                            procesos[i].setEstadoActual(1);
                            procesos[i].setEntra(true);
                            procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                            tiempoMonitoreoCPU -= tiempoNecesario;
                            if (procesos[i].getTiempoRestante() == 0) {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                            }
                            totalEjecutado++;
                            break;

                        case 3:
                            int nuevoEstado = (int) (Math.random() * 2);
                            if (nuevoEstado == 1) {
                                procesos[i].setEstadoActual(2);
                                procesos[i].setEntra(true);
                                procesos[i].setTiempoRestante(procesos[i].getTiempoRestante() - tiempoNecesario);
                                tiempoMonitoreoCPU -= tiempoNecesario;
                                if (procesos[i].getTiempoRestante() == 0) {
                                    System.out
                                            .println("Entra Proceso " + procesos[i].getID() + ", se ejecuta y termina");
                                } else {
                                    System.out.println("Entra Proceso " + procesos[i].getID() + ", se ejecuta");
                                }
                                totalEjecutado++;
                            } else {
                                System.out.println("Entra Proceso " + procesos[i].getID()
                                        + ", no se ejecuta porque sigue bloqueado");
                                totalEjecutado++;
                                continue;
                            }
                            break;
                    }

                }

                if (procesos[i].getTiempoRestante() != 0) {
                    i -= 1;
                }

            }
            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|                   Tabla de control de procesos (PCB)                      |");
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("|" + "\t" + "Procesos en" + "\t" + "Tiempo restante  " + "\t" + "Estado actual" + "\t"
                + "Prioridad" + "   |");
        System.out.println(
                "|" + "\t" + " ejecución " + "\t  " + "de ejecución                                   " + "   |");
        System.out.println("+---------------------------------------------------------------------------+");

        for (int i = 0; i < procesos.length; i++) {

            System.out.println(
                    "|" + "\t" + "    " + procesos[i].getID() + "\t" + "\t" + "\t" + procesos[i].getTiempoRestante()
                            + "\t" + "\t" + "     " + procesos[i].getEstadoActual() + "\t" + "\t" + "    "
                            + procesos[i].getPrioridad() + "       |");

        }
        System.out.println("+---------------------------------------------------------------------------+");

        InformeFinal(procesos, totalEjecutado);

    }

    public static void InformeFinal(Proceso[] procesos, int totalEjecutado) {
        boolean existe = false;
        System.out.println("\n" + "****Informe final de ejecución****" + "\n");

        System.out.print("Procesos que terminaron:");

        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i].getTiempoRestante() == 0) {
                existe = true;
                System.out.print(" " + procesos[i].getID());
            }
        }

        if (!existe)
            System.out.print(" Ninguno");

        System.out.print("\n" + "Procesos que nunca entraron en ejecucion:");

        existe = false;
        for (int i = 0; i < procesos.length; i++) {
            if (!procesos[i].isEntra()) {
                existe = true;
                System.out.print(" " + procesos[i].getID());
            }
        }
        if (!existe) {
            System.out.print(" Ninguno");
        }

        System.out.print("\n" + "Procesos que siguen en ejecución:");
        existe = false;

        for (int i = 0; i < procesos.length; i++) {
            if (procesos[i].isEntra() && procesos[i].getTiempoRestante() != 0) {
                existe = true;
                System.out.print(" " + procesos[i].getID());
            }
        }
        if (!existe)
            System.out.print(" Ninguno");

        System.out.print("\n" + "Cantidad de cambios de procesos: ");
        System.out.println(totalEjecutado);

    }
}