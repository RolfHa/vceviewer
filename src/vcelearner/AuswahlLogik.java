/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcelearner;

import java.util.ArrayList;

/**
 *
 * @author J.Bleich
 */
public class AuswahlLogik {

    private ArrayList<LernKarte> alleLKs = LernKarte.getAllAktiv();
    private ArrayList<LernKarte> unselectedLKs = new ArrayList<>();
    private boolean[] tBsGewaehlt = new boolean[7];
    private int zeitLimit;
    private int fragenAnzahl;
    private boolean nurWiederVorlage;
    private Benutzer ben;
    private String sitzungsTyp;
    ArrayList<Integer> wiederVorlageLKIDs = new ArrayList<>();

    
    public AuswahlLogik(Benutzer ben) {
        this.ben = ben;
        ArrayList<Benutzer2LernKarte> b2LKs = Benutzer2LernKarte.getAllByBenutzer(ben);
        for (Benutzer2LernKarte b2LK : b2LKs) {
            if (b2LK.isWiedervorlage()) {
                wiederVorlageLKIDs.add(b2LK.getLernKarte_id());
            }
        }
    }

    public boolean[] gettBsGewaehlt() {
        return tBsGewaehlt;
    }

    public void settBsGewaehlt(boolean[] tBGewaehlt) {
        this.tBsGewaehlt = tBGewaehlt;
    }

    public int getZeitLimit() {
        return zeitLimit;
    }

    public void setZeitLimit(int zeitLimit) {
        this.zeitLimit = zeitLimit;
    }

    public int getFragenAnzahl() {
        return fragenAnzahl;
    }

    public void setFragenAnzahl(int fragenAnzahl) {
        this.fragenAnzahl = fragenAnzahl;
    }

    public boolean isNurWiederVorlage() {
        return nurWiederVorlage;
    }

    public void setNurWiederVorlage(boolean nurWiederVorlage) {
        this.nurWiederVorlage = nurWiederVorlage;
    }

    public String getSitzungsTyp() {
        return sitzungsTyp;
    }

    public void setSitzungsTyp(String sitzungsTyp) {
        this.sitzungsTyp = sitzungsTyp;
    }

    public int maxFragenAnzahlByTBs() {
        return alleLKs.size() - unselectedLKs.size();
    }

    public void calcUnselectedLKs() {
        unselectedLKs.clear();
        for (LernKarte lK : alleLKs) {
            boolean keep = false;
            for (int tBIndex = 0; tBIndex < tBsGewaehlt.length; tBIndex++) {
                if (tBsGewaehlt[tBIndex]) {
                    for (ThemenBereich tB : lK.gettBs()) {
                        if (tB.getId() == tBIndex + 1) {
                            keep = true;
                        }
                    }
                }
            }
            if (nurWiederVorlage && !wiederVorlageLKIDs.contains(lK.getId())) {
                keep = false;
            }
            if (!keep) {
                unselectedLKs.add(lK);
            }
        }
    }

    public ArrayList<LernKarte> getSessionLKs() {
        ArrayList<LernKarte> selectedLKs = (ArrayList) alleLKs.clone();
        for (LernKarte lKToRemove : unselectedLKs) {
            selectedLKs.remove(lKToRemove);
        }
        if (sitzungsTyp == "LernR" || sitzungsTyp == "Test") {
            java.util.Collections.shuffle(selectedLKs);
        }
        for (LernKarte lK : selectedLKs) {
            java.util.Collections.shuffle(lK.getpAs());
        }
        return selectedLKs;
    }

    public ArrayList<LernKarte> getSessionLKs(int anzahl) {
        ArrayList<LernKarte> selectedLKs = (ArrayList) alleLKs.clone();
        for (LernKarte lKToRemove : unselectedLKs) {
            selectedLKs.remove(lKToRemove);
        }
        if (sitzungsTyp == "LernR" || sitzungsTyp == "Test") {
            java.util.Collections.shuffle(selectedLKs);
        }
        while (selectedLKs.size() > anzahl) {
            selectedLKs.remove(0);
        }
        for (LernKarte lK : selectedLKs) {
            java.util.Collections.shuffle(lK.getpAs());
        }
        return selectedLKs;
    }
}
