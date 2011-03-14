package no.ntnu.fp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Room {
	private String name;
	private int size;
	
	private PropertyChangeSupport propChangeSupp;
	
	public final static String PROPERTY_NAME = "name";
	public final static String PROPERTY_SIZE = "size";
	
	public Room(String n, int s)
	{
		name=n;
		size=s;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		String old = this.name;
		this.name = name;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROPERTY_NAME, old, name);
		propChangeSupp.firePropertyChange(event);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		int old = this.size;
		this.size = size;
		PropertyChangeEvent event = new PropertyChangeEvent(this, PROPERTY_SIZE, old, size);
		propChangeSupp.firePropertyChange(event);
	}
	
	public boolean hasReservation(Date date, Date startTime, Date endTime)
	{
		throw new NotImplementedException();
	}
}
