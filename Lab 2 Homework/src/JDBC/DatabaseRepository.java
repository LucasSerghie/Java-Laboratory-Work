package JDBC;

import Repository.MemoryRepo;
import Domain.Identifiable;
import Repository.IRepository;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public abstract class DatabaseRepository<ID, T>{

    protected Connection connection = null;

    protected MemoryRepo memoryRepository;

    public DatabaseRepository() {
        openConnection();
        createSchema();
    }

    protected void getData() {
        Map<ID, T> dataList = (Map<ID, T>) memoryRepository.getAll();
        dataList.forEach((ID, T) -> this.memoryRepository.add(ID, T));
    }

    public void openConnection() {
        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            String JDBC_URL = "jdbc:sqlite:data/test.db";
            dataSource.setUrl(JDBC_URL);
            if(connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    protected abstract void createSchema();
}
