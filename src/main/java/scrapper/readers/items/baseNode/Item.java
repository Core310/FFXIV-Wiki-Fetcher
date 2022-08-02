package scrapper.readers.items.baseNode;

import java.util.LinkedHashMap;

/**
 * Used to enforce the method toLinkedHashmap. Should be implemented by every item (denoted by their suffix of '_Node'
 * @see BaseItem
 */
public interface Item {
    /**
     * @return ItemType in LinkedHashmap form. Keys represent what the value is. For example, key = level, value = 5.
     */
    LinkedHashMap<String, String> toLinkedHashmap();
}
