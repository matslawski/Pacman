
package org.projektpwr.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class MazeBuilder {
    private static final int START_X = 4;
    private static final int START_Y = 50;
    private int blockHeight = 0;
    private int blockWidth = 0;
    private static final int DOT_HEIGHT = 3;
    private static final int DOT_WIDTH = 3;
    private static int NUM_X_BLOCK = 17;
    private static int NUM_Y_BLOCK = 23;
    private Graphics graphics;
    private Image pacManWholeImage;
    private Image pacManRightImage;
    private Image pacManLeftImage;
    private Image pacManUpImage;
    private Image pacManDownImage;
    private Image blinkyGhost;
    private Image pinkyGhost;
    private Image clydeGhost;
    private Player pacMan;
    private List<Player> ghosts = new ArrayList<Player>();

    public MazeBuilder(int width, int height, Player pacMan, List<Player> ghosts) {
        this.blockWidth = Math.abs(width / NUM_X_BLOCK);
        this.blockHeight = Math.abs(height / NUM_Y_BLOCK - 1);
        this.pacMan = pacMan;
        this.ghosts = ghosts;

        try {
            if (pacManWholeImage == null) {
                pacManWholeImage = ImageIO.read(getClass().getResource(
                    "/res/PacMan1.gif"));
                pacManRightImage = ImageIO.read(getClass().getResource(
                    "/res/PacMan3right.gif"));
                pacManLeftImage = ImageIO.read(getClass().getResource(
                    "/res/PacMan3left.gif"));
                pacManUpImage = ImageIO
                    .read(getClass().getResource("/res/PacMan3up.gif"));
                pacManDownImage = ImageIO.read(getClass().getResource(
                    "/res/PacMan3down.gif"));
                blinkyGhost = ImageIO.read(getClass().getResource("/res/Blinky.gif"));
                pinkyGhost = ImageIO.read(getClass().getResource("/res/Pinky.gif"));
                clydeGhost = ImageIO.read(getClass().getResource("/res/Clyde.gif"));
               
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void draw(NodeType[][] maze) {
        int y = START_Y;
        for (int i = 0; i < maze.length; i++) {
            int x = START_X;
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == NodeType.WALL) {
                    getGraphics().setColor(Color.BLUE);
                    getGraphics().fillRect(x, y, blockWidth, blockHeight);
                } else if (maze[i][j] == NodeType.DOT) {
                    getGraphics().setColor(Color.BLACK);
                    getGraphics().fillRect(x, y, blockWidth, blockHeight);
                    getGraphics().setColor(Color.YELLOW);
                    getGraphics().fillRect(x + (blockWidth / 2), y + (blockHeight / 2),
                        DOT_WIDTH, DOT_HEIGHT);
                } else if (maze[i][j] == NodeType.PACMAN) {
                    getGraphics().setColor(Color.BLACK);
                    getGraphics().fillRect(x, y, blockWidth, blockHeight);
                    getGraphics().setColor(Color.YELLOW);
                    animate(getGraphics(), pacMan, x, y);
                } else if (maze[i][j] == NodeType.GHOST) {
                    getGraphics().setColor(Color.BLACK);
                    getGraphics().fillRect(x, y, blockWidth, blockHeight);
                    getGraphics().setColor(Color.YELLOW);
                    Player ghost = GetGhost(i,j);
                    animate(getGraphics(), ghost, x, y);
                } else if (maze[i][j] == NodeType.BLANK) {
                    getGraphics().setColor(Color.BLACK);
                    getGraphics().fillRect(x, y, blockWidth, blockHeight);
                }
                x += blockWidth;
            }
            y += blockHeight;
        }
    }
    
    public Player GetGhost(int inputRow, int inputColumn)
    {
    	for(Player ghost : ghosts)
    	{
    		int row = ghost.getCurrentRow();
    		int column = ghost.getCurrentColumn();
    		if(row == inputRow)
    		{
    			if(column == inputColumn)
    			{
    				return ghost;
    			}
    		}
    	}
    	return null;
    }

    private void animate(Graphics graphics, Player player, int x, int y) {
        Image image = null;
        if (player.getPlayerType() == Player.PlayerType.PACMAN) {
            if (player.getPosition() == Player.Position.LEFT) {
                image = pacManLeftImage;
            } else if (player.getPosition() == Player.Position.RIGHT) {
                image = pacManRightImage;
            } else if (player.getPosition() == Player.Position.UP) {
                image = pacManUpImage;
            } else if (player.getPosition() == Player.Position.DOWN) {
                image = pacManDownImage;
            }
        } else if (player.getPlayerType() == Player.PlayerType.GHOST) {
        	if(player.getGhostType() == Player.GhostType.PINKY)
        		{ image = pinkyGhost; }
        	else if(player.getGhostType() == Player.GhostType.CLYDE)
        		{ image = clydeGhost; }
        	else
        		{ image = blinkyGhost; }
        }

        if (player.getPosition() == Player.Position.RIGHT) {
            graphics.drawImage(image, x, y, blockWidth, blockHeight, null);
        } else if (player.getPosition() == Player.Position.LEFT) {
            graphics.drawImage(image, x, y, blockWidth, blockHeight, null);
        } else if (player.getPosition() == Player.Position.DOWN) {
            graphics.drawImage(image, x, y, blockWidth, blockHeight, null);
        } else if (player.getPosition() == Player.Position.UP) {
            graphics.drawImage(image, x, y, blockWidth, blockHeight, null);
        }
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
    
    public static void setNumBlock(int x, int y) {
    	NUM_X_BLOCK = x;
    	NUM_Y_BLOCK = y;
    }
}
