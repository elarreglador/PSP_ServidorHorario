import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

// El servidor horario queda a la espera de forma perpetua

public class Servidor {
    int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;

        for (int numCli = 1; true; numCli++) {
            try {
                String hora;
                ServerSocket ssServidor = new ServerSocket(puerto);
                System.out.println("Atendiendo al puerto " + puerto);

                // CONEXION CON EL CLIENTE
                // Espera conexion de cliente y la asocia a la variable sCliente
                System.out.println("A la espera de nueva conexion...");
                Socket sCliente = ssServidor.accept();
                System.out.println("Conectado cliente Num." + numCli);
                // Asocia el flujo de salida al socket del cliente
                OutputStream aux = sCliente.getOutputStream();
                DataOutputStream flux = new DataOutputStream(aux);

                // da la hora al cliente
                hora = miHora();
                flux.writeUTF(hora);
                System.out.println("Entregada hora: " + hora);

                // cierra la conexion
                sCliente.close();
                ssServidor.close();

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } 
        }
    }

    public String miHora() {
        // DEVUELVE LA HORA ACTUAL

        // obtiene el tiempo actual en milisegundos
        // desde el 1 de enero de 1970 a las 00:00:00 UTC
        // (también conocido como el "Tiempo Unix" o "Época Unix")
        long millis = System.currentTimeMillis();

        // Convertimos la hora a un formato mas civilizado
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String localTime = sdf.format(date);

        return localTime;
    }

}