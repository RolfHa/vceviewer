/**
 * BENUTZERSITZUNG
 * 
 * Klasse arbeitet auf den Klassen (indirekt auf den Tabellen):
 * 
 * - Benutzer2LernKarte
 * - LernSitzung2PotentielleAntwort
 * - Lernsitzung2LernKarte 
 *  
 * 
 * Hier wird auf den Tabellen und der UI mit folgenden Parametern gearbeitet:
 * 
 * 
 * - zeitVorgabe               int
 * 
 * - benutzer                  Benutzer
 * 
 * - sLKs                      ArrayList(Typ: SitzungsLernKarte)
 * 
 * - aktuellerSLKIndex         int
 * 
 * - lernSitzung               LernSitzung
 * 
 * 
 * Methodenübersicht:
 * 
 * - insert()
 * - geheZu()
 * - getTitelString()
 * - speichereInDB()
 * - getNextSitzungsLernKarte()
 * - getPrevSitzungsLernKarte()
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
 * @author J.Bleich
 */
public class BenutzerSitzung {

    // Verbindungsvariablen
    static Statement st = null;
    static PreparedStatement pst = null;
    static ResultSet rst = null;

    // Objektvariablen
    private int zeitVorgabe;
    private Benutzer benutzer;
    private ArrayList<SitzungsLernKarte> sLKs;
    private int aktuellerSLKIndex;
    private LernSitzung lernSitzung;

    // Konstruktor
    public BenutzerSitzung(int zeitVorgabe, Benutzer benutzer,
            ArrayList<LernKarte> lKs) {
        this.zeitVorgabe = zeitVorgabe;
        this.benutzer = benutzer;
        sLKs = new ArrayList<>();
//        ArrayList<Benutzer2LernKarte> b2LKs = Benutzer2LernKarte.getAllByBenutzer(benutzer);
//        ArrayList<Integer> wiederVorlageLKIds = new ArrayList<>();
//        for (Benutzer2LernKarte b2LK : b2LKs) {
//            if (b2LK.isWiedervorlage()) {
//                wiederVorlageLKIds.add(b2LK.getLernKarte_id());
//            }
//        }
        ArrayList<Integer> wiederVorlageLKIds = Benutzer2LernKarte.getWiedervorlageLernKarteIDsByBenutzer(benutzer);
        for (LernKarte lK : lKs) {
            this.sLKs.add(new SitzungsLernKarte(lK));
            if (wiederVorlageLKIds.contains(lK.getId())) {
                this.sLKs.get(this.sLKs.size() - 1).setWiederVorlage(true);
            }
        }
        lernSitzung = new LernSitzung("ungewertet", benutzer.getId());
        LernSitzung.insert(lernSitzung);
    }

    public BenutzerSitzung(int zeitVorgabe, Benutzer benutzer,
            ArrayList<LernKarte> lKs, String lernSitzungsTyp) {
        this.zeitVorgabe = zeitVorgabe;
        this.benutzer = benutzer;
        sLKs = new ArrayList<>();
//        ArrayList<Benutzer2LernKarte> b2LKs = Benutzer2LernKarte.getAllByBenutzer(benutzer);
//        ArrayList<Integer> wiederVorlageLKIds = new ArrayList<>();
//        for (Benutzer2LernKarte b2LK : b2LKs) {
//            if (b2LK.isWiedervorlage()) {
//                wiederVorlageLKIds.add(b2LK.getLernKarte_id());
//            }
//        }

        ArrayList<Integer> wiederVorlageLKIds = Benutzer2LernKarte.getWiedervorlageLernKarteIDsByBenutzer(benutzer);
        for (LernKarte lK : lKs) {
            this.sLKs.add(new SitzungsLernKarte(lK));
            if (wiederVorlageLKIds.contains(lK.getId())) {
                this.sLKs.get(this.sLKs.size() - 1).setWiederVorlage(true);
            }
        }
        lernSitzung = new LernSitzung(lernSitzungsTyp, benutzer.getId());
        LernSitzung.insert(lernSitzung);
    }

    // Methoden
    
    /**
     * Speichert die übergebene BenutzerSitzung.
     * 
     * in Tabellen:                     speichert:
     * Benutzer2LernKarte               wiedervorlage (löscht Eintrag, falls vorhanden)
     * LernSitzung2PotentielleAntwort   gegebeneAntworten (als Datentyp PotentielleAntwort)
     * Lernsitzung2LernKarte            gemogelt          
     * 
     * Reihenfolge der Speicherung: wiedervorlage, gegebeneAntwort, gemogelt
     * @param benutzerSitzung 
     */

