/**
 * SITZUNGSLERNKARTE
 * 
 * Wrapper Klasse bzw. Erweiterung der Klasse Lernkarte. 
 *  
 * 
 * 
 * Hier werden LernKarten mit folgenden Parametern gespeichert:
 * 
 * 
 * - lK                         LernKarte
 * 
 * - gegebeneAntworten          ArrayList(Typ: PotentielleAntwort)
 * 
 * - wiederVorlage              boolean
 * 
 * - gemogelt                   boolean
 * 
 * - mogelnAktiv                boolean
 * 
 * 
 * Methodenübersicht:
 * 
 * keine, außer getter und setter
 * 
 */
package vcelearner;

import java.util.ArrayList;

/**
 *
 * @author J.Bleich
 */
public class SitzungsLernKarte {
    
    // Objektvariablen
    private LernKarte lK;
    private ArrayList<PotentielleAntwort> gegebeneAntworten;
    private boolean wiederVorlage;
    private boolean gemogelt;
    private boolean mogelnAktiv;

    // Konstruktor
    public SitzungsLernKarte(LernKarte lK) {
        this.lK = lK;
        gemogelt = false;
        mogelnAktiv = false;
        gegebeneAntworten = new ArrayList<>();
    }

    // GETTER und SETTER
    public ArrayList<PotentielleAntwort> getGegebeneAntworten() {
        return gegebeneAntworten;
    }

    public void setGegebeneAntworten(ArrayList<PotentielleAntwort> gegebeneAntworten) {
        this.gegebeneAntworten = gegebeneAntworten;
    }

    public boolean isWiederVorlage() {
        return wiederVorlage;
    }

    public void setWiederVorlage(boolean wiederVorlage) {
        this.wiederVorlage = wiederVorlage;
    }

    public boolean isGemogelt() {
        return gemogelt;
    }

    public void setGemogeltTrue() {
        this.gemogelt = true;
    }

    public LernKarte getlK() {
        return lK;
    }

    public boolean isMogelnAktiv() {
        return mogelnAktiv;
    }

    public void setMogelnAktiv(boolean mogelnAktiv) {
        this.mogelnAktiv = mogelnAktiv;
    }

}
