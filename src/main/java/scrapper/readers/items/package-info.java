/**
 * Used for both making file and reading files. See the baseNode package for the interface/abstract classes.
 * <p>Due to me being dumb, all classes do not actually need the toString override method. </p>
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
 *   alternate is for formatted. <br>
 *   An alternate constructor that has a string array as an argument for loading.</li>
 *   <li>An overridden LinkedHashMap method from the interface.</li>
 * </ul>
 *
 * @see scrapper.readers.items.Fish_Node example Item Class
 */
package scrapper.readers.items;