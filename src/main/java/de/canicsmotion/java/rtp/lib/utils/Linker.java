package de.canicsmotion.java.rtp.lib.utils;

import de.canicsmotion.java.rtp.lib.handler.RESTHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Linker updates all players and sends them to the Backend of RTP
 */
public class Linker {
    private RESTHandler restHandler;
    private Timer updateTimer = new Timer();
    private UpdateTask updateTask;
    private List<Player> players = new ArrayList<>();
    private boolean connected = false;

    /**
     * Sets the ip and the port of the Backend
     *
     * @param ip   of the Backend
     * @param port of the Backend
     */
    public Linker(String ip, int port) {
        connect(ip, port);
    }

    /**
     * sets the task that gets executed on updating the players
     *
     * @param updateTask is the task that gets executed
     */
    public void setUpdateTask(UpdateTask updateTask) {
        this.updateTask = updateTask;
    }

    /**
     * Connects the Linker to the Backend
     *
     * @param ip   defines the ip of the Backend
     * @param port defines the port of the Backend
     */
    private void connect(String ip, int port) {
        restHandler = new RESTHandler(ip, port);
        restHandler.connect();
        connected = true;
    }

    /**
     * disconnects the Linker from the Backend
     */
    private void disconnect() {
        restHandler.disconnect();
        connected = false;
    }

    /**
     * starts the updating service
     *
     * @param updateRate defines in milliseconds how often an Update should be made
     */
    public void start(int updateRate) {
        if (updateTask != null && connected && updateRate > 10) {
            updateTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateTask.updatePlayers();
                    sendPlayersToBackend();
                }
            }, 0, updateRate);

        }
    }

    /**
     * Stops updating
     */
    public void stop() {
        updateTimer.cancel();
        disconnect();
    }

    /**
     * sends the locations of all players to the backend
     */
    private void sendPlayersToBackend() {
        restHandler.sendPlayers(players);
    }

    /**
     * Updates all improtant Informations about the player
     *
     * @param player is the player that gets updates
     */
    public void updatePlayer(Player player) {
        if (playerRegistered(player)) {
            Player p = players.get(indexOf(player));
            p.setPosition(player.getPosition());
            p.setDisplayName(player.getDisplayName());
        } else {
            player.register();
            players.add(player);
        }
    }

    /**
     * checks if player is registered or not
     *
     * @param player is the player that gets checked
     * @return true if the player is registered
     */
    private boolean playerRegistered(Player player) {
        for (Player p : players) {
            if (player.getGuid().equalsIgnoreCase(p.getGuid())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param player of index thats wanted
     * @return the index of the player in players-list
     */
    private int indexOf(Player player) {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            if (player.getGuid().equalsIgnoreCase(p.getGuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * removes a specific player
     *
     * @param player is the player that gets removed from players-list and the Backend
     */
    public void removePlayer(Player player) {
        players.remove(player);
        player.unRegister();
    }
}
