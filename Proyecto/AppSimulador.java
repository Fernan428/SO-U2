package Proyecto;
import java.util.*;

public class AppSimulador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double tiempoRestante = 0;
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

        int procesosEjecucion = (int) (Math.random() * 10) + 1;

        int usuariosActivos = (int) (Math.random() * 5) + 1;
        if (opcionAlgoritmo == 7) {
            System.out.println("Usuarios activos: " + usuariosActivos);
        }

        if (opcionTipo == 1 && opcionAlgoritmo != 5) {
            quantum = (int) (Math.random() * 3) + 2;
            System.out.println("Quantum para cada proceso: " + quantum + "\n");
        } else if (opcionAlgoritmo == 5) {
            System.out.println("Promesa: " + tiempoMonitoreoCPU / procesosEjecucion + "\n");
        }

        System.out.println("'Estado actual': [(1) En ejecución] [(2) Listo] [(3) Bloqueado]" + "\n");
        System.out.printf("+---------------------------------------------------------------------------+%n");
        System.out.printf("|                   Tabla de control de procesos (PCB)                      |%n");
        System.out.printf("+---------------------------------------------------------------------------+%n");
        System.out.printf("| %-15s %-20s %-15s %-10s %-10s|%n", "Procesos en", "Tiempo restante", "Estado actual",
                "Prioridad", "Usuario");
        System.out.printf("| %-15s %-20s %36s |%n", "ejecución", "de ejecución", "");
        System.out.printf("+---------------------------------------------------------------------------+%n");

        Proceso[] procesos = new Proceso[procesosEjecucion];

        for (int i = 0; i < procesosEjecucion; i++) {

            procesos[i] = new Proceso((tiempoRestante = (double) (Math.random() * 8) + 3),
                    (estadoActual = (int) (Math.random() * 3) + 1), ((int) (Math.random() * 4) + 1), i + 1, false,
                    (int) (Math.random() * usuariosActivos) + 1);
            System.out.printf("| %5d %18d %20d %12d %10d     |%n", procesos[i].getID(), procesos[i].getTiempoRestante(),
                    procesos[i].getEstadoActual(),
                    procesos[i].getPrioridad(), procesos[i].getUsuario());

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
                    MultiplesColasPrioridadApropiativo(procesos, quantum, tiempoMonitoreoCPU);
                    break;

                case 4:
                    ProcesoMasCortoPrimeroApropiativo(procesos, quantum, tiempoMonitoreoCPU);
                    break;

                case 5:
                    PlanificacionGarantizadaApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 6:
                    BoletosLoteriaApropiativo(procesos, quantum, tiempoMonitoreoCPU);
                    break;

                case 7:
                    ParticipacionEquitativaApropiativo(procesos, quantum, tiempoMonitoreoCPU, usuariosActivos);
                    break;

                default:
                    break;
            }

        } else if (opcionTipo == 2) {

            switch (opcionAlgoritmo) {
                case 1:
                    RoundRobinNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 2:
                    PrioridadesNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 3:
                    MultiplesColasPrioridadNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 4:
                    ProcesoMasCortoPrimeroNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 5:
                    PlanificacionGarantizadaNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 6:
                    BoletosLoteriaNoApropiativo(procesos, tiempoMonitoreoCPU);
                    break;

                case 7:
                    ParticipacionEquitativaNoApropiativo(procesos, tiempoMonitoreoCPU, usuariosActivos);
                    break;

                default:
                    break;
            }

        }

    }

    public static void RoundRobinApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU, int quantum) {

        boolean ProcesosPendientes;
        double tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;
        int inanicion = 0;

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
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
                                continue;
                            }
                            break;
                    }
                }

            }

            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        InformeFinal(procesos, totalEjecutado);
    }

    public static void RoundRobinNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

        boolean ProcesosPendientes;
        double tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;
        int inanicion = 0;

        do {
            ProcesosPendientes = false;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i].getTiempoRestante() > 0) {
                    ProcesosPendientes = true;

                    tiempoNecesario = procesos[i].getTiempoRestante();

                    if (tiempoMonitoreoCPU < tiempoNecesario) {

                        if (tiempoMonitoreoCPU == 0) {
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
                                continue;
                            }
                            break;
                    }

                }

            }
            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        ImprimirPCB(procesos);

        System.out.println("+---------------------------------------------------------------------------+");

        InformeFinal(procesos, totalEjecutado);

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

    public static void MultiplesColasPrioridadApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {

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

    public static void MultiplesColasPrioridadNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

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

    public static void PlanificacionGarantizadaApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

        boolean ProcesosPendientes;
        double tiempoNecesario;
        double promesa = tiempoMonitoreoCPU / procesos.length;
        int totalEjecutado = 0;
        boolean salir = false;
        int inanicion = 0;

        do {
            ProcesosPendientes = false;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i].getTiempoRestante() > 0) {
                    ProcesosPendientes = true;

                    if (procesos[i].getTiempoRestante() < promesa) {
                        tiempoNecesario = procesos[i].getTiempoRestante();
                    } else {
                        tiempoNecesario = promesa;
                    }

                    if (tiempoMonitoreoCPU < tiempoNecesario) {

                        if (tiempoMonitoreoCPU == 0) {
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
                                continue;
                            }
                            break;
                    }
                }

            }

            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        InformeFinal(procesos, totalEjecutado);

    }

    public static void PlanificacionGarantizadaNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

        boolean ProcesosPendientes;
        double tiempoNecesario;
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
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
                                continue;
                            }
                            break;
                    }

                }

            }
            if (salir)
                break;

        } while (ProcesosPendientes && tiempoMonitoreoCPU > 0);

        ImprimirPCB(procesos);

        System.out.println("+---------------------------------------------------------------------------+");

        InformeFinal(procesos, totalEjecutado);

    }

    public static void BoletosLoteriaApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {

        int boletos[] = new int[procesos.length];
        int contUnidades[] = new int[procesos.length];
        int tiempoRest = tiempoMonitoreoCPU, cantCambios = 0, procTerm = 0, bolUsados = 0;
        int[] boletosPorPrioridad = { 2, 4, 6, 8 };

        for (int i = 0; i < procesos.length; i++) {
            int prioridad = procesos[i].getPrioridad() - 1;
            if (prioridad >= 0 && prioridad < 4) {
                boletos[i] = boletosPorPrioridad[prioridad];
            } else {
                boletos[i] = 1;
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
                int numBol = (int) (Math.random() * loteria.length);
                while (loteria[numBol] != 0)
                    numBol = (int) (Math.random() * loteria.length);
                loteria[numBol] = i;
            }
        }
        while (tiempoRest != 0) {
            int bolGan = (int) (Math.random() * loteria.length);
            int pos = loteria[bolGan];

            if (pos >= procesos.length || pos < 0) {
                continue;
            } else if (procesos[pos] == null) {
                continue;
            } else {
                if (procesos[pos].getEstadoActual() == 3) {
                    int ejecuta = (int) (Math.random() * 2);
                    if (ejecuta == 1) {
                        procesos[pos].setEstadoActual(2);
                    } else {
                        continue;
                    }
                }
            }
            int tiempoPorEntrada = Math.min(quantum, Math.min(procesos[pos].getTiempoRestante(), tiempoRest));
            procesos[pos].setTiempoRestante(procesos[pos].getTiempoRestante() - tiempoPorEntrada);
            contUnidades[pos] += tiempoPorEntrada;
            tiempoRest -= tiempoPorEntrada;
            cantCambios++;
            System.out.println(
                    "Proceso " + procesos[pos].getID() + " ejecutado por " + tiempoPorEntrada + " unidades de tiempo.");
            if (procesos[pos].getTiempoRestante() == 0) {
                System.out.println("Proceso " + procesos[pos].getID() + " ha terminado.");
                procesos[pos] = null;
                procTerm++;
            }
            bolUsados++;
            loteria[bolGan] = procesos.length + 1;
            if (procTerm == procesos.length || bolUsados == loteria.length) {
                tiempoRest = 0;
            }
        }
        InformeFinal(procesos, totalEjecutado);
    }

    public static void BoletosLoteriaNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU) {

        int boletos[] = new int[procesos.length];
        int contUnidades[] = new int[procesos.length];
        int tiempoRestanteCPU = tiempoMonitoreoCPU, cantCambios = 0, procTerm = 0, bolUsados = 0;
        int[] boletosPorPrioridad = { 2, 4, 6, 8 };

        for (int i = 0; i < procesos.length; i++) {
            int prioridad = procesos[i].getPrioridad() - 1;
            if (prioridad >= 0 && prioridad < 4) {
                boletos[i] = boletosPorPrioridad[prioridad];
            } else {
                boletos[i] = 1;
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
                int numBol = (int) (Math.random() * loteria.length);
                while (loteria[numBol] != 0)
                    numBol = (int) (Math.random() * loteria.length);
                loteria[numBol] = i;
            }
        }
        while (tiempoRestanteCPU != 0) {
            int bolGan = (int) (Math.random() * loteria.length);
            int pos = loteria[bolGan];

            if (pos >= procesos.length || pos < 0) {
                continue;
            } else if (procesos[pos] == null) {
                continue;
            } else {
                if (procesos[pos].getEstadoActual() == 3) {
                    int ejecuta = (int) (Math.random() * 2);
                    if (ejecuta == 1) {
                        procesos[pos].setEstadoActual(2);
                    } else {
                        continue;
                    }
                }
            }
            int tiempoPorEntrada = Math.min(procesos[pos].getTiempoRestante(), tiempoRestanteCPU);
            procesos[pos].setTiempoRestante(procesos[pos].getTiempoRestante() - tiempoPorEntrada);
            contUnidades[pos] += tiempoPorEntrada;
            tiempoRestanteCPU -= tiempoPorEntrada;
            cantCambios++;
            System.out.println(
                    "Proceso " + procesos[pos].getID() + " ejecutado por " + tiempoPorEntrada + " unidades de tiempo.");
            if (procesos[pos].getTiempoRestante() == 0) {
                System.out.println("Proceso " + procesos[pos].getID() + " ha terminado.");
                procesos[pos] = null;
                procTerm++;
            }
            bolUsados++;
            loteria[bolGan] = procesos.length + 1;
            if (procTerm == procesos.length || bolUsados == loteria.length) {
                tiempoRestanteCPU = 0;
            }
        }
        InformeFinal(procesos, totalEjecutado);

    }

    public static void ParticipacionEquitativaApropiativo(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU,
            int usuariosActivos) {

        int contUnidades[] = new int[procesos.length];
        int cantCambios = 0;
        int tiempoRestanteCPU = tiempoMonitoreoCPU;
        int procTerm = 0;

        if (usuariosActivos <= 0) {
            return;
        }

        boolean usuariosConProcesos[] = new boolean[usuariosActivos];
        for (int i = 0; i < procesos.length; i++) {
            Proceso p = procesos[i];
            if (p != null && !usuariosConProcesos[p.getUsuario() - 1]) {
                usuariosConProcesos[p.getUsuario() - 1] = true;
            }
        }

        int tiempoPorUsuario = tiempoMonitoreoCPU / usuariosActivos;
        int[] tiempoRestanteUsuario = new int[usuariosActivos];
        for (int i = 0; i < tiempoRestanteUsuario.length; i++) {
            if (usuariosConProcesos[i]) {
                tiempoRestanteUsuario[i] = tiempoPorUsuario;
            }
        }

        System.out.println("Tiempo de CPU para cada Usuario: " + tiempoPorUsuario);

        while (tiempoRestanteCPU > 0 && procTerm < procesos.length) {
            boolean todosProcesosTerminado = true;
            boolean todosSinTiempo = true;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i] != null && procesos[i].getTiempoRestante() > 0) {
                    todosProcesosTerminado = false;
                }
            }

            if (todosProcesosTerminado) {
                tiempoRestanteCPU = 0;
                break;
            }

            for (int i = 0; i < usuariosActivos; i++) {
                if (tiempoRestanteUsuario[i] > 0) {
                    todosSinTiempo = false;
                    break;
                }
            }

            if (todosSinTiempo) {
                tiempoRestanteCPU = 0;
                break;
            }

            for (int j = 0; j < procesos.length; j++) {
                if (procesos[j] == null)
                    continue;

                int usuario = procesos[j].getUsuario() - 1;

                if (tiempoRestanteUsuario[usuario] <= 0 || !usuariosConProcesos[usuario]) {
                    continue;
                }

                if (procesos[j].getEstadoActual() == 3) {
                    int ejecuta = (int) (Math.random() * 2);
                    if (ejecuta == 1) {
                        procesos[j].setEstadoActual(2);
                        System.out.println("Proceso " + (j + 1) + " listo para ejecutar");
                        cantCambios++;
                    } else {
                        System.out.println("Proceso " + (j + 1) + " bloqueado, no se puede ejecutar");
                        cantCambios++;
                        continue;
                    }
                }

                if ((procesos[j].getEstadoActual() == 1 || procesos[j].getEstadoActual() == 2)
                        && tiempoRestanteCPU > 0) {
                    int tiempoAsignado = Math.min(procesos[j].getTiempoRestante(),
                            Math.min(tiempoRestanteUsuario[usuario], Math.min(tiempoRestanteCPU, quantum)));

                    tiempoRestanteCPU -= tiempoAsignado;
                    tiempoRestanteUsuario[usuario] -= tiempoAsignado;
                    procesos[j].setTiempoRestante(procesos[j].getTiempoRestante() - tiempoAsignado);
                    contUnidades[j] += tiempoAsignado;
                    cantCambios++;

                    System.out.println("Proceso " + procesos[j].getID() + " ejecutado por " + tiempoAsignado
                            + " unidades de tiempo.");

                    if (procesos[j].getTiempoRestante() == 0) {
                        System.out.println("Proceso " + procesos[j].getID() + " ha terminado.");
                        procesos[j] = null;
                        procTerm++;
                    }
                }
            }
        } 
        InformeFinal(procesos, totalEjecutado);

    }

    public static void ParticipacionEquitativaNoApropiativo(Proceso[] procesos, int tiempoMonitoreoCPU,
            int usuariosActivos) {

        int contUnidades[] = new int[procesos.length];
        int cantCambios = 0;
        int tiempoRestanteCPU = tiempoMonitoreoCPU;
        int procTerm = 0;

        if (usuariosActivos <= 0) {
            return;
        }

        boolean usuariosConProcesos[] = new boolean[usuariosActivos];
        for (int i = 0; i < procesos.length; i++) {
            Proceso p = procesos[i];
            if (p != null && !usuariosConProcesos[p.getUsuario() - 1]) {
                usuariosConProcesos[p.getUsuario() - 1] = true;
            }
        }

        int tiempoPorUsuario = tiempoMonitoreoCPU / usuariosActivos;
        int[] tiempoRestanteUsuario = new int[usuariosActivos];
        for (int i = 0; i < tiempoRestanteUsuario.length; i++) {
            if (usuariosConProcesos[i]) {
                tiempoRestanteUsuario[i] = tiempoPorUsuario;
            }
        }

        System.out.println("Tiempo de CPU para cada Usuario: " + tiempoPorUsuario);

        while (tiempoRestanteCPU > 0 && procTerm < procesos.length) {
            boolean todosProcesosTerminado = true;
            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i] != null && procesos[i].getTiempoRestante() > 0) {
                    todosProcesosTerminado = false;
                    break;
                }
            }

            if (todosProcesosTerminado) {
                tiempoRestanteCPU = 0;
                break;
            }

            boolean todosSinTiempo = true;
            for (int i = 0; i < usuariosActivos; i++) {
                if (tiempoRestanteUsuario[i] > 0) {
                    todosSinTiempo = false;
                    break;
                }
            }

            if (todosSinTiempo) {
                tiempoRestanteCPU = 0;
                break;
            }

            for (int j = 0; j < procesos.length; j++) {
                if (procesos[j] == null)
                    continue;

                int usuario = procesos[j].getUsuario() - 1;

                if (tiempoRestanteUsuario[usuario] <= 0 || !usuariosConProcesos[usuario]) {
                    continue;
                }

                if (procesos[j].getEstadoActual() == 3) {
                    int ejecuta = (int) (Math.random() * 2);
                    if (ejecuta == 1) {
                        procesos[j].setEstadoActual(2);
                        System.out.println("Proceso " + (j + 1) + " listo para ejecutar");
                        cantCambios++;
                    } else {
                        System.out.println("Proceso " + (j + 1) + " bloqueado, no se puede ejecutar");
                        cantCambios++;
                        continue;
                    }
                }

                if ((procesos[j].getEstadoActual() == 1 || procesos[j].getEstadoActual() == 2)
                        && tiempoRestanteCPU > 0) {
                    int tiempoAsignado = Math.min(procesos[j].getTiempoRestante(),
                            Math.min(tiempoRestanteUsuario[usuario], tiempoRestanteCPU));

                    tiempoRestanteCPU -= tiempoAsignado;
                    tiempoRestanteUsuario[usuario] -= tiempoAsignado;
                    procesos[j].setTiempoRestante(procesos[j].getTiempoRestante() - tiempoAsignado);
                    contUnidades[j] += tiempoAsignado;
                    cantCambios++;

                    System.out.println("Proceso " + procesos[j].getID() + " ejecutado por " + tiempoAsignado
                            + " unidades de tiempo.");

                    if (procesos[j].getTiempoRestante() == 0) {
                        System.out.println("Proceso " + procesos[j].getID() + " ha terminado.");
                        procesos[j] = null;
                        procTerm++;
                    }
                }
            }
        }
        System.out.println("Salió del ciclo");

        InformeFinal(procesos, totalEjecutado);
    }

    public static void ProcesoApropiativoGeneral(Proceso[] procesos, int quantum, int tiempoMonitoreoCPU) {
        boolean ProcesosPendientes;
        double tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;
        int inanicion = 0;

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
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
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
        double tiempoNecesario;
        int totalEjecutado = 0;
        boolean salir = false;
        int inanicion = 0;

        do {
            ProcesosPendientes = false;

            for (int i = 0; i < procesos.length; i++) {
                if (procesos[i].getTiempoRestante() > 0) {
                    ProcesosPendientes = true;

                    tiempoNecesario = procesos[i].getTiempoRestante();

                    if (tiempoMonitoreoCPU < tiempoNecesario) {

                        if (tiempoMonitoreoCPU == 0) {
                            System.out.println("Tiempo de simulación terminado");
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
                                inanicion++;
                                if(inanicion >= 5) {
                                    salir = true;
                                    System.out.println("La simulación murió de inanición");
                                    break;
                                }
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

        ImprimirPCB(procesos);

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

        System.out.print("\n" + "Procesos que nunca entraron en ejecución:");

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

    public static void ImprimirPCB(Proceso[] procesos) {
        System.out.println("'Estado actual': [(1) En ejecución] [(2) Listo] [(3) Bloqueado]" + "\n");
        System.out.printf("+---------------------------------------------------------------------------+%n");
        System.out.printf("|                   Tabla de control de procesos (PCB)                      |%n");
        System.out.printf("+---------------------------------------------------------------------------+%n");
        System.out.printf("| %-15s %-20s %-15s %-10s %-10s|%n", "Procesos en", "Tiempo restante", "Estado actual",
                "Prioridad", "Usuario");
        System.out.printf("| %-15s %-20s %36s |%n", "ejecución", "de ejecución", "");
        System.out.printf("+---------------------------------------------------------------------------+%n");
        for (int i = 0; i < procesos.length; i++) {
            System.out.printf("| %5d %18d %20d %12d %10d     |%n", procesos[i].getID(), procesos[i].getTiempoRestante(),
                    procesos[i].getEstadoActual(),
                    procesos[i].getPrioridad(), procesos[i].getUsuario());
        }
        System.out.printf("+---------------------------------------------------------------------------+%n");

    }
}
