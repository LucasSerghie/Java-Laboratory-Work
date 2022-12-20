package JDBC;

import Domain.Doctor;
import Domain.Appointment;
import Repository.MemoryRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DoctorDatabaseRepository extends DatabaseRepository<Integer, Doctor>{

    public DoctorDatabaseRepository() {
        super();
        memoryRepository = new MemoryRepo<Integer, Doctor>();
        getData();
    }

    @Override
    public void createSchema() {
        final Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("" +
                    "create table if not exists Doctors(" +
                    "id integer primary key autoincrement, " +
                    "firstName varchar(256), " +
                    "lastname varchar(256), " +
                    "age int, cnp char(13), " +
                    "speciality varchar(256), " +
                    "grade int" +
                    ");"
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Doctor save(Integer integer, Doctor doctor) {
        if(memoryRepository.findById(integer) != null) {
            saveDoctor(doctor);
        } else {
            updateDoctor(doctor);
        } return null;
    }

    public Doctor updateDoctor(Doctor doctor) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update Doctors " +
                            "set Name=?, department=?, appointments=?" +
                            "where id=?"
            );

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getDepartment());
            statement.setArray(3, (Array) doctor.getAppointments());
            statement.setInt(4, doctor.getId());

            statement.executeUpdate();
            statement.close();
            return doctor;
        } catch (SQLException e) {
            return null;
        }
    }

    public Doctor saveDoctor(Doctor doctor) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into Doctors(id, Name, department, appointments) " +
                            "values (?, ?, ?, ?)"
            );
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getDepartment());
            statement.setArray(3, (Array) doctor.getAppointments());
            statement.setInt(4, doctor.getId());
            statement.executeUpdate();
            statement.close();
            return doctor;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Doctor delete(Doctor doctor) {
        memoryRepository.delete(doctor);
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from Doctors where id=?"
            );

            statement.setInt(1, doctor.getId());
            statement.executeUpdate();
            statement.close();
            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object findById(Doctor doctor) {
        return memoryRepository.findById(doctor);
    }

    public Collection<Doctor> getAll() {
        List<Doctor> doctorList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from Doctors"
            );

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("Name"),
                        resultSet.getString("department"),
                        (ArrayList<Appointment>) resultSet.getArray("appointments")
                );
                doctorList.add(doctor);
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorList;
    }

    public Doctor findById(Integer id) {
        return getDoctorMemoryRepository().findById(id);
    }

    protected MemoryRepo<Integer, Doctor> getDoctorMemoryRepository() {
        return (MemoryRepo) memoryRepository;
    }

}