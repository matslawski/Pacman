
package org.projektpwr.pacman;

public class Player {
    public enum Position {
        UP, DOWN, LEFT, RIGHT
    }
    
    public static Position Reverse(Position pos) {
    	if(pos == Position.UP) return Position.DOWN;
    	else if(pos == Position.DOWN) return Position.UP;
    	else if(pos == Position.LEFT) return Position.RIGHT;
    	else return Position.LEFT;
    }

    public enum PlayerType {
        PACMAN, GHOST
    }
    
    public enum GhostType {
    	BLINKY, PINKY, CLYDE
    }

    public enum PlayerStatus {
        NORMAL, POWERED_UP, SCARED
    }
    
    private PlayerType playerType;
    private GhostType ghostType;
    private PlayerStatus playerStatus;
    private int currentRow;
    private int currentColumn;
    private Position position;
    private int numDotsEaten;
    private boolean dead;

    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getNumDotsEaten() {
        return numDotsEaten;
    }

    public void setNumDotsEaten(int numDotsEaten) {
        this.numDotsEaten = numDotsEaten;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
    
    public GhostType getGhostType() {
        return ghostType;
    }

    public void setGhostType(GhostType ghostType) {
        this.ghostType = ghostType;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
