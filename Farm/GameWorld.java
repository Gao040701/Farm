import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    //720p resolution 16:9 aspect ratio
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld(String saveFile)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1, false); 
        initialize(saveFile);
    }
    
    public void act(){
        
    }
    public void initialize(String saveFile){
        //add objects
        setPaintOrder(Plant.class, DirtTile.class, LandPlot.class);
        
        addObject(new LandPlot(), SCREEN_WIDTH/2, SCREEN_HEIGHT/2);
        
        //sets up the enventory from previous save
        Inventory.initialize(saveFile);
        
        for(int i = 0; i < 100; i++){
            addObject(new Wheat(), 1200, 650);
        }
    }
}
