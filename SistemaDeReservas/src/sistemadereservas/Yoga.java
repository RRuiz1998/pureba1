package sistemadereservas;

import java.util.Vector;
import javax.swing.JOptionPane;

public class Yoga {

    private final int maxCupos = 30; // Máximo de cupos por horario.
    private int reservas7pm = 0;
    private int reservas8pm = 0;
    private final String[] reservas = new String[maxCupos * 2]; // Reservas almacenadas.

    Vector<Vector<String>> list7pm = new Vector<>();
    Vector<Vector<String>> list8pm = new Vector<>();

    // Registra una nueva reserva
    public void registrarReserva(Vector<Vector<String>> register, String text) {
        Vector<String> listSet = new Vector<>();
        String id = text;
        if (id == null || !id.matches("\\d{6}")) {
            JOptionPane.showMessageDialog(null, "ID inválido. Por favor, intente nuevamente.");
            return;
        }

        // Verifica si el ID existe en los registros
        Vector<String> usuario = null;
        for (Vector<String> registro : register) {
            if (registro.get(1).equals(id)) {
                usuario = registro;
                break;
            }
        }

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "ID no encontrado. Por favor, intente nuevamente.");
            return;
        }

        String horario = JOptionPane.showInputDialog("""
            Horarios disponibles:
            1. Clase a las 7pm
            2. Clase a las 8pm
            
            Seleccione el horario deseado:""");

        if (horario == null || !horario.matches("[1-2]")) {
            JOptionPane.showMessageDialog(null, "Seleccione un horario válido.");
            return;
        }

        String mensaje;
        if (horario.equals("1") && reservas7pm < maxCupos) {
            reservas[getIndiceReserva()] = usuario.get(0) + " (" + usuario.get(1) + ") - 7pm";
            reservas7pm++;
            mensaje = "Reserva registrada exitosamente para " + usuario.get(0) + " en la clase de las 7pm.";
            listSet.add(usuario.get(0));
            listSet.add(usuario.get(1));
            list7pm.add(listSet);

        } else if (horario.equals("2") && reservas8pm < maxCupos) {
            reservas[getIndiceReserva()] = usuario.get(0) + " (" + usuario.get(1) + ") - 8pm";
            reservas8pm++;
            mensaje = "Reserva registrada exitosamente para " + usuario.get(0) + " en la clase de las 8pm.";
            listSet.add(usuario.get(0));
            listSet.add(usuario.get(1));
            list8pm.add(listSet);

        } else {
            mensaje = "No hay cupos disponibles en el horario seleccionado.";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Cancela una reserva
    public void cancelarReserva(String text) {
        String idReservaStr = text;
        if (idReservaStr == null || !idReservaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
            return;
        }

        int idReserva = Integer.parseInt(idReservaStr);
        if (idReserva > 0 && idReserva <= reservas.length && reservas[idReserva - 1] != null) {
            String reserva = reservas[idReserva - 1];
            reservas[idReserva - 1] = null; // Elimina la reserva
            if (reserva.contains("7pm")) {
                reservas7pm--;
            } else if (reserva.contains("8pm")) {
                reservas8pm--;
            }
            JOptionPane.showMessageDialog(null, "Reserva " + idReserva + " cancelada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "ID de reserva no encontrado.");
        }
    }

    //Visualizacion
    public void mostrarReservas() {
        StringBuilder listado = new StringBuilder("Reservas actuales:\n");
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null) {
                listado.append("ID ").append(i + 1).append(": ").append(reservas[i]).append("\n");
            }
        }
        listado.append("\nTotal de reservas:\n").append(reservas7pm).append(" en 7pm\n").append(reservas8pm).append(" en 8pm.");
//        JOptionPane.showMessageDialog(null, listado.toString());

    }

    public Vector<Vector<Vector<String>>> listas() {
        Vector<Vector<Vector<String>>> list = new Vector<>();

        list.add(list7pm);
        list.add(list8pm);

        return list;
    }

    private int getIndiceReserva() {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
