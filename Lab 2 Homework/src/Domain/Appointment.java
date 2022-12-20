package Domain;

import java.io.Serializable;
import java.util.*;

public class Appointment implements Identifiable<Integer>, Comparable<Appointment>, Serializable {

    private final Integer id;
    private String name;
    private Date date;
    private int duration;

    public Appointment(Integer id, String name, Date date, int duration) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Id:   " + id +
                ";    Name:  '" + name + '\'' +
                ";    Date:  " + date +
                ";    Duration:  " + duration + "\n";
    }

    public String getPatient() {
        return name;
    }

    public void setPatient(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public boolean equals(Appointment o) {
        return this.getDate() == o.getDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (getDuration() != that.getDuration()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!Objects.equals(name, that.name)) return false;
        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + getDuration();
        return result;
    }

    @Override
    public int compareTo(Appointment o) {
        return this.date.compareTo(o.date);
    }

}