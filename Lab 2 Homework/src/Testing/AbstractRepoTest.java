package Testing;

import static org.junit.Assert.*;

import Repository.AbstractRepo;
import org.junit.Before;
import org.junit.Test;


public class AbstractRepoTest {
    private AbstractRepo<Integer, String> repo;

    @Before
    public void setUp() {
        repo = new AbstractRepo<>();
    }

    @Test
    public void testAdd() {
        repo.add(1, "test");
        assertEquals("test", repo.findById(1));
    }

    @Test
    public void testAddDuplicate() {
        repo.add(1, "test");
        try {
            repo.add(1, "test2");
            fail();
        } catch (RuntimeException e) {
            assertEquals("Element already made!", e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        repo.add(1, "test");
        repo.delete(1);
        assertNull(repo.findById(1));
    }

    @Test
    public void testDeleteNonExistent() {
        try {
            repo.delete(1);
            fail();
        } catch (RuntimeException e) {
            assertEquals("This element does not exist!", e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        repo.add(1, "test");
        repo.update(1, "test2");
        assertEquals("test2", repo.findById(1));
    }

    @Test
    public void testUpdateNonExistent() {
        try {
            repo.update(1, "test");
            fail();
        } catch (RuntimeException e) {
            assertEquals("This element does not exist!", e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        repo.add(1, "test");
        assertEquals("test", repo.findById(1));
    }

    @Test
    public void testFindByIdNonExistent() {
        assertNull(repo.findById(1));
    }

    @Test
    public void testToString() {
        repo.add(1, "test1");
        repo.add(2, "test2");
        assertEquals("test1test2", repo.toString());
    }
}