package Items;


//This one doesnt have slot and hass additional info. Else everything else is the same
public class FolkLore_Fishing extends BaseItem{ //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info
    private String folkloreTome;
    private String time;

    public FolkLore_Fishing(String folkloreTome, String time, String item,
                            String location, String cords, String additionalInfo){
        super(location,cords,item,additionalInfo);
        this.folkloreTome = folkloreTome;
        this.time = time;
    }

    //todo in toString part (how the item will be stored), makesure getExtra should return with the string Extra: ...

}
