# RoleplayTalkPlatform Java Library

## Getting started
We highly recommend to create a method to parse the Player of your Game to a Player of this Library and the same for the position(Coordinate).

It could look like this:

    public de.canicsmotion.java.rtp.lib.utils.Player parsePlayer(some.game.Player player){
        de.canicsmotion.java.rtp.lib.utils.Player parsedPlayer;
        parsedPlayer = new de.canicsmotion.java.rtp.lib.utils.Player(
            player.getIP().toString(),
            player.getDisplayName(),
            player.getGuid()
        );
        parsedPlayer.setPosition(parsePosition(player.getPosition()));
        return parsedPlayer;
    }
    
    public de.canicsmotion.java.rtp.lib.utils.Coordinate parsePosition(some.game.Position position){
        de.canicsmotion.java.rtp.lib.utils.Coordinate parsedPosition;
        parsedPosition = new de.canicsmotion.java.rtp.lib.utils.Coordinate(
            position.getX(),
            position.getY(),
            position.getZ()
        );
        return parsedPosition;
    }
    
To connect to the Backend(Our Web-Service) you need to implement a Linker.

You can create a Linker like this:

    linker = new Linker(ip, port);
    linker.setUpdateTask(() -> {
        for (Player player : getOnlinePlayers()) {
            linker.updatePlayer(parsePlayer(player));
        }
    });
    
To start the linker and update all players frequently you should use:

    linker.start(200);
    
In this case you will provide all Player Data every 200 milliseconds. The minimum is 10 ms.

To stop the Linker again you have to use:
    
    linker.stop();
    
You should start the Linker on initialization of the server/plugin and stop ist on Closing the server/plugin.