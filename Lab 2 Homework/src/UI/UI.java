package UI;

import Domain.Appointment;
import Service.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UI {
    private final Service appointmentService;

    public UI(Service appointmentService){this.appointmentService = appointmentService;}

    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter department: ");
        String department = scanner.next();
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            appointmentService.addDoctor(doctorId, name, department, appointments);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void addAppointment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter appointment id: ");
        Integer id = scanner.nextInt();
        System.out.println("Enter appointment name: ");
        String name = scanner.next();
        System.out.println("Enter date of appointment \nDay: ");
        int day = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Hour: ");
        int hour = scanner.nextInt();
        System.out.println("Minutes: ");
        int minutes = scanner.nextInt();
        System.out.println("Enter appointment duration: ");
        int duration = scanner.nextInt();
        Date date = new Date(year-1900, month, day, hour, minutes);
        try {
            appointmentService.addAppointment(doctorId, id, name, date, duration);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void deleteDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        try{
            appointmentService.deleteDoctor(doctorId);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void deleteAppointment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter appointment id: ");
        Integer id = scanner.nextInt();
        try{
            appointmentService.deleteAppointment(doctorId, id);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void updateDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter department: ");
        String department = scanner.next();
        try {
            appointmentService.updateDoctor(doctorId, name, department);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void updateAppointment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter appointment id: ");
        Integer id = scanner.nextInt();
        System.out.println("Enter appointment name: ");
        String name = scanner.next();
        System.out.println("Enter exact time of the appointment \nDay: ");
        int day = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Hour: ");
        int hour = scanner.nextInt();
        System.out.println("Minutes: ");
        int minutes = scanner.nextInt();
        System.out.println("Enter appointment duration: ");
        int duration = scanner.nextInt();
        Date date = new Date(year-1900, month, day, hour, minutes);
        try {
            appointmentService.updateAppointment(doctorId, id, name, date,  duration);
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void findDoctorById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        try{
            System.out.println(appointmentService.findDoctorById(doctorId));
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void findAppointmentById(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter doctor id: ");
        Integer doctorId = scanner.nextInt();
        System.out.println("Enter id: ");
        Integer id = scanner.nextInt();
        try{
            System.out.println(appointmentService.findAppointmentById(doctorId, id));
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void printAllDoctors() {
        try{
            System.out.println(appointmentService.getAllDoctors());
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void printAllAppointments(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter doctor id: ");
            Integer doctorId = scanner.nextInt();
            System.out.println(appointmentService.getAllAppointments(doctorId));
        }catch (RuntimeException exe)
        {
            System.out.println(exe.getMessage());
        }
    }

    public void menu(){
        System.out.println("""
                    \s
                    1. Add doctor\s
                    2. Add appointment for a doctor\s
                    3. Delete doctor\s
                    4. Delete appointment from a doctor\s
                    5. Update doctor details\s
                    6. Update appointment from a doctor\s
                    7. Find a specific doctor\s
                    8. Find a specific appointment from a doctor\s
                    9. View all doctors\s
                    10. View all appointments from a doctor\s
                    0. Exit application\s
                    Enter command...""");
    }

    public void command() {
        int command = 1;
        menu();
        Scanner scanner = new Scanner(System.in);
        while (command != 0) {
            command = scanner.nextInt();
            if (command == 1)
                addDoctor();
            else if (command == 2) {
                addAppointment();
            } else if (command == 3) {
                deleteDoctor();
            } else if (command == 4) {
                deleteAppointment();
            } else if (command == 5) {
                updateDoctor();
            } else if (command == 6) {
                updateAppointment();
            } else if (command == 7) {
                findDoctorById();
            } else if (command == 8) {
                findAppointmentById();
            } else if (command == 9) {
                printAllDoctors();
            } else if (command == 10) {
                printAllAppointments();
            }
            menu();
        }
        System.out.println("Application closed.");
    }

}
