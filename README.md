# RoleplayTalkPlatform Java Library
## Overview
##### [Getting started](https://github.com/RoleplayTalkPlatform/Java-Library/tree/develop#getting-started-1)
##### [Others](https://github.com/RoleplayTalkPlatform/Java-Library/tree/develop#others-1)

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
    
In order to be able to update your player-positions, you have to implement an UpdateTask, witch defines, what will be executed at an update.
It will look like this:

    UpdateTask updateTask = () -> {
        for (Player player : getOnlinePlayers()) {
            linker.updatePlayer(parsePlayer(player));
        }
    }
    
Now you have to add the UpdateTask to the linker:
    
    linker.setUpdateTask(updateTask);
    
To start the linker and update all players frequently you should use:

    linker.start(20);
    
In this case you will provide all Player Data every 200 milliseconds. The minimum is 10 ms. 
> If your server is running very slow or you don't have enough RAM accessable, you can turn this number up, but recommendet is about 20 to 50 ms. Than higher this number is, then viewer updates will be done, so if you only have n update every 500ms, it will not sound authentic.

To stop the Linker again you have to use:
    
    linker.stop();
    
You should start the Linker on initialization of the server/plugin and stop it on Closing the server/plugin.

## Others

> If you want to provide us an own Implementation of this, in any other programming language, just contact us via de.linushoja@gmail.com, or open an issue. You can find a Documantation of the REST API and any other documentation about the project in the Repository [Documentation](https://github.com/RoleplayTalkPlatform/Documentation "Documentation Repository")