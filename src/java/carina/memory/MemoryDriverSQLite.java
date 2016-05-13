/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carina.memory;

import carina.metalevel.old.metamemory.LongTermMemorySQLite;
import carina.objectlevel.Input;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jalheart
 */
public class MemoryDriverSQLite extends MemoryDriver{
    private Connection c;
    public MemoryDriverSQLite(Object config) {
        super("jdbc:sqlite:./memory/"+config+".db");
        this.conectar();
        this.crearTabla();
        this.desconectar();
    }

    @Override
    public void storeInformation(MemoryInformation information) {
        //TODO Hace falta serializar y deserializar los objetos almacenados
        Statement s;
        this.conectar();
        System.out.println("Aqui estoy");
        try {
//            s = c.createStatement();
//            String sql = "INSERT OR REPLACE INTO LONG_TERM_MEMORY " +
//                    "(KEY,VALUE) "+
//                    "VALUES('"+information.cue+"','"+information+"')";
//            s.executeUpdate(sql);
//            s.close();
            PreparedStatement ps =c.prepareStatement("insert or replace into LONG_TERM_MEMORY (key,value) values(?,?)");
            ps.setString(1, information.cue);
            ps.setObject(2, new Input("Amor", "salvaje"));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );            
        }
        this.desconectar();
    }

    @Override
    public MemoryInformation retrieveInformation(String cue) {
        Object salida   =null;
        this.conectar();
        Statement s;
        try {
            s = c.createStatement();
            String sql = "SELECT VALUE FROM LONG_TERM_MEMORY "+
                            "WHERE KEY='"+cue+"'";
            ResultSet rs= s.executeQuery(sql);
            while (rs.next()) {
                salida  =rs.getObject("VALUE");                
            }
            System.out.println("La salida es: "+((Input)salida).getName());
            s.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );            
        }
        this.desconectar();
        return (MemoryInformation)salida;
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