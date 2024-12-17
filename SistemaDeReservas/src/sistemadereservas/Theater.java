
package sistemadereservas;

import java.util.Vector;
import javax.swing.JOptionPane;

public class Theater {
    String spots;
    String schecudleOne;
    String scheduleTwo;
    String movieOne;
    String movieTwo;
    Vector<String> availableSpots;
    Vector<String> busySpots;
    Vector<Vector<String>> reservedList;
    Vector<String> registeredIds;


    //Construtor
    public Theater(String spots, String schecudleOne, String scheduleTwo, String movieOne, String movieTwo, Vector<String> availableSpots, Vector<String> busySpots, Vector<Vector<String>> reservedList, Vector<String> registedIds) {
        this.spots = spots;
        this.schecudleOne = schecudleOne;
        this.scheduleTwo = scheduleTwo;
        this.movieOne = movieOne;
        this.movieTwo = movieTwo;
        this.availableSpots = availableSpots;
        this.busySpots = busySpots;
        this.reservedList = reservedList;
        this.registeredIds = registedIds;
    }

        
//    //Llama funciones que el usuario desea
//    public void gestion (Vector<Vector<String>> register) {
//        while (true) {
//            String option = JOptionPane.showInputDialog("""
//                                                        Estas en el apartado especial para nuestras salas de cine. 
//                                                        En esta seccion podras elegir que funcion deseas realizar
//                                                        Para facilidad agregamos un menu de opciones disponibles
//                                                        
//                                                        Menu:
//                                                        1. Crear una reservacion
//                                                        2. Realizar una actualizacion a la configuraciones
//                                                        
//                                                        Digite cualquier otra tecla para salir
//                                                        
//                                                        introduzaca la opcion que deseas realizar: """);
//            
//            if (option == null || !option.matches("[1-2]")) {
//                break;
//            }
//            switch (option) {
//                case "1" -> this.reservation(register);
//                case "2" -> this.updateSettings();
//            }
//        }
//        
//
//        
//        
//    }
        
    
    
    //Se crea solicitud para reserva de un spot en la sala de cine
    public String reservation(Vector <Vector<String>> register, String textOne, String textTwo) {
        Vector <String> confirmedList = new Vector<>();
        String view = this.view();
        
        String employeeId = textOne;
        for (int i = 0; i < register.size(); i++) {
            
                System.out.println("x");
            if (register.get(i).contains(employeeId)) {
                System.out.println("x");
                String selectedSpot = textTwo;
                System.out.println(textTwo);
                selectedSpot = selectedSpot.toUpperCase();
                if (availableSpots.contains(selectedSpot)) {
                    if (!registeredIds.contains(employeeId)) {
                        busySpots.add(selectedSpot);
                        availableSpots.remove(selectedSpot);
                        confirmedList.add(register.get(i).get(0));
                        confirmedList.add(register.get(i).get(1));
                        confirmedList.add(selectedSpot);
                        reservedList.add(confirmedList);
                        registeredIds.add(employeeId);
                        JOptionPane.showMessageDialog(null, "Reservacion realizada satisfactoriamente\n\nEmpleado: "+confirmedList.get(0)+"\nID: "+confirmedList.get(1)+"\nSpot Reservado: "+confirmedList.get(2));
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "El sistema ha detectado una reservacion previamente creada para este empleado");
                        break;
                    }
                } else {
                    break;
                }
            }  
        }
        return view;
    }  

    
    //Modificador de opciones predeterminadas de las salas
    public void updateSettings() {
        OUTER:
        while (true){
           settingsView();

            String specificOption = JOptionPane.showInputDialog("""
                                                                Perfecto, estas en la seccion para la modificacion de configuraciones
                                                                En este segmento lograras localizar las distintas opciones que son disponibles de ajustar
                                                                
                                                                Indique cual opcion desea modificar: 
                                                                
                                                                1. Pelicula 1 
                                                                2. Pelicula 2 
                                                                3. Horario de inicio pelicula 1 
                                                                4. Horario de inicio pelicula 2 
                                                                5. Spots disponibles en sala
                                                                
                                                                Digita cualquier otra tecla para salir""");
            
            if (specificOption == null || !specificOption.matches("[1-5]")) {
                break;
            }
            
            switch (specificOption) {
                case "1" -> this.movieOne = JOptionPane.showInputDialog("Introduzca el titulo de la pelicula que sera transmitida en la primer jornada: ");
                case "2" -> this.movieTwo = JOptionPane.showInputDialog("Introduzca el titulo de la pelicula que sera transmitida en la segunda jornada: ");
                case "3" -> this.schecudleOne = JOptionPane.showInputDialog("Introduzca el nuevo horario para la primer jornada de peliculas: ");
                case "4" -> this.scheduleTwo = JOptionPane.showInputDialog("Introduzca el nuevo horario para la segunda jornada de peliculas: ");
                case "5" -> this.spots = JOptionPane.showInputDialog("Introduzca la cantidad de spots disponibles para la sala (Maximo 30)");
            }
            JOptionPane.showMessageDialog(null, "Modificacion realizada satisfactoriamente\nTitulo de pelicula 1: "+this.movieOne+"\nTitulo de pelicula 2: "+this.movieTwo+"\nHorario 1: "+this.schecudleOne+"\nHorario 2: "+this.scheduleTwo);
            break;
        }
    }
    
    //Settings view
    public void settingsView () {
        System.out.println("Titulo: "+this.movieOne+" (Sera transmitida en el horario "+this.schecudleOne+")");
        System.out.println("Titulo: "+this.movieTwo+" (Sera transmitida en el horario "+this.scheduleTwo+")");
        System.out.println("Inicio de primer tanta: "+this.schecudleOne);
        System.out.println("inicio de segunda tanta: "+this.scheduleTwo);
        System.out.println("Cantidad de spots disponible en sala "+this.spots);
        System.out.println("");
    }

        
    //Visualizacion de los spots de la sala (FASE 2)
    public String view() { 
        String view = "\n";
        
        int columns = Integer.parseInt(this.spots)/5;
        String [][] arragement = new String [5][columns];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < columns; j++) {
                switch (i) {
                    case 0 -> arragement[i][j] = "A"+j;
                    case 1 -> arragement[i][j] = "B"+j;
                    case 2 -> arragement[i][j] = "C"+j;
                    case 3 -> arragement[i][j] = "D"+j;
                    case 4 -> arragement[i][j] = "E"+j;
                }
                String element = arragement[i][j];
                String spot = checker(element);
                view += spot+" ";
                
            }
            System.out.println("");
            view += "\n\n";
        }
        System.out.println("\nEspacios disponibles: "+this.availableSpots);
        System.out.println("Espacios ocupados: "+this.busySpots);
        

        
        return view;
    }
    
    //Valida si el spot esta disponible o ocupado
    public String checker (String element) {
        String spotchecker = "";
        if (!(this.busySpots.contains(element))) {
            if (!(this.availableSpots.contains(element))) {
                this.availableSpots.add(element);
            }
        } 
        if (this.availableSpots.contains(element)) {
            spotchecker = "<font color='green'>"+element+"</font>";
            System.out.print("\u001B[32m"+element+" "); 
        }
        if (this.busySpots.contains(element)) {
            spotchecker = "<font color='red'>"+element+"</font>";
            System.out.print("\u001B[37m"+element+" ");
        }
        return spotchecker;
    }
}
