/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.inegi.geo.map.xml.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Aaron.Villar
 */
public class Tables {
    private List<Table> tables = new LinkedList<Table>();
    
    public void addTable(Table t){
        tables.add(t);
    }
    
    public List<Table> getTables(){
        return tables;
    }
    
}
