package ClientServer.Komponente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client extends ConnectionComponents implements Runnable{

    private List<Path> files;
    public static void main(String[] args) {
        Client client = new Client();
        client.files = Arrays.stream(args).map(Path::of).collect(Collectors.toList());
        client.runOldVersion();

    }

    private String host;
    private int port;

    public Client() {
        this("localhost",5000);
    }

    public Client(String host, int port) {
        super(new ConsoleOutput());
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port)) {
            setDataInputStream(new DataInputStream(socket.getInputStream()));
            setDataOutputStream(new DataOutputStream(socket.getOutputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void runOldVersion() {
        try (Socket socket = new Socket(host, port)) {
            setDataInputStream(new DataInputStream(socket.getInputStream()));
            setDataOutputStream(new DataOutputStream(socket.getOutputStream()));
//            Path origin = Path.of("E:/Downloads/era_tutor09.pdf");
//
//            sendFile(origin);
            files.forEach(this::sendFile);
            FileSyncUtil.sleepFiveSeconds();
            getDataOutputStream().writeUTF("Ich bin toll");
            closeConnection();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
