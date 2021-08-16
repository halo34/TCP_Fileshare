package ClientServer.view;

import ClientServer.Komponente.Client;

public class ClientUI {

    private Client client;

    public ClientUI(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
