package Scrapper;

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
    Delete,
    Ignore; //Ignore is for Data

}
