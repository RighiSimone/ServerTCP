package echoserver;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoThread extends Thread {

    private Socket s;
    private String inFromUser;
    
    /**
     * Di Default la stringa inviata è uguale a quella ricevuta
     */
    private boolean isUpperCase = false;

    public EchoThread(Socket s) {

        this.s = s;
    }

    public void run() {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            
            while (true) {
                
                /**
                 * Acquisisco la Stringa
                 */
                inFromUser = in.readLine();
                
                /**
                 * Se la stringa che viene ricevuta equivale a "Fine"
                 */
                if(inFromUser.equalsIgnoreCase("fine")){
                    out.println("Terminato");
                }
                
                /**
                 * Maiuscolo : on
                 */
                else if(inFromUser.equalsIgnoreCase("Maiuscolo: on")){
                    isUpperCase = true;
                    out.println("Maiuscolo attivato!");
                }
                
                /**
                 * Maiuscolo : off
                 */
                else if(inFromUser.equalsIgnoreCase("Maiuscolo: off")){
                    isUpperCase = false;
                    out.println("Maiuscolo disattivato!");
                }
                
                /**
                 * Se non è nessuno dei casi precedenti
                 */
                else {
                    if(isUpperCase){
                        out.println(inFromUser.toUpperCase());
                    }else{
                        out.println(inFromUser);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
