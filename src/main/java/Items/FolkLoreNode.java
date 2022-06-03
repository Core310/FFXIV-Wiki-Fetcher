package Items;

public class FolkLoreNode extends FolkLoreFishing implements Item{

    private int slot;
    public FolkLoreNode(String folkloreTome,String time,String item,
                        String location, String cords, String additionalInfo, int slot){
        super(folkloreTome,time,item,location,cords,additionalInfo);
        this.slot = slot;

    }







    //getters below

    public int getSlot() {
        return slot;
    }
}
