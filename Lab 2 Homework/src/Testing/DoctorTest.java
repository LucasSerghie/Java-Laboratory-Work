package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import Domain.Appointment;
import Domain.Doctor;
import org.junit.Test;

public class DoctorTest {

    @Test
    public void testConstructor() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1, "John Smith", new Date(), 30));
        Doctor doctor = new Doctor(1, "Dr. Smith", "Surgery", appointments);
        assertEquals(1, doctor.getId().intValue());
        assertEquals("Dr. Smith", doctor.getName());
        assertEquals("Surgery", doctor.getDepartment());
        assertEquals(appointments, doctor.getAppointments());
    }

    @Test
    public void testSetGetName() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor = new Doctor(1, "Dr. Smith", "Surgery", appointments);
        doctor.setName("Dr. Doe");
        assertEquals("Dr. Doe", doctor.getName());
    }

    @Test
    public void testSetGetDepartment() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor = new Doctor(1, "Dr. Smith", "Surgery", appointments);
        doctor.setDepartment("Pediatrics");
        assertEquals("Pediatrics", doctor.getDepartment());
    }

    @Test
    public void testSetGetAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor = new Doctor(1, "Dr. Smith", "Surgery", appointments);
        ArrayList<Appointment> newAppointments = new ArrayList<>();
        newAppointments.add(new Appointment(2, "Jane Doe", new Date(), 60));
        doctor.setAppointments(newAppointments);
        assertEquals(newAppointments, doctor.getAppointments());
    }

}
