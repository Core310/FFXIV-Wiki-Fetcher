package Items;


//This one doesnt have slot and hass additional info. Else everything else is the same
public class FolkLoreFishing implements Item{ //FolkLore_Toem, Time, Item, Slot, Location, Cords, Added info
    String folkloreTome;
    String zone;
    String cords;
    String extra;
    String name;
    String time;

    public FolkLoreFishing(String folkloreTome,String time,String item,
                           String location, String cords, String additionalInfo){
        this.folkloreTome = folkloreTome;
        this.time = time;
        this.name = item;
        zone = location;
        this.cords = cords;
        extra = additionalInfo;
    }

    //todo in toString part (how the item will be stored), makesure getExtra should return with the string Extra: ...
    public String getFolklore_Tome() {
        return folkloreTome;
    }

    public String getTime() {
        return time;
    }
    @Override
    public String getItemName() {
        return null;
    }

    @Override
    public String getZone() {
        return null;
    }

    @Override
    public String getCords() {
        return null;
    }

    @Override
    public String getExtra() {
        return null;
    }
}
