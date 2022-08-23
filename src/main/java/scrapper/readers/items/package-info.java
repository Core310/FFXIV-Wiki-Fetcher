/**
 * Used for both making file and reading files.
 * <p>Houses all the types of items that will be read and written into the final file. All item classes have a suffix of '_Node'.</p>
 * Includes custom readers for these classes, and ENUM giving each item type.
 * <p>Should include all item types encountered, eg. Unspoiled Nodes (here: https://ffxiv.consolegameswiki.com/wiki/Unspoiled_Nodes)</p>
 * has a class of its own. The arguments should be all the table headers.
 *
 *
 * <br>
 * Items generally follow in the same manner:
 * <ul>
 *   <li>They extend BaseItem, and  implement Item</li>
 *   <li>Have a base and alternate constructor. Base is for formatted items (raw data) and
 *   alternate is for formatted. <br> Alternate constructor has a string array as an argument.</li>
 *   <li>A overridden toString and LinkedHashMap method from their respective classes</li>
 * </ul>
 *
 * @see scrapper.readers.items.Fish_Node example Item Class
 */
package scrapper.readers.items;