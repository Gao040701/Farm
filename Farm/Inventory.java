import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class will store all the items that have been attained by the user
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory
{
    private static HashMap <ObjectID, ArrayList<Actor>> inventory;
    public void act()
    {
        // Add your action code here.
    }
    
    public static void initialize(String saveFile){
        inventory = new HashMap<>();
        loadGame(saveFile);
    }
    
    public static void loadGame(String saveFile){
        //will read from game save file
        resetData();
    }
    
    public static void resetData(){
        inventory.clear();
    }
    
    public static int getObjectCount(ObjectID id){
        return inventory.get(id).size();
    }
        
    public static ArrayList<Actor> getObjects(ObjectID id){
        if(!inventory.containsKey(id)){
            return null;
        }
        return inventory.get(id);
    }
    
    //removes object from list
    public static Actor retrieveOneObject(ObjectID id){
        if(!inventory.containsKey(id)){
            return null;
        }
        Actor retrieved = inventory.get(id).remove(0);
        if(getObjectCount(id) == 0){
            inventory.remove(id);
        }
        
        return retrieved;
    }
    
    public static void addObject(ObjectID id, Actor actor){
        if(!inventory.containsKey(id)){
            ArrayList <Actor> actorList = new ArrayList <>();
            actorList.add(actor);
            inventory.put(id,actorList);
        }
        else{
            ArrayList <Actor> actorList = inventory.get(id);
            actorList.add(actor);
        }
    }
    
}
