import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StubbyWheat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StubbyWheat extends Plant
{
    public static final int GROWTH_STAGES = 4;
    private ObjectID ID = ObjectID.STUBBY_WHEAT;
    /**
     * NEW: Variable deltaIndex moved to super class
     */
    
    public StubbyWheat(){
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

        if(lifeTime % 12 == 0 && growthStage > 0){
            nextFrame();
        }
    }

    public void initialize(){
        growthAnimations = new GreenfootImage[GROWTH_STAGES][3];
        deltaIndex = 1;
        growthStage = 0;
        maturity = 0;
        growthRate = 1;
        growthAnimations[0][0] = new GreenfootImage("Stubby Wheat Stage 0.png");
        for(int stage = 1; stage < GROWTH_STAGES; stage++){
            for(int frame = 0; frame < growthAnimations[stage].length; frame++){
                System.out.println(stage +" " + frame);
                growthAnimations[stage][frame] = new GreenfootImage("Stubby Wheat Stage " + stage + " " + frame + ".png");
            }

        }

        setImage(growthAnimations[growthStage][0]);
    }

    public void grow(){
        maturity += growthRate;
        if(maturity % 300 == 0 && growthStage < GROWTH_STAGES - 1){
            growthStage ++;
            //fade before setting
            fadeOval(growthAnimations[growthStage][0]);
            setImage(growthAnimations[growthStage][0]);
        }

        //when the growthStage is at max the crop is mature
        if(growthStage == GROWTH_STAGES - 1){
            mature = true;
        }

    }
    /**
     * NEW
     */
    public void nextFrame(){
        animationIndex += deltaIndex;
        if(animationIndex >= 0 && animationIndex < growthAnimations[growthStage].length){
            fadeOval(growthAnimations[growthStage][animationIndex]);
            setImage(growthAnimations[growthStage][animationIndex]);
        }
        else{
            deltaIndex *= -1;
            animationIndex += 2 * deltaIndex;
            fadeOval(growthAnimations[growthStage][animationIndex]);
            setImage(growthAnimations[growthStage][animationIndex]);
        }
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
        /**
         * NEW
         */
        if(mature && hoveringThis()  && Greenfoot.mouseClicked(null)  && Cursor.getActor() == null && Cursor.getButton() == 1){
            collect();
        }
    }

    public void playPlaceSound(){

    }

    public void playRemoveSound(){

    }
}
