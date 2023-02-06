package scrapper.readers.items.baseNode;

/**
 * Triggers these item types when looking at a header. Delete is for cases that can be deleted
 * (such as the ending table).
 * Ignore is for data.
 *
 * @see scrapper.fileCreator.Formatter
 * @see scrapper.fileCreator.ItemBuilder
 */
public enum StaticItemTypes {
    FOLK_LORE_NODE,
    REGULAR_NODE,
    UNSPOILED_NODE,
    ARR_UNSPOILED_NODE, //For ARR variant of unspoiled nodes (does not include level)

    FISH_NODE,
    FOLK_LORE_FISH_NODE,
    FISH_BIG_NODE,
    FISH_COLLECTABLES_NODE,
    EPHEMERAL_FISH_NODE,
    EPHEMERAL_NODE,
    SKIP,//For epmh nodes, Reduction Tables
    DELETE,//For headers
    IGNORE //For Data
}
