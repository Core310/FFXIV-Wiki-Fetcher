package Items;

import java.util.ArrayList;
/**
 * Used for toString of the ITEMs in this module.
 * All methods/vars are either private or protected.
 */
public class ItemOutputFormatter {
    private ArrayList<String> arrayList = new ArrayList<>();
    private String delimiter = "\t";

    /**
     * Used for toString of the ITEMs in this module.
     */
    protected ItemOutputFormatter(){

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
            stringBuilder.append(delimiter);//Appends whatever is the desired delimiter todo this is bad pratice fixme
        }
        return stringBuilder.toString();
    }

    //Append items to a custom array. Output by TSV using a for-loop
    //Use a method to set the delim


}
