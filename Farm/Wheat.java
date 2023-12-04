import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wheat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wheat extends Plant
{
    public static final int GROWTH_STAGES = 5;
    private ObjectID ID = ObjectID.WHEAT;
    public Wheat(){
        super();
        initialize();
        
    }
    
    public void act()
    {
        super.act();
        //is a seedling
        if(myTile == null){
            setImage(growthAnimations[0][0]);
        }
        else{
            grow();
        }
        
    }
    
    public void initialize(){
        growthAnimations = new GreenfootImage[GROWTH_STAGES][7];
        growthStage = 0;
        maturity = 0;
        growthRate = 1;
        for(int i = 0; i < GROWTH_STAGES; i++){
            growthAnimations[i][0] = new GreenfootImage("Wheat Stage " + i + ".png");
        }
        
        setImage(growthAnimations[growthStage][0]);
        for (int i = 0; i < 7; i++){
            growthAnimations[1][i] = new GreenfootImage("growth0/growth0_" + i + ".png");
        }
    }
    public void grow(){
        maturity += growthRate;
        if(maturity % 300 == 0 && growthStage < GROWTH_STAGES - 1){
            growthStage ++;
        }
        
        //when the growthStage is at max the crop is mature
        if(growthStage == GROWTH_STAGES - 1){
            mature = true;
        }
        
        //fade before setting
        fadeOval(growthAnimations[growthStage][0]);
        setImage(growthAnimations[growthStage][0]);
    }
    
    public void collect(){
        myTile.unPlant();
        getWorld().removeObject(this);
    }
    
    public void checkKeypressAction(){
        
    }
    
    public void checkMouseAction(){
        //planting if not planted
        if(myTile == null && Cursor.getActor() == null && hoveringThis() && Greenfoot.mouseDragged(null)){
            Cursor.pickUp(this);
        }
        
        if(Cursor.getActor() == this){
            setLocation(Cursor.getX(), Cursor.getY());
            if(Greenfoot.mouseDragEnded(null) && myTile == null){
                Cursor.release();
            }
        }
        
        if(mature && hoveringThis() && Greenfoot.mouseClicked(null) && Cursor.getButton() == 1){
            collect();
        }
    }
    public void playPlaceSound(){
        
    }
    public void playRemoveSound(){
        
    }
}
