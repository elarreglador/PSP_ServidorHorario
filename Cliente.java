import java.io.*;
import java.net.*;

public class Cliente {
    String IP = "localhost";
    int puerto = 6996;

    public Cliente(String IP, int puerto) {
        this.IP = IP;
        this.puerto = puerto;

        try{
        // Creamos socket de cliente
        Socket sCliente = new Socket(IP, puerto);
        // Asocio flujo de datos del server al socket de cliente
        InputStream aux = sCliente.getInputStream();
        DataInputStream flux = new DataInputStream(aux);

        // Muestra mensaje recibido
        System.out.println(flux.readUTF());
        
        // Cierra la comunicacion
        flux.close();
        sCliente.close();

        } catch (Exception e){
            System.out.println("Exception: "+ e);
        }
    }
}
