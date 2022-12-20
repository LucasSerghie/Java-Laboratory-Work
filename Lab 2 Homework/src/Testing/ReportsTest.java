package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Domain.Doctor;
import Repository.StreamReports;
import Domain.Appointment;
import org.junit.Before;
import org.junit.Test;

public class ReportsTest {
    private StreamReports reports;

    @Before
    public void setup() {
        reports = new StreamReports();

        // Add some doctors and appointments
        ArrayList<Appointment> ar1 = new ArrayList<>();
        ar1.add(new Appointment(1, "Patient 1", new Date(122, Calendar.MAY, 2, 16, 10), 60));
        ar1.add(new Appointment(2, "Patient 2", new Date(122, Calendar.MAY, 2, 16, 10), 60));

        ArrayList<Appointment> ar2 = new ArrayList<>();
        ar2.add(new Appointment(3, "Patient 1", new Date(), 60));
        ar2.add(new Appointment(4, "Patient 3", new Date(), 60));

        reports.add(1, new Doctor(1, "Doctor 1", "General", ar1));
        reports.add(2, new Doctor(2, "Doctor 2", "Dentist", ar2));
    }

    @Test
    public void testGetAppointmentsForDoctor() {
        List<Appointment> appointments = reports.getAppointmentsForDoctor("Doctor 1");
        assertEquals(2, appointments.size());
        assertEquals("Patient 1", appointments.get(0).getPatient());
        assertEquals("Patient 2", appointments.get(1).getPatient());
    }

    @Test
    public void testGetAppointmentsForDay() {
        List<Appointment> appointments = reports.getAppointmentsForDay(new Date(122, Calendar.MAY, 2, 16, 10));
        assertEquals(2, appointments.size());
        assertEquals("Patient 1", appointments.get(0).getPatient());
        assertEquals("Patient 2", appointments.get(1).getPatient());
    }

    @Test
    public void testGetNamesForDoctorBookings() {
        List<String> names = reports.getNamesForDoctorBookings("Doctor 1");
        assertEquals(2, names.size());
        assertTrue(names.contains("Patient 1"));
        assertTrue(names.contains("Patient 2"));

        names = reports.getNamesForDoctorBookings("Doctor 2");
        assertEquals(2, names.size());
        assertTrue(names.contains("Patient 1"));
        assertTrue(names.contains("Patient 3"));

        names = reports.getNamesForDoctorBookings("Doctor 3");
        assertEquals(0, names.size());
    }

}
