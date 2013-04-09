package se.chalmers.tda367.group15.game.net.packets;

import se.chalmers.tda367.group15.game.net.GameClient;
import se.chalmers.tda367.group15.game.net.GameServer;

public class Packet01Disconnect extends Packet {

    private String username;

    public Packet01Disconnect(byte[] data) {
        super(01);
        this.username = readData(data);
    }

    public Packet01Disconnect(String username) {
        super(01);
        this.username = username;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("01" + this.username).getBytes();
    }

    public String getUsername() {
        return username;
    }

}
