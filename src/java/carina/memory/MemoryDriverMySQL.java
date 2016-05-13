/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.memory;

import carina.metalevel.old.metamemory.LongTermMemorySQLite;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalheart
 */
public class MemoryDriverMySQL extends MemoryDriver{

    public MemoryDriverMySQL(Object config) {
        super(config);
    }

    @Override
    public void storeInformation(MemoryInformation information) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MemoryInformation retrieveInformation(String cue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forgetInformation(String cue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void crearTabla(){
        Statement s;        
        try {
            s = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS LONG_TERM_MEMORY " +
                     "(KEY CHAR(50) PRIMARY KEY NOT NULL, " + 
                     " VALUE         BLOB     NOT NULL )";
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );            
        }
    }
    private void conectar(){
        try {
            Class.forName("org.sqlite.JDBC");
            c   =DriverManager.getConnection((String)this.getConfig());
        } catch (Exception e) {
            System.err.println("Error en bd");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } 
    }
    private void desconectar(){
        try {
            c.close();
        } catch (SQLException ex) {
            System.out.println("Error cerrando");
            Logger.getLogger(LongTermMemorySQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}