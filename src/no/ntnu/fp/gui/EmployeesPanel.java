/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EmployeesPanel.java
 *
 * Created on 18.mar.2011, 10:16:19
 */
package no.ntnu.fp.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import no.ntnu.fp.model.Communication;
import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.Person;
import no.ntnu.fp.model.ServiceWrapper.EventsRunner;
import no.ntnu.fp.utils.Loader;
import no.ntnu.fp.utils.ServiceLoaders;

/**
 *
 * @author Erlend Dahl
 */
public class EmployeesPanel extends BaseCalendarView
{

    DefaultListModel dlm = new DefaultListModel();

    /** Creates new form EmployeesPanel */
    public EmployeesPanel()
    {
        initComponents();

        employees = Communication.getEmployees();
        ListEmployees();
    }
    List<Person> employees;

    private void ListEmployees()
    {
        String f = txtFilter.getText().toLowerCase();
        dlm.clear();
        lstEmployees.setModel(dlm);
        for (Person p : employees)
        {
            if (p == null)
            {
                continue;
            }
            if (f.equals("") || (p.getName() + p.getEmail()).toLowerCase().contains(f))
            {
                dlm.addElement(p);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmployees = new javax.swing.JList();
        btnShowCalendar = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        lstEmployees.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstEmployees.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstEmployees.setCellRenderer(new AllEmployeesListRenderer());
        lstEmployees.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstEmployeesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstEmployees);

        btnShowCalendar.setText("Vis kalender");
        btnShowCalendar.setEnabled(false);
        btnShowCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCalendarActionPerformed(evt);
            }
        });

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilterKeyTyped(evt);
            }
        });

        jLabel1.setText("Filter:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnShowCalendar)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShowCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lstEmployeesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstEmployeesValueChanged
        btnShowCalendar.setEnabled(lstEmployees.getSelectedIndex() > -1);
    }//GEN-LAST:event_lstEmployeesValueChanged

    private void btnShowCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCalendarActionPerformed
        final Person person = (Person) dlm.getElementAt(lstEmployees.getSelectedIndex());

        getService().getEvents(person.getEmail(), new EventsRunner()
        {

            public void run(List<Event> events)
            {
                final List<Event> otherEvents = events;
                getService().getEvents(new EventsRunner()
                {

                    public void run(List<Event> events)
                    {
                        CalendarDoubleView cdv = new CalendarDoubleView(events, otherEvents, person.getName());
                        MainPanel.getMainForm().changeMain(MainPanel.CALENDAR, cdv);
                        for (CalendarPanel cp : cdv.getCalendarPanels())
                        {
                            cp.addCalendarPanelActionListener(MainPanel.getMainForm());
                        }
                    }
                });
            }
        });
        /*List<Event> events = Communication.getEvents(person.getEmail());
        CalendarDoubleView cdv = new CalendarDoubleView(events, person.getName());
        MainPanel.getMainForm().changeMain(MainPanel.CALENDAR, cdv);
        for(CalendarPanel cp : cdv.getCalendarPanels()) {
        cp.addCalendarPanelActionListener(MainPanel.getMainForm());
        }*/
    }//GEN-LAST:event_btnShowCalendarActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        ListEmployees();
    }//GEN-LAST:event_txtFilterKeyReleased

    private void txtFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyTyped
        ListEmployees();
    }//GEN-LAST:event_txtFilterKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstEmployees;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
