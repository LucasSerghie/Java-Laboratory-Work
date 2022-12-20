package Repository;


import java.util.HashMap;
import java.util.Map;

public class AbstractRepo<ID,T> implements IRepository<ID,T>{

    protected final Map<ID,T> repo;

    public AbstractRepo() {this.repo = new HashMap<>();}

    public AbstractRepo(Map<ID, T> repo) {this.repo = repo;}

    @Override
    public void add(ID id, T elem) {
        if(repo.containsKey(id))
            throw new RuntimeException("Element already made!");
        repo.putIfAbsent(id,elem);
    }

    @Override
    public void delete(ID id) {
        if(!repo.containsKey(id))
            throw new RuntimeException("This element does not exist!");
        repo.remove(id);
    }

    @Override
    public void update(ID id, T elem) {
        if(!repo.containsKey(id))
            throw new RuntimeException("This element does not exist!");
        repo.replace(id, elem);
    }

    @Override
    public T findById(ID id) {
        return repo.get(id);
    }

    @Override
    public Iterable<T> getAll() {return repo.values();}

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        repo.forEach((i, j) -> string.append(j));
        return string.toString();
    }

}