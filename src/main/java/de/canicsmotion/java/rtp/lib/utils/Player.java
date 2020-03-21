package de.canicsmotion.java.rtp.lib.utils;

/**
 * The Player-Object contains all the player-data.
 * it contains
 * - the position of the player
 * - the ip of the player, to authenticate if the player is in the Chat
 * - the displayName, witch will be displayed in the Live-chat
 *   It also should be the in-game displayName to prevent any confusion
 */
public class Player {
    private Coordinate position;
    private String ip;
    private String displayName;

    /**
     * @param ip is the players ip, to track him in the live-chat
     * @param displayName is the live-chat display name
     */
    public Player(String ip, String displayName) {
        this.ip = ip;
        this.displayName = displayName;
    }

    /**
     * @return the Position of the player
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * @param position of the player
     */
    public void setPosition(Coordinate position) {
        this.position = position;
    }

    /**
     * @return ip of the player
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip of the player
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the dislpayName of the player, witch is displayed in the livechat
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName witch should be displayed in the liveChat
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
