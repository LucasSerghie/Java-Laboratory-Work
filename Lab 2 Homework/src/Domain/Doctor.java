package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor implements Identifiable<Integer>, Serializable {


    private final Integer id;
    private String name;
    private String department;
    private ArrayList<Appointment> appointments;

    public Doctor(int id, String name, String department, ArrayList<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        StringBuilder string;
        string = new StringBuilder();
        string.append("*doctor id* ").append(this.id).append("   Appointments for doctor: ")
              .append(this.name).append(" from department: ").append(this.department).append(":\n");
        for(Appointment value : this.appointments)
            string.append(value.toString());
        string.append("\n");
        return string.toString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {return department;}

    public ArrayList<Appointment> getAppointments() {return appointments;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (getId() != doctor.getId()) return false;
        if (getName() != null ? !getName().equals(doctor.getName()) : doctor.getName() != null) return false;
        if (getDepartment() != null ? !getDepartment().equals(doctor.getDepartment()) : doctor.getDepartment() != null) return false;
        return getAppointments() != null ? getAppointments().equals(doctor.getAppointments()) : doctor.getAppointments() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDepartment() != null ? getDepartment().hashCode() : 0);
        result = 31 * result + (getAppointments() != null ? getAppointments().hashCode() : 0);
        return result;
    }

}
