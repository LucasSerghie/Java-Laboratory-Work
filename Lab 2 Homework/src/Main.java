import Domain.Appointment;
import Domain.Doctor;
import JDBC.DatabaseRepository;
import JDBC.DoctorDatabaseRepository;
import Repository.FileRepository;
import Repository.MemoryRepo;
import Service.Service;
import UI.UI;

import java.util.*;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        MemoryRepo<Integer, Doctor> repo = new MemoryRepo<>();
        Service service = new Service(repo);
        addInitialValues(service);
        UI ui = new UI(service);
        ui.command();

        DoctorDatabaseRepository db_example = new DoctorDatabaseRepository();
        db_example.openConnection();
        db_example.createSchema();

        Doctor d = new Doctor(9, "Jordan Peter","Dentist", new ArrayList<Appointment>());
        db_example.save(9, d);

        db_example.updateDoctor(new Doctor(9, "Margus Peter","Orthodontist", new ArrayList<Appointment>()));

        ArrayList<Doctor> doctorArrayList = (ArrayList<Doctor>) db_example.getAll();
        for (Doctor doctor: doctorArrayList)
            System.out.println(doctor);

        db_example.closeConnection();

        FileRepository fileRepository = new FileRepository()
    }
    public static void addInitialValues(Service service) {
        Appointment a5 = new Appointment(5,"Matei", new Date(122, Calendar.MAY, 2, 16, 10), 10);

        service.addDoctor(1, "Dr. Pop Andrei", "Orthodontist", new ArrayList<Appointment>());
        service.addAppointment(1, 1,"Marcel", new Date(122, Calendar.JUNE, 22, 12, 0), 60);
        service.addAppointment(1, 2,"Carla", new Date(122, Calendar.JUNE, 20, 10, 30), 20);

        service.addDoctor(2, "Dr. Miller Carina", "Pediatric Dentistry", new ArrayList<Appointment>());
        service.addAppointment(2, 3,"Marc", new Date(122, Calendar.JANUARY, 21, 10, 0), 30);
        service.addAppointment(2, 4,"Cristina", new Date(122, Calendar.DECEMBER, 12, 15, 45), 20);

        Predicate<ArrayList<Appointment>> checkInitialAppointments = ArrayList::isEmpty;

        ArrayList<Appointment> list = new ArrayList<>();
        if(checkInitialAppointments.test(list))
            service.addDoctor(3, "Dr. Miller Carina", "Pediatric Dentistry", list);
        list.add(new Appointment(5,"Matei", new Date(122, Calendar.MAY, 2, 16, 10), 10));
        if(checkInitialAppointments.test(list))
            service.addDoctor(4, "Dr. Miller Carina", "Pediatric Dentistry", list);
    }
}
