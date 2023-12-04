import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DirtTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class DirtTile extends Tile
{
    public static final int DEFAULT_GROWTH_MULTIPLIER = 1;
    
    private ObjectID ID = ObjectID.DIRTILE;
    //active/projected toggle
    private boolean active;
    private GreenfootImage projectedTile;
    private GreenfootImage activeTile;

    private Plant plant;
    private double growthMultiplier;

    //edit mode toggle
    private boolean editMode;
    private boolean offseted;
    /**
     * Constructor
     * 
     * @param active state of tile
     * @param row
     * @param col
     */
    public DirtTile(LandPlot plot, int row, int col, boolean active){
        Initialize(plot, row, col, active);
    }

    public void addedToWorld(World w){
        if(active){
            projectTiles();
        }
        //adjust transparancy
        fadeOval(activeTile);
    }

    public void act()
    {
        super.act();
        if(active && editMode){
            projectTiles();
        }
        else if(active && !editMode){
            stopProjection();
        }
        fadeOval(activeTile);
    }

    /**
     * Initializes the tile
     * 
     * @param active state of tile
     */
    public void Initialize(LandPlot plot, int row, int col, boolean active){
        this.active = active;
        this.row = row;
        this.col = col;
        myPlot = plot;
        growthMultiplier = DEFAULT_GROWTH_MULTIPLIER;
        //initialize projected to be invisable
        projectedTile = new GreenfootImage("Projected Tile.png");
        projectedTile.setTransparency(TRANSPARENT);
        activeTile  = new GreenfootImage("Basic Dirt.png");

        /**
         * PLEASE REMOVE EDITMODE = TRUE LATOR
         */
        editMode = true;

        if(active){
            setImage(activeTile);
        }
        else{
            setImage(projectedTile);
        }     
    }
    
    public void unPlant(){
        plant = null;
    }
    
    /**
     * used to handle all mouse inputs
     */
    public void checkMouseAction(){
        //this is for inactive tiles to become real tiles
        MouseInfo mouse = Cursor.getMouseInfo();
        if(mouse != null){
            //will set the projectedTiles transparancy 
            if (activeTile.getTransparency() >TRANSLUCENT && getAffordable() && editMode &&hoveringThis()){
                projectedTile.setTransparency(OPAQUE);
            }
            else{
                projectedTile.setTransparency(TRANSPARENT);
            }
            
            //activate tile when clicked
            //NOTE: can remove Cursor.getActor() == null after editmode is implemented
            if (Cursor.getActor() == null && activeTile.getTransparency() >TRANSLUCENT && !active && (clickedThis() || (hoveringThis() && Greenfoot.mouseDragged(null))) && mouse.getButton() == 1) {
                setImage(activeTile);
                projectTiles();
                activate();
                playPlaceSound();
            }

        }
        
        
        //check for plant on cursor
        Actor actor = Cursor.getActor();
        if(active && plant == null && activeTile.getTransparency() >TRANSLUCENT && Greenfoot.mouseDragEnded(null) && hoveringThis() && actor != null && actor instanceof Plant){
            plant = (Plant) Cursor.getActor();
            plant.plant(myPlot, this);
        }
    }
    
    /**
     * used to handle all keypress Actions
     */
    public void checkKeypressAction(){
        //does nothing right now
        //needs to be filled in
    }

    /**
     * projects possible tiles
     */
    public void projectTiles(){
        //loops through each direction
        for(int i = 0; i < directions.length; i++){
            //dcol, drow is where the next tile is located in the plot
            int dcol = directions[i][0];
            int drow = directions[i][1];
            //dx, dy is where the next tile will be
            int dx = directionDistances[i][0];
            int dy = directionDistances[i][1];
            DirtTile neighbour = myPlot.createTile(row + drow, col + dcol);
            if(neighbour != null){
                getWorld().addObject(neighbour, getX() + dx, getY() + dy);
            }
        }
    }

    public void stopProjection(){
        for(int[] direction : directions){
            //dcol = dx, drow = dy
            int deltaCol = direction[0];
            int deltaRow = direction[1];

            DirtTile neighbour = myPlot.getTile(row + deltaRow, col + deltaCol);
            if(neighbour != null && !neighbour.isActive()){
                getWorld().removeObject(neighbour);
                myPlot.removeFromPlot(row + deltaRow, col + deltaCol);
            }
        }
    }

    //checks if the mouse is hovering and there has been a click
    public boolean clickedThis(){
        return hoveringThis() && Greenfoot.mouseClicked(null);
    }

    //does some animation when a tile is clicked this is temporary
    //mouse info does nothinng in this code block right now
    public void clickOffset(){
        if(activeTile.getTransparency() >TRANSLUCENT && clickedThis() && active && !offseted){
            setLocation(getX(), getY() + SELECT_OFFSET);
            offseted = true;
        }    
        else if(offseted && !hoveringThis()){
            setLocation(getX(), getY() - SELECT_OFFSET);
            offseted = false;
        }
    }

    //see if it is possible for the player to place the tile does not have to be related to price.
    //this method name is temporary
    public boolean getAffordable(){
        //needs filling in
        return true;
    }
    //returns the current editing mode
    public boolean getEditMode(){
        return editMode;
    }
    //sets edit mode
    public void setEditMode(boolean mode){
        editMode = mode;
    }
    //changes edit mode from true to false and vice verca
    public void toggleEditMode(){
        editMode ^= true;
    }

    public void playPlaceSound(){
        //start the place sound here
    }

    public void playRemoveSound(){
        //start the remove sound here
    }

    /**
     * returns the state of the tile
     */
    public boolean isActive(){
        return active;
    }

    public void activate(){
        active = true;
    }

}
