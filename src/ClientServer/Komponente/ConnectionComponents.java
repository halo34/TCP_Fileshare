package ClientServer.Komponente;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.format.DateTimeParseException;

public abstract class ConnectionComponents {
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Socket socket;

    public OutputInterface getOutputInterface() {
        return outputInterface;
    }

    public ConnectionComponents(OutputInterface outputInterface) {
        this.outputInterface = outputInterface;
    }

    private static final int BUFFER_SIZE = 4096;
    private final OutputInterface outputInterface;

    public void sendFile(Path path) {
        int bytes;
        try (InputStream fileInputStream = Files.newInputStream(path, StandardOpenOption.READ)){
            dataOutputStream.writeUTF(path.getFileName().toString());
            dataOutputStream.writeLong(Files.size(path));
            byte[] buffer = new byte[BUFFER_SIZE];
            while (((bytes =fileInputStream.read(buffer)))!=-1) {
                dataOutputStream.write(buffer,0,bytes);
                dataOutputStream.flush();
            }
            dataOutputStream.writeUTF(Files.getLastModifiedTime(path).toInstant().toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void receiveFile() {
        int bytes;
        Path filePath = null;
        try {
            filePath = Path.of(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream fileOutputStream = Files.newOutputStream(filePath)){

            long size = dataInputStream.readLong();
//            long originalSize = size;
            byte[] buffer = new byte[BUFFER_SIZE];
            while (size>0 && (bytes = dataInputStream.read(buffer,0,(int) Math.min(buffer.length, size)))!=-1) {
                fileOutputStream.write(buffer,0,bytes);
                size-=bytes;
//                System.out.println((originalSize-size)/originalSize+" %");
            }
            Instant modifiedTime = Instant.parse(dataInputStream.readUTF());
            Files.setLastModifiedTime(filePath, FileTime.from(modifiedTime));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (DateTimeParseException exception) {
            exception.printStackTrace();
        }
    }
    public void closeConnection() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();
    }
    public abstract void run();

    public abstract void runOldVersion();

    //Getter and Setters

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
