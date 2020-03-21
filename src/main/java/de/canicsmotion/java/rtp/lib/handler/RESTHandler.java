package de.canicsmotion.java.rtp.lib.handler;

import de.canicsmotion.java.rtp.lib.utils.Player;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class RESTHandler {
    private Client client = ClientBuilder.newClient();
    private String ip;
    private int port;

    public RESTHandler(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private void put(String targetPath, Object... objects) {
        WebTarget target = client.target(String.format("http://%s:%d/rest", ip, port));
        WebTarget resourceTarget = target.path(targetPath);
        Invocation.Builder request = resourceTarget.request(MediaType.APPLICATION_JSON);
        request.put(Entity.json(objects));
    }

    public void sendPlayers(List<Player> players) {
        put("players", players);
    }

    public void connect() {
        put("server/connected", true);
    }

    public void disconnect() {
        put("server/connected", false);
    }
}
