package Repository;

public interface IRepository<ID,T> {
    public void add(ID id, T elem);
    public void delete(ID id);
    public void update(ID id, T elem);
    public T findById(ID id);
    public Iterable<T> getAll();
}
