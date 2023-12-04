import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super Class for all plants that can be placed on dirtTiles
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Plant extends Tile
{

    protected int growthRate;
    protected int maturity; 
    protected int growthStage;
    protected int lifeTime;
    protected boolean mature;    
    //[Growth Stage][Animation Frame];
    protected GreenfootImage[][] growthAnimations;
    protected int animationIndex;
    protected DirtTile myTile;

    public Plant(){
        myTile = null;
        myPlot = null;
        lifeTime = 0;
        growthStage = 0;
        maturity = 0;
        mature = false;
        //nextFrame would be 0
        animationIndex = -1;
    }

    public void act()
    {
        super.act();
        
        if(myTile != null){
            setLocation(myTile.getX(), myTile.getY());
        }
        
        //fade
        if(growthStage > 0){
            fadeOval(getImage());
        }
        
        
        lifeTime++;
    }

    public abstract void grow();

    public void plant(LandPlot plot, DirtTile tile){
        growthStage++;
        myPlot = plot;
        myTile = tile;
        Cursor.release();
    }
    //animate
    //NOTE: probably doesn't work as desired please change to your needs
    public void nextFrame(){
        animationIndex++;
        if(animationIndex > growthAnimations[growthStage].length){
            setImage(growthAnimations[growthStage][animationIndex]);
        }
        else{
            animationIndex = 0;
            setImage(growthAnimations[growthStage][animationIndex]);
        }
    }

    //getters and setters
    public int getGrowthRate(){
        return growthRate;
    }

    public void setGrowthRate(int growthRate){
        this.growthRate = growthRate;
    }
}
