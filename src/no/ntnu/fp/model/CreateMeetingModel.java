package no.ntnu.fp.model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.activity.InvalidActivityException;

import no.ntnu.fp.model.Event.Type;

public final class CreateMeetingModel
{

    private boolean newEvent;
    private boolean timeIsSet;
    private Event event;
    private Person responsiblePerson;
    private List<Person> atendees;
    private List<Person> emps;

    public CreateMeetingModel(Calendar date, Calendar startTime, Calendar endTime)
    {
        this(new Event());
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        newEvent = true;
        timeIsSet = true;

        for (Person p : Communication.getEmployees())
        {
            if (p.getEmail().equals(Communication.LoggedInUserEmail))
            {
                responsiblePerson = p;
                break;
            }
        }
    }

    public CreateMeetingModel(Event event)
    {
        this.event = event;

        for (Person p : Communication.getEmployees())
        {
            if (p.getEmail().equals(event.getResponsible()))
            {
                responsiblePerson = p;
                break;
            }
        }
        newEvent = false;
        timeIsSet = true;
    }

    public CreateMeetingModel()
    {
        this(new Event());
        newEvent = true;
        timeIsSet = false;

        for (Person p : Communication.getEmployees())
        {
            if (p.getEmail().equals(Communication.LoggedInUserEmail))
            {
                responsiblePerson = p;
                break;
            }
        }
    }

    public Calendar getDate()
    {
        return event.getDate();
    }

    public boolean isNew()
    {
        return newEvent;
    }

    public void setDate(Calendar calendar)
    {
        event.setDate(calendar);
    }

    public Calendar getStartTime()
    {
        return event.getStartTime();
    }

    public void setStartTime(Calendar startTime)
    {
        event.setStartTime(startTime);
    }

    public Calendar getEndTime()
    {
        return event.getEndTime();
    }

    public void setEndTime(Calendar endTime)
    {
        event.setEndTime(endTime);
    }

    public Room getRoom()
    {
        System.out.println("getRooms() <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<<");

        return event.getRoomObject();
    }

    public void setRoom(Room room)
    {
        if (room == null)
        {
            event.setRoom("");
        }
        else
        {
            event.setRoom(room.toString());
        }
    }

    public String getDescription()
    {
        return event.getDescription();
    }

    public void setDescription(String description)
    {
        event.setDescription(description);
    }

    public void setDefaultValues()
    {
        if (newEvent)
        {
            if (!timeIsSet)
            {
                setDate(Calendar.getInstance());
                Calendar startTime = Calendar.getInstance();
                Calendar endTime = Calendar.getInstance();
                startTime.setTime(new Time(10, 0, 0));
                endTime.setTime(new Time(11, 0, 0));
                setStartTime(startTime);
                setEndTime(endTime);
            }
            event.setResponsible(Communication.LoggedInUserEmail);
        }
        else
        {
            setDate(event.getDate());
            setStartTime(event.getStartTime());
            setEndTime(event.getEndTime());
            setRoom(event.getRoomObject());
            setDescription(event.getDescription());
        }
    }

    public Time[] getTimes()
    {
        Time[] comboChoose = new Time[24];
        for (int i = 0; i < 24; i++)
        {
            comboChoose[i] = new Time(i * 3600 * 1000);
        }
        return comboChoose;
    }

    public List<Room> getRooms()
    {
        List<Room> rooms = Communication.getFreeRooms(
            new Reservation(new Date(getDate().getTimeInMillis()), new Time(getStartTime().getTimeInMillis()), new Time(getEndTime().getTimeInMillis())),
            event.getEid());

        return rooms;
    }

    public List<Room> getRooms(Calendar date, Time startTime, Time endTime)
    {
    	List<Room> rooms = Communication.getFreeRooms(
                new Reservation(new Date(date.getTimeInMillis()), startTime, endTime),
                event.getEid());

        return rooms;
    }
    
    
    public List<Person> getAttendees()
    {
        if (atendees == null)
        {
            List<Person> att = new ArrayList<Person>();
            getAllUsers();
            String status;
            for (final Person p : emps)
            {
                if (event.attendees.contains(p.getEmail()) || responsiblePerson.getEmail().equals(p.getEmail()))
                {
                    status = Communication.getStatus(event.getEid(), p.getEmail());
                    p.setStatus(status);
                    att.add(p);
                }
            }

            atendees = att;
        }
        return atendees;
    }

    public void getAllUsers()
    {
        if (emps == null)
        {
            emps = Communication.getEmployees();
        }
    }

    public List<Person> getAvailUsers()
    {
        getAllUsers();
        List<Person> attendees = getAttendees();
        List<Person> employees = new ArrayList<Person>(emps);

        for (Person person : attendees)
        {
            if (person == null)
            {
                continue;
            }

            employees.remove(person);
        }

        for (Person person : employees)
        {
            person.setStatus("");
        }


        return employees;

    }

    public void cleanAttendees()
    {
        event.attendees.clear();
    }

    public void addAttendee(Person person)
    {
        event.attendees.add(person.getEmail());
    }

    public boolean isValidInput()
    {
        if (getEndTime().before(getStartTime()) || getEndTime() == getStartTime())
        {
            return false;
        }

        return true;
    }

    public void save()
    {
        if (event.getAttendees().size() == 1)
        {
            event.setType(Type.Appointment);
        }
        else
        {
            event.setType(Type.Meeting);
        }

        if (newEvent)
        {
            Communication.saveEvent(event);
        }
        else
        {
            Communication.updateEvent(event);
        }
    }

    public void delete()
    {
        Communication.deleteEvent(event.getEid());
    }

    public boolean isEditable()
    {
        return event.getResponsible().equals(Communication.LoggedInUserEmail);
    }

    public String getResponsible()
    {
        return event.getResponsible();
    }

    public void messageMeOff() throws InvalidActivityException
    {
        if (!isEditable())
        {
            Communication.messageMeOff(event.getEid(), Communication.LoggedInUserEmail);
        }
        else
        {
            throw new InvalidActivityException("Can't message off the event if you own it.");
        }
    }

    public boolean isAtendee()
    {
        for (Person p : getAttendees())
        {
            if (p.getEmail().equals(Communication.LoggedInUserEmail))
            {
                return true;
            }
        }
        return false;
    }
}
