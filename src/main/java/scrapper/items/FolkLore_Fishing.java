package scrapper.items;


import java.util.LinkedHashMap;

//This one doesn't have slot and hass additional info. Else everything else is the same
public class FolkLore_Fishing extends BaseItem implements Item{ //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info
    private final String folkloreTome;
    private final String time;

    public  FolkLore_Fishing(
            String item,
            String location,
            String cords,
            String additionalInfo,//Base Item stuff
            String folkloreTome,
            String time
    ){
        super(item,location,cords,additionalInfo);
        this.folkloreTome = folkloreTome;
        this.time = time;
    }

    /**
     * Used when file is already formatted.
     * @param arr array to input instead of manual input.
     */
    public FolkLore_Fishing(String[] arr){
        super(arr[1],arr[2],arr[3],arr[4]);
        folkloreTome = arr[5];
        time = arr[6];
    }

    protected String getFolkloreTome() {
        return folkloreTome;
    }

    protected String getTime() {
        return time;
    }
    /**
     *
     * @return Output format as follows after standard items: time,folkLore
     */
    @Override
    public String toString() {
        //in the toString of itemOutputFormatter, it should autoload the following: itemName,location,cords,addedInfo. Figure out how 2 do this?
        ItemOutputFormatter itemOutputFormatter = new ItemOutputFormatter(getItemName(),getZone(),getCords(),getExtra());
        itemOutputFormatter.addElements(new String[]{time,folkloreTome});
        return itemOutputFormatter.toString();
    }

    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    @Override
    public LinkedHashMap<String, String> toLinkedHashmap() {
        LinkedHashMap<String,String> lhm = new LinkedHashMap<>(BaseLinkedHashMap());
        lhm.put("FolkLore Tome",getFolkloreTome());
        lhm.put("Time",getTime());
        return lhm;
    }
}
