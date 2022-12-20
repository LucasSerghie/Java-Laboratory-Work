package Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class FileRepository<ID, T> extends AbstractRepo<ID, T>{

    private final String fileName;
    private ArrayList<T> entities;

    public FileRepository(Map<ID, T> repo, String fileName) {
        super(repo);
        this.fileName = fileName;
        this.entities = new ArrayList<>();
    }

    public void loadData() {
        this.entities = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.fileName))) {
            ArrayList<T> entities = (ArrayList<T>) objectInputStream.readObject();
            this.entities.addAll(entities);
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void writeToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            Iterable<T> all = getAll();
            ArrayList<T> list = new ArrayList<>();
            all.forEach(list::add);
            objectOutputStream.writeObject(list);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}