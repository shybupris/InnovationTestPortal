/**
 * 
 *//*
package com.ITP.core.FileParsers;

import org.jdom.input.SAXBuilder;

*//**
 * @author shybu.sudhamani
 *
 *//*
public class xmlReader {

	
	public xmlReader(){
		
	}
	 private void xmlReader( String filename )
	    throws java.io.FileNotFoundException {
	        String filePath = PrefsDirectory.getLocation()+filename;
	        if( filePath.toUpperCase().indexOf(".XML") == -1 )
	            filePath = filePath + ".xml";
	        java.io.File file = new java.io.File( filePath );
	        try {
	            this.loadDoc(file);
	            this.getRootElement();
	        }
	        catch( Exception ex ) {
	            // @todo Can't chain a cause to FileNotFoundException
	            throw new java.io.FileNotFoundException("Configuration file not found: "+filePath + ex.toString());
	        }
	    }
	    *//** Creates and loads an XmlDoc from a String variable
	     * @param fXml File handle of a valid XML document
	     * @throws XmlDocException if the String is not valid XML
	     *//*
	    public void loadDoc( java.io.File fXml )  {
	        try {
	            // Get thread-local SAXBuilder for reading/parsing the file
	            SAXBuilder builder = SAXBuilder.get();
	            if( resolver != null )
	                builder.setEntityResolver(resolver);

	            // Create JDOM document object from SAXBuilder object using 
	            //      a String variable as the input source
	            org.jdom.Document jdomDoc = builder.build( fXml );

	            // Set the current document's content to the content loaded from the file
	            org.jdom.Element ele = jdomDoc.getRootElement();
	            ele.detach();
	            this.setRootElement(ele);
	            
	            // Remember that we need a reset
	            saxBuilderReset.set(false);
	        }
	        catch (Exception ex) {
	            //
	        }
}
*/