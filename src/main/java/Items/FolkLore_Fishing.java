package Items;


//This one doesnt have slot and hass additional info. Else everything else is the same
public class FolkLore_Fishing extends BaseItem{ //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info
    private String folkloreTome;
    private String time;

    public FolkLore_Fishing(String folkloreTome,
                            String time,
                            String item,
                            String location,
                            String cords,
                            String additionalInfo
    ){
        super(location,cords,item,additionalInfo);
        this.folkloreTome = folkloreTome;
        this.time = time;
    }

    /**
     * A customized toString method to normalize the first 3 rows in the file
     * @return Formatted Item
     */
    @Override
    public String toString() {
        //in the toString of itemOutputFormatter, it should autoload the following: itemName,location,cords,addedInfo. Figure out how 2 do this?
        return itemOutputFormatter.toString();
    }
//todo in toString part (how the item will be stored), makesure getExtra should return with the string Extra: ...

}
