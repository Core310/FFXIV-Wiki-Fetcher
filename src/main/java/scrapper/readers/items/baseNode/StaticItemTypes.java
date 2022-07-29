package scrapper.readers.items.baseNode;

/**
 * Triggers these item types when looking at a header. Delete is for cases that can be deleted
 * (such as the ending table).
 * Ignore is for data.
 */
public enum StaticItemTypes {
    FOLK_LORE_NODE,
    REGULAR_NODE,
    UNSPOILED_NODE,
    ARR_UNSPOILED_NODE, //For ARR variant of unspoiled nodes (does not include level)

    FISHING_NODE,
    FOLK_LORE_FISHING_NODE,
    BIG_FISH_NODE,
    FISHING_COLLECTABLES_NODE,
    DELETE,
    IGNORE; //Ignore is for Data
}
