package lopreiato.it;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyThread extends Thread{
    
    private Socket s;

    public MyThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try{
            System.out.println("Server in avvio!");

            ServerSocket ss = new ServerSocket(5637);

            Socket s = ss.accept(); //istruzione bloccante (in attesa di un collegamento, se qualcuno si collega ritorna un socket)

            System.out.println("Un client si è collegato");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String stringaRicevuta = in.readLine();
            System.out.println("La stringa ricevuta:" + stringaRicevuta);

            String stringaMaiuscola = stringaRicevuta.toUpperCase();
            out.writeBytes(stringaMaiuscola + '\n');

            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println("errore");
        }
    }
}
