package scrapper.items;


import java.util.LinkedHashMap;

//This one doesnt have slot and hass additional info. Else everything else is the same
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
        super(arr[0],arr[1],arr[2],arr[3]);
        folkloreTome = arr[4];
        time = arr[5];
    }// TODO: 7/24/2022 Test me, not sure if works

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
        itemOutputFormatter.addElement(time);
        itemOutputFormatter.addElement(folkloreTome);
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
