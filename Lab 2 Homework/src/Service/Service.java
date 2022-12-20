package Service;

import Domain.Appointment;
import Domain.Doctor;
import Repository.MemoryRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Service {
    private final MemoryRepo<Integer, Doctor> repo;

    public Service(MemoryRepo<Integer, Doctor> repo){this.repo = repo;}

    public void addDoctor(Integer id, String name, String domain, ArrayList<Appointment> appointments){
        Doctor a = new Doctor(id, name, domain, appointments);
        repo.add(id, a);
    }

    public void addAppointment(Integer doctorId, Integer id, String name, Date date, int duration) {
        repo.findById(doctorId).getAppointments().add(new Appointment(id, name, date, duration));
    }

    public void deleteDoctor(Integer id){repo.delete(id);}

    public void deleteAppointment(Integer doctorId, Integer id) {
        repo.findById(doctorId).getAppointments().remove(findAppointmentById(doctorId, id));
    }

    public void updateDoctor(Integer id, String name, String domain){
        Doctor a = new Doctor(id, name, domain, findDoctorById(id).getAppointments());
        repo.update(id, a);
    }

    public void updateAppointment(Integer doctorId, Integer id, String name, Date date, int duration) {
        repo.findById(doctorId).getAppointments().set(id, new Appointment(id, name, date, duration));
    }

    public Doctor findDoctorById(Integer id){return repo.findById(id);}

    public Appointment findAppointmentById(Integer doctorId, Integer id) {
        return repo.findById(doctorId).getAppointments().get(id);
    }

    public String getAllDoctors() {return repo.toString();}

    public Iterable<Appointment> getAllAppointments(Integer id) {return repo.findById(id).getAppointments();}

}
