
import java.net.*;
import java.io.*;

public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public Server(){
        try{
            server=new ServerSocket(9999);
            System.out.println("Server is ready to accept connection");
            System.out.println("Waiting...");
            socket=server.accept();
            
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            
            startReading();
            startWriting();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startReading(){
        Runnable r1=()->{
            System.out.println("Reader Started");

            while(true){
                try{
                String msg=br.readLine();
                if(msg.equals("exit")){
                    System.out.println("Client terminated ");
                    break;
                }
                System.out.println("Client : "+msg);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            }

        };

        new Thread(r1).start();
    }

    public void startWriting(){
        Runnable r2=()->{
            System.out.println("Writing Started");

            while(true){
                try{
                BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                
                String content=br1.readLine();
                out.println(content);
                out.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            
            
        };
        new Thread(r2).start();
    }

    public static  void main(String arg[]){
        new Server();
    }
}
