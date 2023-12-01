import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Tile extends Actor
{
    public static int SELECT_OFFSET = -16;
    //Constants    
    public static final int WIDTH = 128;
    public static final int HEIGHT = 64;
    public static final int MARGIN = 128;
    //direction of neighbors
    public static final int[][] directionDistances = {{64,32},{-64,-32},{-64,32},{64,-32}};
    public static final int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}};
    //object information
    protected int row;
    protected int col;
    protected LandPlot myPlot;
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    /**
     * adjust the transparancy of tiles based on thier distance from the center
     * 
     * NOTE: this will be changed to a rectangle; currently it fades based on a circle
     */
    public void adjust(GreenfootImage image){
        double distance = Math.sqrt((getX() - myPlot.getX()) * (getX() - myPlot.getX()) + (getY() - myPlot.getY()) * (getY() - myPlot.getY()));
        
        if(distance >= 300 && distance <= 400 ){
            double delta = 400 - distance;
            image.setTransparency((int)(255 * delta/100.0));
        }
        else if(distance > 400){
            image.setTransparency(0);
        }
        else {
            image.setTransparency(255);
        }
    }
    
    /**
     * handles all mouse input
     */
    public abstract void checkMouseAction();
    
    /**
     * handles all keyboard input
     */
    public abstract void checkKeypressAction();
    
    /**
     * Checks if the mouse is hovering exactly on the issometic square
     */
    public boolean hoveringThis(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        //math to make sure it is just in isometric square
        if(mouse != null){
            int yOffset = mouse.getY() - getY();
            int leftBound = getX() - WIDTH/2 + 2 * yOffset;
            int rightBound = getX() + WIDTH/2 - 2 * yOffset;
            if(yOffset > 0){
                leftBound = getX() - WIDTH/2 + 2 * yOffset;
                rightBound = getX() + WIDTH/2 - 2 * yOffset;
            }
            else{
                leftBound = getX() - WIDTH/2 - 2 * yOffset;
                rightBound = getX() + WIDTH/2 + 2 * yOffset;
            }

            int topBound = getY() - HEIGHT/2;
            int bottomBound = getY() + HEIGHT/2;
            return mouse.getX() > leftBound && mouse.getX() < rightBound && mouse.getY() < bottomBound && mouse.getY() > topBound;

            
        }
        return false;
    }
}
