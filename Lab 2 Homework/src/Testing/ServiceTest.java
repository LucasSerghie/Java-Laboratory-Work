package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import Domain.Doctor;
import Service.Service;
import org.junit.Before;
import org.junit.Test;

import Repository.MemoryRepo;

public class ServiceTest {
    private MemoryRepo<Integer, Doctor> repo;
    private Service service;

    @Before
    public void setUp() {
        repo = new MemoryRepo<>();
        service = new Service(repo);
    }

    @Test
    public void testAddDoctor() {
        service.addDoctor(1, "Doctor 1", "General", new ArrayList<>());
        assertEquals("Doctor 1", service.findDoctorById(1).getName());
    }

    @Test
    public void testDeleteDoctor() {
        service.addDoctor(1, "Doctor 1", "General", new ArrayList<>());
        service.deleteDoctor(1);
        assertNull(service.findDoctorById(1));
    }

    @Test
    public void testUpdateDoctor() {
        service.addDoctor(1, "Doctor 1", "General", new ArrayList<>());
        service.updateDoctor(1, "Doctor 2", "General");
        assertEquals("Doctor 2", service.findDoctorById(1).getName());
    }

    @Test
    public void testFindDoctorById() {
        service.addDoctor(1, "Doctor 1", "General", new ArrayList<>());
        assertEquals("Doctor 1", service.findDoctorById(1).getName());
    }

    @Test
    public void testFindDoctorByIdNonExistent() {
        assertNull(service.findDoctorById(1));
    }

}