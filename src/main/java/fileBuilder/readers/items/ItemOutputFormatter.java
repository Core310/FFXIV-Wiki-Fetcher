package fileBuilder.readers.items;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Used for toString of the ITEMs in this module.
 * All methods/vars are either private or protected.
 * The constructor should never be called.
 */
public class ItemOutputFormatter {
    private final Queue<String> queue = new LinkedList<>();
    private final String delimiter = "\t";

    /**
     * Params are for normalization and declutter. All params are shared by every item and will appear FIRST in their listing.
     */
    protected ItemOutputFormatter(String itemName, String zone, String cords, String extra) {
        String[] str = {itemName, zone, cords, extra};
        queue.addAll(List.of(str));
    }

    /**
     * @param element adds an array of elements to a private queue.
     */
    protected void addElements(String[] element) {
        queue.addAll(Arrays.asList(element));
    }


    /**
     * Takes all inputted values, separates them by the default delimiter (in this case \t) and outputs it in a string
     *
     * @return inputted values seperated by delim
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
            stringBuilder.append(delimiter);//Appends whatever is the desired delimiter
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
    //Append items to a custom array. Output by TSV using a for-loop
    //Use a method to set the delim


}
