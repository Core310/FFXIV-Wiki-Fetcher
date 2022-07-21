package scrapper;

/**
 * Contains all links that are to be searched.
 */
public enum Wikipages {
    BNT_REGULAR_NODE("https://ffxiv.consolegameswiki.com/wiki/Botanist_Node_Locations"),
    BNT_UNSPOILED_NODE("https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Botanist_Nodes"),
    MNR_REGULAR_NODE("https://ffxiv.consolegameswiki.com/wiki/Mining_Node_Locations"),
    MNR_UNSPOILED_NODE("https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Mining_Nodes"),
    FOLK_LORE_NODE("https://ffxiv.consolegameswiki.com/wiki/Folklore_Nodes");
    //EphemeralNode("https://ffxiv.consolegameswiki.com/wiki/Ephemeral_Nodes") // TODO: 2/2/2022 Emph nodes need a reader/parser


    private final String url;

    /**
     * Default constructor, this should not be used. Outside of this class
     * This is needed to have a link attached to each enum declaration.
     * @param string String to assign to Enum values
     */
    Wikipages(String string) {
        url = string;
    }

    /**
     *
     * @return Enum Value (in this case the URL)
     */
    @Override
    public String toString() {
        return url;
    }
}