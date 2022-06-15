package Items;

public class FolkLore_Node extends FolkLore_Fishing{

    private int slot;
    public FolkLore_Node(String folkloreTome, String time, String item,
                         String location, String cords, String additionalInfo, int slot){
        super(folkloreTome,time,item,location,cords,additionalInfo);
        this.slot = slot;

    }

}
