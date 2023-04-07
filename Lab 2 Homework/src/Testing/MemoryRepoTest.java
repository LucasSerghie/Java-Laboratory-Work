package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import Domain.Appointment;
import Domain.Doctor;
import Repository.MemoryRepo;
import org.junit.Before;
import org.junit.Test;

public class MemoryRepoTest {
    private MemoryRepo<Integer, Doctor> repo;

    @Before
    public void setup() {
        repo = new MemoryRepo<>();
    }

    @Test
    public void testAddElements() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor 1", "Department 1", appointments);
        Doctor doctor2 = new Doctor(2, "Doctor 2", "Department 2", appointments);
        Doctor doctor3 = new Doctor(3, "Doctor 3", "Department 3", appointments);
        Doctor doctor4 = new Doctor(4, "Doctor 4", "Department 4", appointments);
        Doctor doctor5 = new Doctor(5, "Doctor 5", "Department 5", appointments);
        repo.add(doctor1.getId(), doctor1);
        repo.add(doctor2.getId(), doctor2);
        repo.add(doctor3.getId(), doctor3);
        repo.add(doctor4.getId(), doctor4);
        repo.add(doctor5.getId(), doctor5);
        assertEquals(5, repo.getAll().spliterator().estimateSize());
    }

    @Test
    public void testPrintElements() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor 1", "Department 1", appointments);
        Doctor doctor2 = new Doctor(2, "Doctor 2", "Department 2", appointments);
        Doctor doctor3 = new Doctor(3, "Doctor 3", "Department 3", appointments);
        Doctor doctor4 = new Doctor(4, "Doctor 4", "Department 4", appointments);
        Doctor doctor5 = new Doctor(5, "Doctor 5", "Department 5", appointments);
        repo.add(doctor1.getId(), doctor1);
        repo.add(doctor2.getId(), doctor2);
        repo.add(doctor3.getId(), doctor3);
        repo.add(doctor4.getId(), doctor4);
        repo.add(doctor5.getId(), doctor5);

        String expectedOutput = """
                *doctor id* 1   Appointments for doctor: Doctor 1 from department: Department 1:

                *doctor id* 2   Appointments for doctor: Doctor 2 from department: Department 2:

                *doctor id* 3   Appointments for doctor: Doctor 3 from department: Department 3:

                *doctor id* 4   Appointments for doctor: Doctor 4 from department: Department 4:

                *doctor id* 5   Appointments for doctor: Doctor 5 from department: Department 5:

                """;
        assertEquals(expectedOutput, repo.toString());
    }

    @Test
    public void testSearchElement() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor 1", "Department 1", appointments);
        Doctor doctor2 = new Doctor(2, "Doctor 2", "Department 2", appointments);
        Doctor doctor3 = new Doctor(3, "Doctor 3", "Department 3", appointments);
        Doctor doctor4 = new Doctor(4, "Doctor 4", "Department 4", appointments);
        Doctor doctor5 = new Doctor(5, "Doctor 5", "Department 5", appointments);
        repo.add(doctor1.getId(), doctor1);
        repo.add(doctor2.getId(), doctor2);
        repo.add(doctor3.getId(), doctor3);
        repo.add(doctor4.getId(), doctor4);
        repo.add(doctor5.getId(), doctor5);

        assertEquals(doctor3, repo.findById(3));
    }

    @Test
    public void testDeleteElement() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor 1", "Department 1", appointments);
        Doctor doctor2 = new Doctor(2, "Doctor 2", "Department 2", appointments);
        Doctor doctor3 = new Doctor(3, "Doctor 3", "Department 3", appointments);
        Doctor doctor4 = new Doctor(4, "Doctor 4", "Department 4", appointments);
        Doctor doctor5 = new Doctor(5, "Doctor 5", "Department 5", appointments);
        repo.add(doctor1.getId(), doctor1);
        repo.add(doctor2.getId(), doctor2);
        repo.add(doctor3.getId(), doctor3);
        repo.add(doctor4.getId(), doctor4);
        repo.add(doctor5.getId(), doctor5);

        repo.delete(2);
        assertEquals(4, repo.getAll().spliterator().estimateSize());
        assertNull(repo.findById(2));
    }

    @Test
    public void testUpdateElement() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor 1", "Department 1", appointments);
        Doctor doctor2 = new Doctor(2, "Doctor 2", "Department 2", appointments);
        Doctor doctor3 = new Doctor(3, "Doctor 3", "Department 3", appointments);
        Doctor doctor4 = new Doctor(4, "Doctor 4", "Department 4", appointments);
        Doctor doctor5 = new Doctor(5, "Doctor 5", "Department 5", appointments);
        repo.add(doctor1.getId(), doctor1);
        repo.add(doctor2.getId(), doctor2);
        repo.add(doctor3.getId(), doctor3);
        repo.add(doctor4.getId(), doctor4);
        repo.add(doctor5.getId(), doctor5);

        Doctor updatedDoctor = new Doctor(2, "Doctor 2 Updated", "Department 2 Updated", appointments);
        repo.update(2, updatedDoctor);
        assertEquals(updatedDoctor, repo.findById(2));
    }
}