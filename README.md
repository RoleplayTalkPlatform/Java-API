# RoleplayTalkPlatform Java Library

## Getting started
We highly recommend to create a method to parse the Player of your Game to a Player of this API and the same for the position(Coordinate).

It could look like this:

    public de.canicsmotion.java.rtp.api.utils.Player parsePlayer(some.game.Player player){
        de.canicsmotion.java.rtp.api.utils.Player parsedPlayer;
        parsedPlayer = new de.canicsmotion.java.rtp.api.utils.Player(
            player.getIP().toString(),
            player.getDisplayName()
        );
        parsedPlayer.setPosition(parsePosition(player.getPosition()));
        retrun parsedPlayer;
    }
    
    public de.canicsmotion.java.rtp.api.utils.Coordinate parsePosition(some.game.Position position){
        de.canicsmotion.java.rtp.api.utils.Coordinate parsedPosition;
        parsedPosition = new de.canicsmotion.java.rtp.api.utils.Coordinate(
            position.getX(),
            position.getY(),
            position.getZ()
        );
        return parsedPosition;
    }