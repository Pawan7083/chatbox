
import java.net.*;
import java.io.*;

public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Client(){
        System.out.println("Client is ready to connection ");
        try {
            socket=new Socket("127.0.0.1",9999);
            System.out.println("Connection done!");
            
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            
            System.out.println("PrintWriter is started");
            startReading();
            startWriting();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void startReading(){
        Runnable r1=()->{
            System.out.println("Reader Started");

            while(true){
                try {
                    String content=br.readLine();
                    if(content.equals("exit")){
                        System.out.println("Server terminated the connection ");
                        break;
                    }
                    System.out.println("Server : "+content);
                
                } catch (Exception e) {
                    e.printStackTrace();
                }
              
            }
        };

        new Thread(r1).start();
    }

    void startWriting(){
        Runnable r2=()->{
            System.out.println("Wrinting started");
            while(true){
                try {
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content=br1.readLine();
                    out.println(content);
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        };
        new Thread(r2).start();
    }
    public static void main(String arg[]){
        Client client=new Client();
    }
}
