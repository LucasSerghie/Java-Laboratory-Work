package Repository;

import Domain.Appointment;
import Domain.Doctor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreamReports extends AbstractRepo<Integer, Doctor> {

    public List<Appointment> getAppointmentsForDoctor(String doctorName) {
        return repo.values().stream()
                .filter(doctor -> doctor.getName().equals(doctorName))
                .flatMap(doctor -> doctor.getAppointments().stream())
                .collect(Collectors.toList());
    }

    public List<String> getNamesForDoctorBookings(String doctorName) {
        return repo.values().stream()
                .filter(doctor -> doctor.getName().equals(doctorName))
                .flatMap(doctor -> doctor.getAppointments().stream())
                .map(Appointment::getPatient)
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsForPerson(String personName) {
        return repo.values().stream()
                .flatMap(doctor -> doctor.getAppointments().stream())
                .filter(appointment -> appointment.getPatient().equals(personName))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsForDay(Date day) {
        return repo.values().stream()
                .flatMap(doctor -> doctor.getAppointments().stream())
                .filter(appointment -> appointment.getDate().equals(day))
                .collect(Collectors.toList());
    }

    public List<Date> getAppointmentDaysForPatient(String patientName) {
        return repo.values().stream()
                .flatMap(doctor -> doctor.getAppointments().stream())
                .filter(appointment -> appointment.getPatient().equals(patientName))
                .map(Appointment::getDate)
                .collect(Collectors.toList());
    }

}
