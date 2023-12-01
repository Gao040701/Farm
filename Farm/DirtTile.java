import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DirtTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class DirtTile extends Tile
{
    
    //active/projected toggle
    private boolean active;
    private GreenfootImage projectedTile;
    private GreenfootImage activeTile;

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
        adjust(activeTile);
    }

    public void act()
    {
        checkMouseAction();
        checkKeypressAction();
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
        //initialize projected to be invisable
        projectedTile = new GreenfootImage("Projected Tile.png");
        projectedTile.setTransparency(0);
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

    /**
     * used to handle all mouse inputs
     */
    public void checkMouseAction(){
        //this is for inactive tiles to become real tiles
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            //will set the projectedTiles transparancy 
            if (activeTile.getTransparency() >50 && getAffordable() && editMode &&hoveringThis()){
                projectedTile.setTransparency(255);
            }
            else{
                projectedTile.setTransparency(0);
            }
            if (activeTile.getTransparency() >50 && !active && (clickedThis() || (hoveringThis() && Greenfoot.mouseDragged(null))) && mouse.getButton() == 1) {
                setImage(activeTile);
                projectTiles();
                activate();
            }
            //offsets the tile when clicked to show selection
            clickOffset(mouse);
            //adjusts transparancy of the active tile
            adjust(activeTile);

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
            int dcol = direction[0];
            int drow = direction[1];

            DirtTile neighbour = myPlot.getTile(row + drow, col + dcol);
            if(neighbour != null && !neighbour.isActive()){
                getWorld().removeObject(neighbour);
            }
        }
    }

    //checks if the mouse is hovering and there has been a click
    public boolean clickedThis(){
        return hoveringThis() && Greenfoot.mouseClicked(null);
    }
    
    //does some animation when a tile is clicked this is temporary
    //mouse info does nothinng in this code block right now
    public void clickOffset(MouseInfo mouse){
        if(activeTile.getTransparency() >50 && clickedThis() && active && !offseted){
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
