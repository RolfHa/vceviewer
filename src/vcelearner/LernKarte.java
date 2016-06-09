/**
 * LERNKARTE
 * 
 * Basisklasse entspricht der "lernkarte"-tabelle in der Datenbank
 *  
 * 
 * Hier wird die LernKarte mit folgenden Parametern gespeichert:
 * 
 * 
 * - id                     int
 * 
 * - frage                  String
 * 
 * - schwierigkeitsgrad     int
 * 
 * - tBs                    ArrayList(Typ: ThemenBereich)
 * 
 * - pAs                    ArrayList(Typ: PotentielleAntwort)
 * 
 * 
 * Methodenübersicht:
 * 
 * - insert()
 * - delete()
 * - getAll()
 * - getById()
 * - update()           DEAKTIVIERT!
 * - toString()
 */
package vcelearner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author J.Weidehaas
 */
public class LernKarte {

    // Verbindungsvariablen
    //static Connection con = null;
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    // Objektvariablen
    private int id;
    private String frage;
    private int schwierigkeitsGrad;
    private boolean aktiv;
    private ArrayList<ThemenBereich> tBs = null;
    private ArrayList<PotentielleAntwort> pAs = null;

    // Konstruktor
    public LernKarte(int id, String frage, int schwierigkeitsGrad, boolean aktiv) {
        this.id = id;
        this.frage = frage;
        this.schwierigkeitsGrad = schwierigkeitsGrad;
        this.aktiv = aktiv;
    }

    public LernKarte(String frage, int schwierigkeitsGrad) {
        this.frage = frage;
        this.schwierigkeitsGrad = schwierigkeitsGrad;
    }

    // GETTER
    public int getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public int getSchwierigkeitsGrad() {
        return schwierigkeitsGrad;
    }

    public ArrayList<ThemenBereich> gettBs() {
        return tBs;
    }

    public ArrayList<PotentielleAntwort> getpAs() {
        return pAs;
    }

    public boolean isAktiv() {
        return aktiv;
    }
    
    // SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setSchwierigkeitsGrad(int schwierigkeitsGrad) {
        this.schwierigkeitsGrad = schwierigkeitsGrad;
    }

    public void settBs(ArrayList<ThemenBereich> tBs) {
        this.tBs = tBs;
    }

    public void setpAs(ArrayList<PotentielleAntwort> pAs) {
        this.pAs = pAs;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    /**
     * Speichert die übergebene LernKarte in die LernKartetabelle, die Zuordnung
     * der Themenbereiche zur LernKarte in die LernKarte2ThemenBereichtabelle 
     * und die Zuordnung der PotentiellenAntworten zur LernKarte in die 
     * PotentielleAntworttabelle.
     * @param lK 
     */
    public static void insert(LernKarte lK) {
        
        try {
            // VERBINDUNG AUFBBAUEN:
            Connection con = MySQLConnection.getConnection();

            // STATEMENT
            String Sql = "INSERT INTO lernkarte VALUES (null, ?, ?, 'aktiv')";
            pst = con.prepareStatement(Sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, lK.getFrage());
            pst.setInt(2, lK.getSchwierigkeitsGrad());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                lK.setId(rst.getInt(1));
            }

            //Zugehörigkeit zu Themenbereichen speichern
            for (ThemenBereich tB : lK.gettBs()) {
                LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(lK.getId(), tB.getId());
                LernKarte2ThemenBereich.insert(lK2TB);
            }
            //Zugehörige PotentielleAntworten speichern
            for (PotentielleAntwort pA : lK.getpAs()) {
                pA.setLernKarte_id(lK.getId());
                PotentielleAntwort.insert(pA);
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
    }

    /**
     * Löscht die übergebene LernKarte in der LernKartetabelle, die Zuordnung
     * der Themenbereiche zur LernKarte in der LernKarte2ThemenBereichtabelle 
     * und die Zuordnung der PotentiellenAntworten zur LernKarte in der 
     * PotentielleAntworttabelle.
     * @param lK 
     */
    public static void delete(LernKarte lK) {

        try {
            LernKarte2ThemenBereich.delete(lK.getId());
            PotentielleAntwort.delete(lK.getId());

            Connection con = MySQLConnection.getConnection();
            String Sql = "DELETE FROM lernkarte WHERE id=?";
            pst = con.prepareStatement(Sql);
            pst.setInt(1, lK.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {

                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * Gibt eine ArrayList vom Typ LernKarte von allen in der DB gespeicherten
     * LernKarten zurück.
     * @return 
     */
    public static ArrayList<LernKarte> getAll() {
        ArrayList<LernKarte> lKs = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM lernkarte";
            st = con.createStatement();
            rst = st.executeQuery(sql);
            while (rst.next()) {
                LernKarte lK = new LernKarte(rst.getInt("id"), rst.getString("frage"), rst.getInt("schwierigkeitsGrad"),Boolean.parseBoolean(rst.getString("aktiv")));
                lK.setpAs(PotentielleAntwort.getAllByLernKarte_id(lK.getId()));

                ArrayList<LernKarte2ThemenBereich> lK2TBs = LernKarte2ThemenBereich.getAllByLernKarte_id(lK.getId());

                ArrayList<ThemenBereich> tBs = new ArrayList<>();
                for (LernKarte2ThemenBereich lK2TB : lK2TBs) {
                    tBs.add(ThemenBereich.getById(lK2TB.getThemenBereich_id()));
                }
                lK.settBs(tBs);
                lKs.add(lK);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lKs;
    }
    
    /**
     * Gibt eine  LernKarte anhand der übergebenen lernKarten_id zurück.
     * @return 
     */
     public static LernKarte getById(int lkid) {
       LernKarte lK = null;
        try {

            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM lernkarte WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, lkid);
            rst = pst.executeQuery();
            while (rst.next()) {

                lK = new LernKarte(rst.getInt("id"), rst.getString("frage"), rst.getInt("schwierigkeitsGrad"), Boolean.parseBoolean(rst.getString("aktiv")));

                lK.setpAs(PotentielleAntwort.getAllByLernKarte_id(lK.getId()));

                ArrayList<LernKarte2ThemenBereich> lK2TBs = LernKarte2ThemenBereich.getAllByLernKarte_id(lK.getId());

                ArrayList<ThemenBereich> tBs = new ArrayList<>();
                for (LernKarte2ThemenBereich lK2TB : lK2TBs) {
                    tBs.add(ThemenBereich.getById(lK2TB.getThemenBereich_id()));

                }
                lK.settBs(tBs);
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {

                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return lK;
    } 
     
    public static ArrayList<LernKarte> getAllAktiv(){
        ArrayList<LernKarte> lKs = new ArrayList<>();
        try {
            Connection con = MySQLConnection.getConnection();
            String sql = "SELECT * FROM lernkarte WHERE aktiv = 'true'";
            st = con.createStatement();
            rst = st.executeQuery(sql);
            while (rst.next()) {
                LernKarte lK = new LernKarte(rst.getInt("id"), rst.getString("frage"), rst.getInt("schwierigkeitsGrad"),Boolean.parseBoolean(rst.getString("aktiv")));
                lK.setpAs(PotentielleAntwort.getAllByLernKarte_id(lK.getId()));

                ArrayList<LernKarte2ThemenBereich> lK2TBs = LernKarte2ThemenBereich.getAllByLernKarte_id(lK.getId());

                ArrayList<ThemenBereich> tBs = new ArrayList<>();
                for (LernKarte2ThemenBereich lK2TB : lK2TBs) {
                    tBs.add(ThemenBereich.getById(lK2TB.getThemenBereich_id()));
                }
                lK.settBs(tBs);
                lKs.add(lK);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lKs; 
    }

    /**
     * Updatet die übergebene LernKarte in der LernKartetabelle.
     * Updatet die Zuordnung der Themenbereiche zur LernKarte per SQL-Update
     * in der LernKarte2ThemenBereichtabelle durch löschen und neu einfügen
     * und die Zuordnung der PotentiellenAntworten zur LernKarte in der 
     * PotentielleAntworttabelle durch löschen und neu einfügen.
     * Es werden die entsprechen Insert()- und Delete()-Methoden hierzu 
     * aufgerufen.
     * @param lK 
     */
    
    /*
    public static void update(LernKarte lK) {

        try {

            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE lernkarte SET frage=?, schwierigkeitsgrad=? WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, lK.getFrage());
            pst.setInt(2, lK.getSchwierigkeitsGrad());
            pst.setInt(3, lK.getId());

            pst.executeUpdate();

            LernKarte2ThemenBereich.delete(lK.getId());

            for (ThemenBereich tB : lK.gettBs()) {
                LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(lK.getId(), tB.getId());
                LernKarte2ThemenBereich.insert(lK2TB);
            }
            PotentielleAntwort.delete(lK.getId());

            for (PotentielleAntwort pA : lK.getpAs()) {
                pA.setLernKarte_id(lK.getId());
                PotentielleAntwort.insert(pA);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
    */
    
    public static void update(LernKarte lK){
        
        try {
            int alteLKId = lK.getId();
            // Variable aktiv von der alten Lernkarte auf false setzen    
            Connection con = MySQLConnection.getConnection();
            String sql = "UPDATE lernkarte SET aktiv = 'false' WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, lK.getId());
            pst.executeUpdate();
            
            // Neue Lernkarte in die Datenbank speichern            
            LernKarte.insert(lK);
            // Methode aufrufen um die Wiedervorlage auf die neue Lernkarte zu setzen
            Benutzer2LernKarte.update(alteLKId, lK.getId());
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "LernKarte ID: " + id
                + "\n" + "Schwierigkeitsgrad: " + schwierigkeitsGrad
                + "\n" + "Themenbereiche: " + tBs
                + "\n" + "Frage: " + frage
                + "\n" + "Potentielle Antworten: " + pAs + "\n";
    }
}
