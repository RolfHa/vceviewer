/**
 * THEMENBEREICH
 * 
 * Basisklasse entspricht der "themenbereich"-tabelle in der Datenbank
 *  
 * 
 * 
 * Hier werden ThemenBereiche mit folgenden Parametern gespeichert:
 * 
 * 
 * - id             int, wird in der DB via auto_increment erzeugt
 * 
 * - bezeichnung    String
 * 
 * 
 * 
 * Methodenübersicht:
 * 
 * - getById()
 * - toString()
 */
package vcelearner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author J.Weidehaas
 */
public class ThemenBereich {

    // Verbindungsvariablen
    //static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    // Objektvariablen
    private int id;
    private String bezeichnung;

    // Konstruktor
    public ThemenBereich(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    // GETTER
    public int getId() {
        return id;
    }

    /**
     * Gibt einen Themenbereich anhand der ID des Themenbereichs zurück.
     * @param tBid
     * @return 
     */
    public static ThemenBereich getById(int tBid) {
        ThemenBereich tB = null;
        try {
            
            //con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer","Petra","Panke");
            Connection con = MySQLConnection.getConnection();
            String Sql = "SELECT * FROM themenbereich WHERE id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, tBid);
            rst = pst.executeQuery();
            while (rst.next()) {

                tB = new ThemenBereich(rst.getInt("id"), rst.getString("bezeichnung"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return tB;
    }

//    @Override
//    public String toString() {
//        return "ThemenBereich{" + "id=" + id + ", bezeichnung=" + bezeichnung + '}';
//    }
    @Override
    public String toString() {
        return  bezeichnung;
    }
}
