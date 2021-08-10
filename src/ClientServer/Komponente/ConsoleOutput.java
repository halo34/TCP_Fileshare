package ClientServer.Komponente;

public class ConsoleOutput implements OutputInterface {
    @Override
    public void sendErrorMessage(String message) {
        System.err.println(message);
    }

    @Override
    public void PrintState(String message) {
        System.out.println(message);
    }
}
