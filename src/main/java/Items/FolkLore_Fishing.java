package Items;


//This one doesnt have slot and hass additional info. Else everything else is the same
public class FolkLore_Fishing extends BaseItem{ //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info
    private final String folkloreTome;
    private final String time;

    public FolkLore_Fishing(
            String item,
            String location,
            String cords,
            String additionalInfo,//Base Item stuff

            String time,
            String folkloreTome
    ){
        super(location,cords,item,additionalInfo);
        this.time = time;
        this.folkloreTome = folkloreTome;
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
        itemOutputFormatter.addElement(time);
        itemOutputFormatter.addElement(folkloreTome);
        return itemOutputFormatter.toString();
    }
//todo in toString part (how the item will be stored), makesure getExtra should return with the string Extra: ...

}
