package ClientServer.view;

import ClientServer.Komponente.Server;

public class ServerUI {
    private Server server;

    //Constructor
    public ServerUI(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
