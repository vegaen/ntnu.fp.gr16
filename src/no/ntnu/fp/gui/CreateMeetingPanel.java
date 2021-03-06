package no.ntnu.fp.gui;

import javax.activity.InvalidActivityException;
import no.ntnu.fp.model.*;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.tools.Diagnostic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CreateMeetingPanel extends BaseCalendarView
{

    private CreateMeetingModel model;
    private JPanel meetingPanel;
    private JButton save_button;
    private JButton delete_button;
    private JPanel buttons_panel;
    private JLabel newMeetingLabel;
    private JLabel selected_users_label;
    private JButton unselect;
    private JButton room_button;
    private JScrollPane jScrollPane1;
    private JList selected_users_list;
    private JButton select;
    private JPanel buttons_select_unselect_panel;
    private JScrollPane selected_users_scroll;
    private JList all_users_list;
    private JLabel all_users_label;
    private JPanel allUsersPanel;
    private JComboBox roomChooser;
    private JLabel roomLabel;
    private JEditorPane description;
    private JLabel descriptionLabel;
    private JLabel endTimeLabel;
    private JLabel startTimeLabel;
    private JComboBox endTime;
    private JComboBox startTime;
    private JLabel dateLabel;
    private JDateChooser calendar;
    private DefaultListModel selected_users_listModel;
    private DefaultListModel all_users_listModel;
    private DefaultComboBoxModel roomChooserModel;
    private ComboBoxModel end_comboModel;
    private ComboBoxModel startTimeModel;
    private JPanel room_chooser_panel;

    public CreateMeetingPanel(Calendar date, Calendar startTime, Calendar endTime)
    {
        super();
        model = new CreateMeetingModel(date, startTime, endTime);
        initGUI();
    }

    public CreateMeetingPanel(no.ntnu.fp.model.Event event)
    {
        super();
        model = new CreateMeetingModel(event);
        initGUI();
    }

    public CreateMeetingPanel()
    {
        super();
        model = new CreateMeetingModel();
        initGUI();
    }

    @SuppressWarnings("CallToThreadDumpStack")
    private void initGUI()
    {
        try
        {
            GridBagLayout thisLayout = new GridBagLayout();
            thisLayout.rowWeights = new double[]
                    {
                        0.1
                    };
            thisLayout.rowHeights = new int[]
                    {
                        7
                    };
            thisLayout.columnWeights = new double[]
                    {
                        0.0, 0.0
                    };
            thisLayout.columnWidths = new int[]
                    {
                        293, 567
                    };
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(873, 406));

            meetingPanel = new JPanel();
            GridBagLayout meetingLayout = new GridBagLayout();
            this.add(meetingPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 30));
            this.add(getAll_users(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            meetingLayout.rowWeights = new double[]
                    {
                        0.1, 0.1, 0.1, 0.1, 0.1, 0.0, 0.0, 0.1
                    };
            meetingLayout.rowHeights = new int[]
                    {
                        7, 7, 7, 7, 7, 25, 44, 7
                    };
            meetingLayout.columnWeights = new double[]
                    {
                        0.0, 0.1
                    };
            meetingLayout.columnWidths = new int[]
                    {
                        10, 7
                    };

            calendar = new JDateChooser();

            meetingPanel.setLayout(meetingLayout);
            meetingPanel.add(calendar, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            meetingPanel.add(getDate_label(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getStart_time_label(), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getEnd_time(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getEnd_time_label(), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getDescription_label(), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getStart_time(), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            meetingPanel.add(endTime, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            meetingPanel.add(getDescription(), new GridBagConstraints(1, 5, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
            meetingPanel.add(getRoom_label(), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getRoomChooserPanel(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
            meetingPanel.add(getNew_agreement_label(), new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            meetingPanel.add(getButtons_panel(), new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        fillCells();
        doEditable();
        
    }

    private void doEditable()
    {
        if (!model.isEditable())
        {
            calendar.setEnabled(false);
            startTime.setEnabled(false);
            endTime.setEnabled(false);
            roomChooser.setEnabled(false);
            description.setEnabled(false);
            select.setEnabled(false);
            unselect.setEnabled(false);
            room_button.setEnabled(false);
        }
        else
        {
            calendar.setEnabled(true);
            startTime.setEnabled(true);
            endTime.setEnabled(true);
            roomChooser.setEnabled(false);
            description.setEnabled(true);
            select.setEnabled(true);
            unselect.setEnabled(true);
            room_button.setEnabled(true);
        }
    }

    public Component getJDateChooser()
    {
        calendar = new JDateChooser();
        return calendar;
    }

    private void fillCells()
    {

        model.setDefaultValues();
        calendar.setDate(new Date(model.getDate().getTimeInMillis()));
        //int startTimeIndex = model.getStartTime().get(Calendar.HOUR_OF_DAY) - 1;
        startTime.setSelectedIndex((model.getStartTime().get(Calendar.HOUR_OF_DAY) + 23) % 24);
        endTime.setSelectedIndex((model.getEndTime().get(Calendar.HOUR_OF_DAY) + 23) % 24);
        System.out.println("index="+(model.getStartTime().get(Calendar.HOUR_OF_DAY) + 23) % 24);
        description.setText(model.getDescription());
        roomChooserModel.setSelectedItem(model.getRoom());
        addEmployees();

    }

    private void addEmployees()
    {
        List<Person> attendees = model.getAttendees();
        if (attendees != null)
        {
            for (Person person : attendees)
            {
                selected_users_listModel.addElement(person);
            }
        }

        List<Person> employees = model.getAvailUsers();
        if (employees != null)
        {
            for (Person person : employees)
            {
                all_users_listModel.addElement(person);
            }
        }

    }

    private JLabel getDate_label()
    {
        if (dateLabel == null)
        {
            dateLabel = new JLabel();
            dateLabel.setText("Dato: ");
            dateLabel.setLabelFor(calendar);
            dateLabel.setPreferredSize(new java.awt.Dimension(100, 0));
            dateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        }
        return dateLabel;
    }

    private JLabel getStart_time_label()
    {
        if (startTimeLabel == null)
        {
            startTimeLabel = new JLabel();
            startTimeLabel.setText("Start: ");
            startTimeLabel.setPreferredSize(new java.awt.Dimension(100, 0));
            startTimeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        }
        return startTimeLabel;
    }

    private JLabel getEnd_time_label()
    {
        if (endTimeLabel == null)
        {
            endTimeLabel = new JLabel();
            endTimeLabel.setText("Slutt: ");
            endTimeLabel.setPreferredSize(new java.awt.Dimension(100, 0));
            endTimeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        }
        return endTimeLabel;
    }

    private JLabel getDescription_label()
    {
        if (descriptionLabel == null)
        {
            descriptionLabel = new JLabel();
            descriptionLabel.setText("Beskrivelse: ");
            descriptionLabel.setPreferredSize(new java.awt.Dimension(100, 0));
            descriptionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        }
        return descriptionLabel;
    }

    public JComboBox getStart_time()
    {
        if (startTime == null)
        {
            startTimeModel = new DefaultComboBoxModel(model.getTimes());
            startTime = new JComboBox(startTimeModel);
            startTime.setEditable(true);
            startTime.setRequestFocusEnabled(false);
            startTime.addActionListener(new TimeListener());
        }
        return startTime;
    }

    private JComboBox getEnd_time()
    {
        if (endTime == null)
        {
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + model.getTimes()[1].getTime());
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + model.getTimes()[1].getTime());
        	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ " + model.getTimes()[1].getTime());
        	
            end_comboModel = new DefaultComboBoxModel(model.getTimes());
            endTime = new JComboBox(end_comboModel);
            endTime.setRequestFocusEnabled(false);
            endTime.setEditable(true);
            endTime.addActionListener(new TimeListener());
            System.out.println(((Time)endTime.getItemAt(5)).getTime());
        }
        return endTime;
    }

    public ComboBoxModel getEndComboModel()
    {
        return end_comboModel;
    }

    public ComboBoxModel getStartComboModel()
    {
        return startTimeModel;
    }

    public JEditorPane getDescription()
    {
        if (description == null)
        {
            description = new JEditorPane();
            description.setSize(100, 81);
            description.setPreferredSize(new java.awt.Dimension(100, 21));
            description.setBorder(new LineBorder(Color.black));
        }
        return description;
    }

    private JButton getSave_button()
    {
        if (save_button == null)
        {
            save_button = new JButton();
            save_button.setIcon(new ImageIcon(getClass().getResource("/Save.png")));
            save_button.setIconTextGap(10);
            save_button.setText("Lagre");
            save_button.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    saveEvent();
                }
            });
        }
        return save_button;
    }

    private JButton getDelete_button()
    {
        if (delete_button == null)
        {
            delete_button = new JButton();
            delete_button.setIcon(new ImageIcon(getClass().getResource("/Close-icon.png")));
            delete_button.setIconTextGap(10);
        	delete_button.setText("Slett");
            delete_button.addActionListener(new ActionListener()
            {

                @SuppressWarnings("CallToThreadDumpStack")
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        deleteEvent();
                    }
                    catch (InvalidActivityException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            });
            if (model.isNew())
            {
                delete_button.setText("Avbryt");
            } else if(model.isAtendee() && !model.isEditable()) {
                delete_button.setText("Meld av");
            } else if(!model.isEditable())
            {
                delete_button.setEnabled(false);
            }
        }
        return delete_button;
    }

    protected void deleteEvent() throws InvalidActivityException
    {
    	//TODO: legg til funksjonalitet til å melde seg av event
        if (model.isNew())
        {
            MainPanel.getMainForm().changeMain(MainPanel.CALENDAR);
            MainPanel.refreshCalendar();
        }
        else if(!model.isEditable()) {
            model.messageMeOff();
            MainPanel.getMainForm().changeMain(MainPanel.CALENDAR);
            MainPanel.refreshCalendar();
        }
        else
        {
            model.delete();
            MainPanel.getMainForm().changeMain(MainPanel.CALENDAR);
            MainPanel.refreshCalendar();
        }
    }

    protected void saveEvent()
    {
        model.setDate(calendar.getCalendar());
        Calendar startTimeCal = Calendar.getInstance();
        startTimeCal.setTime((java.sql.Time) startTime.getSelectedItem());
        model.setStartTime(startTimeCal);
        Calendar endTimeCal = Calendar.getInstance();
        endTimeCal.setTime((java.sql.Time) endTime.getSelectedItem());
        model.setEndTime(endTimeCal);
        model.setRoom(((Room) roomChooser.getSelectedItem()));
        model.setDescription(description.getText());
        model.cleanAttendees();

        for (Object person : selected_users_listModel.toArray())
        {
            model.addAttendee((Person) person);
        }

        if (model.isValidInput())
        {
            model.save();
            MainPanel.getMainForm().changeMain(MainPanel.CALENDAR);
            MainPanel.refreshCalendar();
        }
        else
        {
        	JOptionPane.showMessageDialog(meetingPanel, "Ugyldig input", "Ugyldig input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel getRoom_label()
    {
        if (roomLabel == null)
        {
            roomLabel = new JLabel();
            roomLabel.setText("Rom: ");
            roomLabel.setPreferredSize(new java.awt.Dimension(100, 0));
            roomLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        }
        return roomLabel;
    }

    private JComboBox getRoom_chooser()
    {
        if (roomChooser == null)
        {
            roomChooserModel = new DefaultComboBoxModel();
            roomChooser = new JComboBox();
            roomChooser.setModel(roomChooserModel);
            roomChooser.setEditable(false);
            roomChooser.setEnabled(false);
            roomChooser.setRenderer(new RoomRendrer());
            roomChooser.setPreferredSize(new Dimension(160, 20));
        }
        return roomChooser;
    }

    private JPanel getRoomChooserPanel()
    {
        if (room_chooser_panel == null)
        {
            room_chooser_panel = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.fill = GridBagConstraints.BOTH;
            room_chooser_panel.add(getRoom_chooser(), c);
            c.gridx = 1;
            room_chooser_panel.add(getRoom_button(), c);
        }
        return room_chooser_panel;
    }

    private JButton getRoom_button()
    {
        if (room_button == null)
        {
            room_button = new JButton();
            room_button.setText("");
            room_button.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
            room_button.setPreferredSize(new Dimension(20, 20));
            room_button.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    getRooms();
                }
            });
        }
        return room_button;
    }

    protected void getRooms()
    {
        model.setDate(calendar.getCalendar());
        int tt1 = (startTime.getSelectedIndex() + 1) * 3600 * 1000;
        Time t1 = new Time(tt1);
        
        int t = (endTime.getSelectedIndex() + 1) * 3600 * 1000;
        System.out.println(endTime.getSelectedIndex());
        Time t2 = new Time(t);
        System.out.println(t1.getTime());
        System.out.println(t2.getTime());
        List<Room> rooms = model.getRooms(calendar.getCalendar(), t1, t2);

        roomChooserModel.removeAllElements();

        for (Room room : rooms)
        {
            roomChooserModel.addElement(room);
        }
        roomChooser.setEnabled(true);
    }

    private JPanel getAll_users()
    {
        if (allUsersPanel == null)
        {
            allUsersPanel = new JPanel();
            GridBagLayout all_usersLayout = new GridBagLayout();
            all_usersLayout.rowWeights = new double[]
                    {
                        0.1, 0.1, 0.1, 0.1
                    };
            all_usersLayout.rowHeights = new int[]
                    {
                        7, 7, 7, 7
                    };
            all_usersLayout.columnWeights = new double[]
                    {
                        0.0, 0.0, 0.0
                    };
            all_usersLayout.columnWidths = new int[]
                    {
                        245, 50, 257
                    };
            allUsersPanel.setLayout(all_usersLayout);
            allUsersPanel.add(getButtons_select_unselect_panel(), new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
            allUsersPanel.add(getAll_users_label(), new GridBagConstraints(-1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            allUsersPanel.add(getSelected_users_scroll(), new GridBagConstraints(2, 1, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            allUsersPanel.add(getAllEmployeeScroll(), new GridBagConstraints(0, 1, 1, 3, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0));
            allUsersPanel.add(getSelected_users_label(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            allUsersPanel.setVisible(true);
        }
        return allUsersPanel;
    }

    private JLabel getAll_users_label()
    {
        if (all_users_label == null)
        {
            all_users_label = new JLabel();
            all_users_label.setText("Alle Brukere");
        }
        return all_users_label;
    }

    private JList getAll_users_list()
    {

        all_users_listModel = new PersonListModel();
        all_users_list = new JList();
        all_users_list.setCellRenderer(new AllEmployeesListRenderer());
        all_users_list.setModel(all_users_listModel);
        return all_users_list;

    }

    private JScrollPane getAllEmployeeScroll()
    {
        if (jScrollPane1 == null)
        {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 387));
            jScrollPane1.setViewportView(getAll_users_list());
            jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
        return jScrollPane1;
    }

    private JScrollPane getSelected_users_scroll()
    {
        if (selected_users_scroll == null)
        {
            selected_users_scroll = new JScrollPane();
            selected_users_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            selected_users_scroll.setViewportView(getSelected_users_list());
            selected_users_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
        return selected_users_scroll;
    }

    private JList getSelected_users_list()
    {
        if (selected_users_list == null)
        {
            selected_users_listModel = new PersonListModel();
            selected_users_list = new JList();
            selected_users_list.setCellRenderer(new AllEmployeesListRenderer());
            selected_users_list.setModel(selected_users_listModel);
        }
        return selected_users_list;
    }

    private JPanel getButtons_select_unselect_panel()
    {
        if (buttons_select_unselect_panel == null)
        {
            buttons_select_unselect_panel = new JPanel();
            GridBagLayout buttons_select_unselect_panelLayout = new GridBagLayout();
            buttons_select_unselect_panelLayout.rowWeights = new double[]
                    {
                        0.1, 0.1, 0.1, 0.1
                    };
            buttons_select_unselect_panelLayout.rowHeights = new int[]
                    {
                        7, 7, 7, 7
                    };
            buttons_select_unselect_panelLayout.columnWeights = new double[]
                    {
                        0.1
                    };
            buttons_select_unselect_panelLayout.columnWidths = new int[]
                    {
                        7
                    };
            buttons_select_unselect_panel.setLayout(buttons_select_unselect_panelLayout);
            buttons_select_unselect_panel.setPreferredSize(new java.awt.Dimension(60, 70));
            buttons_select_unselect_panel.add(getSelect(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            buttons_select_unselect_panel.add(getUnselect(), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        }
        return buttons_select_unselect_panel;
    }

    private JButton getSelect()
    {
        if (select == null)
        {
            select = new JButton();
            select.setIcon(new ImageIcon(getClass().getResource("/frontArrow.png")));
            select.setSize(40, 40);
            select.setPreferredSize(new java.awt.Dimension(40, 40));
            select.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    addSelectedEmployees();
                }
            });
        }
        return select;
    }

    protected void addSelectedEmployees()
    {

        for (Object person : all_users_list.getSelectedValues())
        {
            all_users_listModel.removeElement(person);
            selected_users_listModel.addElement(person);
        }
    }

    private JButton getUnselect()
    {
        if (unselect == null)
        {
            unselect = new JButton();
            unselect.setIcon(new ImageIcon(getClass().getResource("/backArrow.png")));
            unselect.setPreferredSize(new java.awt.Dimension(40, 40));
            unselect.setSize(40, 40);
            unselect.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    unselectEmployees();
                }
            });
        }
        return unselect;
    }

    protected void unselectEmployees()
    {
        for (Object person : selected_users_list.getSelectedValues())
        {
            if(((Person)person).getEmail().equals(Communication.LoggedInUserEmail))
                continue;
            selected_users_listModel.removeElement(person);
            all_users_listModel.addElement(person);
        }
    }

    private JLabel getSelected_users_label()
    {
        if (selected_users_label == null)
        {
            selected_users_label = new JLabel();
            selected_users_label.setText("Valgte brukere");
        }
        return selected_users_label;
    }

    private JLabel getNew_agreement_label()
    {
        if (newMeetingLabel == null)
        {
            newMeetingLabel = new JLabel();
            newMeetingLabel.setText("Legg til ny avtale");
        }
        return newMeetingLabel;
    }

    private JPanel getButtons_panel()
    {
        if (buttons_panel == null)
        {
            buttons_panel = new JPanel();
            GridBagLayout buttons_panelLayout = new GridBagLayout();
            buttons_panelLayout.rowWeights = new double[]
                    {
                        0.1
                    };
            buttons_panelLayout.rowHeights = new int[]
                    {
                        7
                    };
            buttons_panelLayout.columnWeights = new double[]
                    {
                        0.1, 0.1
                    };
            buttons_panelLayout.columnWidths = new int[]
                    {
                        7, 7
                    };
            buttons_panel.setLayout(buttons_panelLayout);
            buttons_panel.add(getSave_button(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        	buttons_panel.add(getDelete_button(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        }
        return buttons_panel;
    }

    public class TimeListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            roomChooserModel.removeAllElements();
            roomChooser.setEnabled(false);
        }
    }
}
