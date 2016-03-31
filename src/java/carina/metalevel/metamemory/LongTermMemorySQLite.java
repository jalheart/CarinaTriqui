/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.metalevel.metamemory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalheart
 */
public class LongTermMemorySQLite extends Memory{
    Connection  c=null;
    public LongTermMemorySQLite() {        
        try {
            Class.forName("org.sqlite.JDBC");
            c   =DriverManager.getConnection("jdbc:sqlite:./longtermmemory.db");            
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }        
        this.crearTabla();        
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(LongTermMemorySQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @Override
    public void memorize(String key, Object information) {
        Statement s;
        try {
            s = c.createStatement();
            String sql = "INSERT INTO LONG_TERM_MEMORY " +
                    "(KEY,VALUE) "+
                    "VALUES('"+key+"','"+information+"')";
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }        
    }
    @Override
    public Object remember(String key) {
        Object salida   =null;
        Statement s;
        try {
            s = c.createStatement();
            String sql = "SELECT VALUE FROM LONG_TERM_MEMORY "+
                            "WHERE KEY='"+key+"'";
            ResultSet rs= s.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("VALUE"));
            }
            s.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }        
        return salida;
    }
    @Override
    public void forgetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void crearTabla(){
        Statement s;        
        try {
            s = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS LONG_TERM_MEMORY " +
                     "(KEY CHAR(50) PRIMARY KEY NOT NULL, " + 
                     " VALUE         TEXT     NOT NULL )"; 
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}