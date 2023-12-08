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
    /**
     * NEW: Variable yeild
     */
    protected int yeild;
    
    //[Growth Stage][Animation Frame];
    protected GreenfootImage[][] growthAnimations;
    protected int animationIndex;
    protected int deltaIndex;
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
            lifeTime++;
        }
        
        
        
    }

    public abstract void grow();

    public void plant(LandPlot plot, DirtTile tile){
        growthStage++;
        myPlot = plot;
        myTile = tile;
        Cursor.release();
    }
    
    public abstract void nextFrame();
    
    /**
     * NEW: Collect() method is now abstract
     */
    public abstract void collect();
    //getters and setters
    public int getGrowthRate(){
        return growthRate;
    }

    public void setGrowthRate(int growthRate){
        this.growthRate = growthRate;
    }
    /**
     * NEW: All getters and setters bellow this comment
     */
    public int getMaturity(){
        return maturity;
    }
    public void setMaturity(int maturity){
        this.maturity = maturity;
    }
    public int getGrowthStage(){
        return growthStage;
    }
    public void setGrowthStage(int growthStage){
        this.growthStage = growthStage;
    }
    public int getLifeTime(){
        return lifeTime;
    }
    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }
    public boolean isMature(){
        return mature;
    }
    public void setMature(boolean mature){
        this.mature = mature;
    }
    public int getYield(){
        return yeild;
    }
    public void setYield(int yeild){
        this.yeild = yeild;
    }
    public DirtTile getDirtTile(){
        return myTile;
    }
    public void setDirtTile(DirtTile newTile){
        myTile = newTile;
    }
}
