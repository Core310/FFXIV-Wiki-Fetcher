package scrapper.readers.items.baseNode;

import java.util.LinkedHashMap;

/**
 * Used to enforce the method toLinkedHashmap
 * @see BaseItem
 */
public interface Item {
    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    LinkedHashMap<String, String> toLinkedHashmap();
}
