/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.org.inegi.geo.map.xml.model;

import java.io.File;

/**
 *
 * @author yan.luevano
 */
public class Document {

    private String fileName;
    private File file;

    public Document(String fileName) {
        this.fileName = fileName;
    }

    public void setFile(File file){
        this.file = file;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }



}
