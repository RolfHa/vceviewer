/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vcelearner;

/**
 *
 * @author J.Weidehaas
 */
public class AuswahlUI extends javax.swing.JFrame {

    /**
     * Creates new form AuswahlUI
     */
    public AuswahlUI() {
        initComponents();
    }

    public AuswahlUI(Benutzer ben) {
        initComponents();
        labelBenutzer.setText("Benutzer : " + ben.getLogin());
        checkBoxesThema = new javax.swing.JCheckBox[]{checkBoxThema1, checkBoxThema2,
            checkBoxThema3, checkBoxThema4, checkBoxThema5, checkBoxThema6, checkBoxThema7};
        this.ben = ben;
        handler = new AuswahlLogik(ben);
        handler.setSitzungsTyp("Lern");
        comboBoxModus.setSelectedIndex(1);
        for (javax.swing.JCheckBox cbt:checkBoxesThema) {
            cbt.setSelected(true);
        }
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));

    }

    private void updateSelection() {
        for (int i=0; i<7; i++) {
        handler.gettBsGewaehlt()[i] = checkBoxesThema[i].isSelected();
        }
        handler.setNurWiederVorlage(toggleButtonWiedervorlage.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new javax.swing.JPanel();
        panel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        labelModus = new javax.swing.JLabel();
        comboBoxModus = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        labelTimer = new javax.swing.JLabel();
        spinnerTimer = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        buttonAbbruch = new javax.swing.JButton();
        buttonStart = new javax.swing.JButton();
        toggleButtonWiedervorlage = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        spinnerAnzahlFragen = new javax.swing.JSpinner();
        labelAnzahlFragen = new javax.swing.JLabel();
        panelThemenBereiche = new javax.swing.JPanel();
        checkBoxThema1 = new javax.swing.JCheckBox();
        checkBoxThema6 = new javax.swing.JCheckBox();
        checkBoxThema5 = new javax.swing.JCheckBox();
        checkBoxThema3 = new javax.swing.JCheckBox();
        checkBoxThema7 = new javax.swing.JCheckBox();
        checkBoxThema4 = new javax.swing.JCheckBox();
        checkBoxThema2 = new javax.swing.JCheckBox();
        labelThemenbereiche = new javax.swing.JLabel();
        buttonAlleThemen = new javax.swing.JButton();
        labelBenutzer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBackground.setBackground(new java.awt.Color(153, 153, 153));

        panel6.setBackground(panelBackground.getBackground());

        jPanel4.setBackground(panelBackground.getBackground());

        labelModus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelModus.setForeground(new java.awt.Color(240,240,240));
        labelModus.setText("Sitzungs Typ:");

        comboBoxModus.setModel(new javax.swing.DefaultComboBoxModel<>(sitzungsTypen));
        comboBoxModus.setNextFocusableComponent(spinnerTimer);
        comboBoxModus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxModusActionPerformed(evt);
            }
        });

        jPanel3.setBackground(panelBackground.getBackground());

        labelTimer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTimer.setForeground(new java.awt.Color(240,240,240));
        labelTimer.setText("Timer:");

        spinnerTimer.setNextFocusableComponent(spinnerAnzahlFragen);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spinnerTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTimer))
                .addContainerGap())
        );

        jPanel5.setBackground(panelBackground.getBackground());

        buttonAbbruch.setText("Abbruch");
        buttonAbbruch.setNextFocusableComponent(checkBoxThema1);

        buttonStart.setText("Start");
        buttonStart.setNextFocusableComponent(buttonAbbruch);
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        toggleButtonWiedervorlage.setText("Nur Wiedervolagen");
        toggleButtonWiedervorlage.setNextFocusableComponent(buttonStart);
        toggleButtonWiedervorlage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonWiedervorlageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(toggleButtonWiedervorlage))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonAbbruch)
                    .addComponent(buttonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(toggleButtonWiedervorlage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(buttonAbbruch))
        );

        jPanel2.setBackground(panelBackground.getBackground());

        spinnerAnzahlFragen.setNextFocusableComponent(toggleButtonWiedervorlage);

        labelAnzahlFragen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelAnzahlFragen.setForeground(new java.awt.Color(240,240,240));
        labelAnzahlFragen.setText("Anzahl Fragen:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAnzahlFragen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spinnerAnzahlFragen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerAnzahlFragen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAnzahlFragen))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelModus))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboBoxModus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(labelModus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxModus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panelThemenBereiche.setBackground(new java.awt.Color(240,240,240));

        checkBoxThema1.setText("Java Basics ");
        checkBoxThema1.setNextFocusableComponent(checkBoxThema2);
        checkBoxThema1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema1ActionPerformed(evt);
            }
        });

        checkBoxThema6.setText("Working with inheritance");
        checkBoxThema6.setNextFocusableComponent(checkBoxThema7);
        checkBoxThema6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema6ActionPerformed(evt);
            }
        });

        checkBoxThema5.setText("Flow Control");
        checkBoxThema5.setNextFocusableComponent(checkBoxThema6);
        checkBoxThema5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema5ActionPerformed(evt);
            }
        });

        checkBoxThema3.setText("Methods and Encapsulation");
        checkBoxThema3.setNextFocusableComponent(checkBoxThema4);
        checkBoxThema3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema3ActionPerformed(evt);
            }
        });

        checkBoxThema7.setText("Exception handling");
        checkBoxThema7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema7ActionPerformed(evt);
            }
        });

        checkBoxThema4.setText("String, StringBuilder, Arrays and ArrayList");
        checkBoxThema4.setNextFocusableComponent(checkBoxThema5);
        checkBoxThema4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema4ActionPerformed(evt);
            }
        });

        checkBoxThema2.setText("Working with Java data types");
        checkBoxThema2.setNextFocusableComponent(checkBoxThema3);
        checkBoxThema2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxThema2ActionPerformed(evt);
            }
        });

        labelThemenbereiche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelThemenbereiche.setText("Themenbereiche");

        buttonAlleThemen.setText("Alle Auswählen");
        buttonAlleThemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAlleThemenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThemenBereicheLayout = new javax.swing.GroupLayout(panelThemenBereiche);
        panelThemenBereiche.setLayout(panelThemenBereicheLayout);
        panelThemenBereicheLayout.setHorizontalGroup(
            panelThemenBereicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemenBereicheLayout.createSequentialGroup()
                .addGroup(panelThemenBereicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelThemenBereicheLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelThemenBereicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxThema4)
                            .addComponent(checkBoxThema1)
                            .addComponent(checkBoxThema2)
                            .addComponent(checkBoxThema3)
                            .addGroup(panelThemenBereicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(checkBoxThema6)
                                .addComponent(checkBoxThema5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkBoxThema7, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(panelThemenBereicheLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(labelThemenbereiche))
                    .addGroup(panelThemenBereicheLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(buttonAlleThemen)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        panelThemenBereicheLayout.setVerticalGroup(
            panelThemenBereicheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThemenBereicheLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelThemenbereiche)
                .addGap(5, 5, 5)
                .addComponent(checkBoxThema1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxThema7)
                .addGap(18, 18, 18)
                .addComponent(buttonAlleThemen)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        labelBenutzer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelBenutzer.setForeground(new java.awt.Color(240,240,240));
        labelBenutzer.setText("Benutzer:");

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBackgroundLayout.createSequentialGroup()
                        .addComponent(panelThemenBereiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBackgroundLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(labelBenutzer)))
                .addGap(25, 25, 25))
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelBenutzer)
                .addGap(26, 26, 26)
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelThemenBereiche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBackgroundLayout.createSequentialGroup()
                        .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxThema1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema1ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
        spinnerAnzahlFragen.repaint();
    }//GEN-LAST:event_checkBoxThema1ActionPerformed

    private void checkBoxThema2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema2ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema2ActionPerformed

    private void checkBoxThema3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema3ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema3ActionPerformed

    private void checkBoxThema4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema4ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema4ActionPerformed

    private void checkBoxThema5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema5ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema5ActionPerformed

    private void checkBoxThema6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema6ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema6ActionPerformed

    private void checkBoxThema7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxThema7ActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));
    }//GEN-LAST:event_checkBoxThema7ActionPerformed

    private void toggleButtonWiedervorlageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonWiedervorlageActionPerformed
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));

    }//GEN-LAST:event_toggleButtonWiedervorlageActionPerformed

    private void comboBoxModusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxModusActionPerformed
        switch (comboBoxModus.getSelectedIndex()) {
            case 0: {
                handler.setSitzungsTyp("Test");
                for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                    cbt.setSelected(true);
                    cbt.setEnabled(false);
                }
                spinnerAnzahlFragen.setValue(70);
                spinnerAnzahlFragen.setEnabled(false);
                spinnerTimer.setValue(120);
                spinnerTimer.setEnabled(false);
                toggleButtonWiedervorlage.setSelected(false);
                toggleButtonWiedervorlage.setEnabled(false);
                break;
            }
            case 1: {
                handler.setSitzungsTyp("Lern");
                for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                    cbt.setEnabled(true);
                }
                spinnerAnzahlFragen.setEnabled(true);
                spinnerTimer.setEnabled(true);
                toggleButtonWiedervorlage.setEnabled(true);
                break;
            }
            case 2: {
                handler.setSitzungsTyp("LernR");
                for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                    cbt.setEnabled(true);
                }
                spinnerAnzahlFragen.setEnabled(true);
                spinnerTimer.setEnabled(true);
                toggleButtonWiedervorlage.setEnabled(true);
                break;
            }
            case 3: {
                handler.setSitzungsTyp("ungewertet");
                for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                    cbt.setEnabled(true);
                }
                spinnerAnzahlFragen.setEnabled(true);
                spinnerTimer.setEnabled(true);
                toggleButtonWiedervorlage.setEnabled(true);
                break;
            }
        }
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));

    }//GEN-LAST:event_comboBoxModusActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        BenutzerSitzung session = new BenutzerSitzung(
                (Integer) spinnerTimer.getValue(),
                ben,
                handler.getSessionLKs((Integer) spinnerAnzahlFragen.getValue()),
                handler.getSitzungsTyp());
        new LernenUI(session).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonStartActionPerformed

    private void buttonAlleThemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAlleThemenActionPerformed
        if (checkBoxThema1.isSelected() && checkBoxThema2.isSelected()
                && checkBoxThema3.isSelected() && checkBoxThema4.isSelected()
                && checkBoxThema5.isSelected() && checkBoxThema6.isSelected()
                && checkBoxThema7.isSelected()) {
            for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                cbt.setSelected(false);
            }
        } else {
            for (javax.swing.JCheckBox cbt : checkBoxesThema) {
                cbt.setSelected(true);
            }
        }
        updateSelection();
        handler.calcUnselectedLKs();
        spinnerAnzahlFragen.setModel(new javax.swing.SpinnerNumberModel(
                handler.maxFragenAnzahlByTBs(), 0, handler.maxFragenAnzahlByTBs(), 1));        
    }//GEN-LAST:event_buttonAlleThemenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuswahlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuswahlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuswahlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuswahlUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuswahlUI().setVisible(true);
            }
        });
    }

    private Benutzer ben;
    private AuswahlLogik handler;
    private String[] sitzungsTypen = {"Prüfung", "Lernen", "Zufall", "ungewertet"};
    private javax.swing.JCheckBox[] checkBoxesThema;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAbbruch;
    private javax.swing.JButton buttonAlleThemen;
    private javax.swing.JButton buttonStart;
    private javax.swing.JCheckBox checkBoxThema1;
    private javax.swing.JCheckBox checkBoxThema2;
    private javax.swing.JCheckBox checkBoxThema3;
    private javax.swing.JCheckBox checkBoxThema4;
    private javax.swing.JCheckBox checkBoxThema5;
    private javax.swing.JCheckBox checkBoxThema6;
    private javax.swing.JCheckBox checkBoxThema7;
    private javax.swing.JComboBox<String> comboBoxModus;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelAnzahlFragen;
    private javax.swing.JLabel labelBenutzer;
    private javax.swing.JLabel labelModus;
    private javax.swing.JLabel labelThemenbereiche;
    private javax.swing.JLabel labelTimer;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelThemenBereiche;
    private javax.swing.JSpinner spinnerAnzahlFragen;
    private javax.swing.JSpinner spinnerTimer;
    private javax.swing.JToggleButton toggleButtonWiedervorlage;
    // End of variables declaration//GEN-END:variables
}
