package scrapper;

/**
 * Triggers these item types when looking at a header. Delete is for cases that can be deleted
 * (such as the ending table).
 * Ignore is for data.
 */
public enum StaticItemTypes {
    FolkLoreFishing,
    FolkLoreNode,
    RegularNode,
    UnspoiledNode,
    ARRUnspoiledNode, //For ARR variant of unspoiled nodes (does not include level)
    Delete,
    Ignore //Ignore is for Data

}
