/**
 * Used to build a new data file.
 * <p>Contains everything needed to scrap data from the wiki and format it.</p>
 * <li>MakeFile to grab and store the raw data</li>
 * <li>ItemBuilder to create each item for Formatter.java</li>
 * <li>Formatter to format the entire file.</li>
 * <li>Wikiapges to set the pages to scrap</li>
 * When adding a new item, add the link into Wikipages, then the itemclass into readers.items, then into ItemBuilder and Formatter.
 */
package fileBuilder.fileCreator;
