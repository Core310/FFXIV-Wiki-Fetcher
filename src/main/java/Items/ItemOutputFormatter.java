package Items;

import java.util.ArrayList;
/**
 * Used for toString of the ITEMs in this module.
 * All methods/vars are either private or protected.
 */
public class ItemOutputFormatter {
    private final ArrayList<String> arrayList = new ArrayList<>();
    private final String delimiter = "\t";

    /**
     * Params are for normalization and declutter. All params are shared by every item and will appear FIRST in their listing.
     */
    protected ItemOutputFormatter(String itemName, String zone, String cords,String extra){
        arrayList.add(itemName);
        arrayList.add(zone);
        arrayList.add(cords);
        arrayList.add(extra);
    }

    protected void addElement(String element){
        arrayList.add(element);
    }


    /**
     * Takes all inputted values, separates them by the default delimiter (in this case \t) and outputs it in a string
     * @return inputted values seperated by delim
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String element:
             arrayList) {
            stringBuilder.append(element);
            stringBuilder.append(delimiter);//Appends whatever is the desired delimiter
        }
        return stringBuilder.toString();
    }

    //Append items to a custom array. Output by TSV using a for-loop
    //Use a method to set the delim


}