    public static void insert(BenutzerSitzung benutzerSitzung) {

        for (int i = 0; i < benutzerSitzung.getsLKs().size(); i++) {

            // Wiedervorlage in Benutzer2Lernkarte speichern, nur wenn
            // Wiedervorlage = true und kein diesbezüglicher Eintrag in der DB
            // vorhaneden ist
            Benutzer2LernKarte b2lk
                    = new Benutzer2LernKarte(benutzerSitzung.getBenutzer().getId(),
                            benutzerSitzung.getsLKs().get(i).getlK().getId(),
                            benutzerSitzung.getsLKs().get(i).isWiederVorlage());

            if (benutzerSitzung.getsLKs().get(i).isWiederVorlage() == true
                    && Benutzer2LernKarte.checkWiedervorlage(b2lk) == false) {

                Benutzer2LernKarte.insert(b2lk);
            }
            // Wiedervorlage in Benutzer2Lernkarte löschen, nur wenn
            // Wiedervorlage = false und allerdings ein diesbezüglicher Eintrag in der DB
            // vorhanden ist
            if (benutzerSitzung.getsLKs().get(i).isWiederVorlage() == false && 
                Benutzer2LernKarte.checkWiedervorlage(b2lk)== true) {
                
                Benutzer2LernKarte.delete(benutzerSitzung.getBenutzer(), 
                    benutzerSitzung.getsLKs().get(i).getlK()
                );
            }
            // ArrayList Gegebene Antworten (als PotentielleAntworten) in 
            // LernSitzung2PotentielleAntwort speichern
            for (int j = 0; j < benutzerSitzung.getsLKs().get(i).getGegebeneAntworten().size(); j++) {

                LernSitzung2PotentielleAntwort ls2pa
                        = new LernSitzung2PotentielleAntwort(benutzerSitzung.getLernSitzung().getId(),
                                benutzerSitzung.getsLKs().get(i).getGegebeneAntworten().get(j).getId());
                LernSitzung2PotentielleAntwort.insert(ls2pa);

            }

            // Gemogelt in LernSitzung2LernKarte speichern
            LernSitzung2LernKarte ls2lk
                    = new LernSitzung2LernKarte(benutzerSitzung.getLernSitzung().getId(),
                            benutzerSitzung.getsLKs().get(i).getlK().getId(),
                            benutzerSitzung.getsLKs().get(i).isGemogelt());
            LernSitzung2LernKarte.insert(ls2lk);

        }

    }

    public int getAktuellerSLKIndex() {
        return aktuellerSLKIndex;
    }

    public void setAktuellerSLKIndex(int aktuellerSLKIndex) {
        this.aktuellerSLKIndex = aktuellerSLKIndex;
    }

    public LernSitzung getLernSitzung() {
        return lernSitzung;
    }

    public void setLernSitzung(LernSitzung lernSitzung) {
        this.lernSitzung = lernSitzung;
    }

    public int getZeitVorgabe() {
        return zeitVorgabe;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public ArrayList<SitzungsLernKarte> getsLKs() {
        return sLKs;
    }

    public SitzungsLernKarte getAktuelleSitzungsLernKarte() {
        return sLKs.get(aktuellerSLKIndex);
    }

    public SitzungsLernKarte geheZu(int nummer) {
        if (nummer <= sLKs.size() && nummer > 0) {
            aktuellerSLKIndex = nummer - 1;
        }
        return getAktuelleSitzungsLernKarte();
    }

    public String getTitelString(int modus) {
        // modus 0 : Frage x / y (ID xxx) Schwierigkeit xxx
        // modus 1 : Themengebiete
        String rueckgabe = "";
        if (modus == 1) {
            rueckgabe += "Themenbereich(e): " + getAktuelleSitzungsLernKarte().getlK().gettBs().toString();
        } else {
            rueckgabe += "Frage " + (aktuellerSLKIndex + 1) + " / " + sLKs.size();
            rueckgabe += " \t(ID = " + getAktuelleSitzungsLernKarte().getlK().getId() + ")";
            rueckgabe += " \tSchwierigkeit: " + sLKs.get(aktuellerSLKIndex).getlK().getSchwierigkeitsGrad();
            if (getAktuelleSitzungsLernKarte().isGemogelt()) {
                rueckgabe += " \tGEMOGELT!";
            }
        }
        return rueckgabe;
    }

    public void speichereInDB() {
        // Dummy-Code
//        String ausgabe = "\nBenutzer : " + benutzer.getLogin();
//        ausgabe += "\nZeitlimit : " + zeitVorgabe;
//        for (SitzungsLernKarte sLK : sLKs) {
//            ausgabe += "\nFrage-ID " + sLK.getlK().getId() + " gegebene Antworten : ";
//            for (PotentielleAntwort pA : sLK.getlK().getpAs()) {
//                if (sLK.getGegebeneAntworten().contains(pA)) {
//                    ausgabe += pA.getId() + "(" + (sLK.getlK().getpAs().indexOf(pA) + 1) + "), ";
//                }
//            }
//            ausgabe += "Gemogelt = " + sLK.isGemogelt() + ", Wiedervorlage = "
//                    + sLK.isWiederVorlage() + "\n";
//        }
//        System.out.println(ausgabe);
        insert(this);
    }

    public SitzungsLernKarte getNextSitzungsLernKarte() {
        if (aktuellerSLKIndex < sLKs.size() - 1) {
            aktuellerSLKIndex++;
        }
        return getAktuelleSitzungsLernKarte();
    }

    public SitzungsLernKarte getPrevSitzungsLernKarte() {
        if (aktuellerSLKIndex > 0) {
            aktuellerSLKIndex--;
        }
        return getAktuelleSitzungsLernKarte();
    }

}
