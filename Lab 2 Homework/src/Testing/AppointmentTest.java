package Testing;

import static org.junit.Assert.*;

import java.util.Date;

import Domain.Appointment;
import org.junit.Test;

public class AppointmentTest {

    @Test
    public void testConstructor() {
        Date date = new Date();
        Appointment appointment = new Appointment(1, "John Smith", date, 30);
        assertEquals(1, appointment.getId().intValue());
        assertEquals("John Smith", appointment.getPatient());
        assertEquals(date, appointment.getDate());
        assertEquals(30, appointment.getDuration());
    }

    @Test
    public void testSetGetPatient() {
        Date date = new Date();
        Appointment appointment = new Appointment(1, "John Smith", date, 30);
        appointment.setPatient("Jane Doe");
        assertEquals("Jane Doe", appointment.getPatient());
    }

    @Test
    public void testSetGetDate() {
        Date date = new Date();
        Appointment appointment = new Appointment(1, "John Smith", date, 30);
        Date newDate = new Date();
        appointment.setDate(newDate);
        assertEquals(newDate, appointment.getDate());
    }

    @Test
    public void testSetGetDuration() {
        Date date = new Date();
        Appointment appointment = new Appointment(1, "John Smith", date, 30);
        appointment.setDuration(60);
        assertEquals(60, appointment.getDuration());
    }

    @Test
    public void testEquals() {
        Date date = new Date();
        Appointment appointment1 = new Appointment(1, "John Smith", date, 30);
        Appointment appointment2 = new Appointment(2, "Jane Doe", date, 60);
        assertTrue(appointment1.equals(appointment2));
        assertNotEquals(appointment1, new Object());
        appointment2.setDate(new Date());
        assertFalse(appointment1.equals(appointment2));
    }


    @Test
    public void testCompareTo() {
        Date date1 = new Date();
        Date date2 = new Date();
        date2.setTime(date2.getTime() + 1000);
        Appointment appointment1 = new Appointment(1, "John Smith", date1, 30);
        Appointment appointment2 = new Appointment(2, "Jane Doe", date2, 60);
        assertEquals(-1, appointment1.compareTo(appointment2));
        assertEquals(1, appointment2.compareTo(appointment1));
        assertNotEquals(0, appointment1.compareTo(appointment2));
    }
}