package ClientServer.Komponente;

import java.io.*;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server extends ConnectionComponents implements Runnable{
    private BufferedReader bufferedReader;
    private int port;

    public Server() {
        this(5000);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Server(int port) {
        super(new ConsoleOutput());
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server();
        System.out.println(Paths.get("").toAbsolutePath().toString());
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        System.out.println(Path.of(System.getProperty("user.home") + "/Desktop").toString());
        server.runOldVersion();
    }


    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("listening to port: 5000");
            setSocket(serverSocket.accept());
            System.out.println("Client connect");
            setDataInputStream(new DataInputStream(getSocket().getInputStream()));
            setDataOutputStream(new DataOutputStream(getSocket().getOutputStream()));
            Path destination = Path.of("era_tutor09.pdf");
            receiveFile();
            closeConnection();
            getSocket().close();
        } catch (ConnectException e) {
            System.err.println(e);
        } catch (BindException bindException) {
            System.err.println(bindException);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void runOldVersion() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("listening to port: 5000");
            setSocket(serverSocket.accept());
            System.out.println("Client connect");
            setDataInputStream(new DataInputStream(getSocket().getInputStream()));
            setDataOutputStream(new DataOutputStream(getSocket().getOutputStream()));

            receiveFile();
            closeConnection();
            getSocket().close();
        } catch (ConnectException e) {
            System.err.println(e);
        } catch (BindException bindException) {
            System.err.println(bindException);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
