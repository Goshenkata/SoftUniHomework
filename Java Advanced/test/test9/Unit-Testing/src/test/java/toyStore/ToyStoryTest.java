package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.beans.Transient;

public class ToyStoryTest {
    @Test
    public void testToy() {
        Toy toy = new Toy("a", "b");
        Assert.assertEquals(toy.getManufacturer(), "a");
        Assert.assertEquals(toy.getToyId(), "b");
    }
    @Test
    public void addToy() throws OperationNotSupportedException {
        Main.main(null);
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("A", toy);
        Assert.assertEquals(toyStore.getToyShelf().get("A").getManufacturer(), "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void noShelf() throws OperationNotSupportedException {
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("Q", toy);
    }
    @Test(expected = IllegalArgumentException.class)
    public void takenShelf() throws OperationNotSupportedException {
        Toy toy = new Toy("a", "b");
        Toy toy2 = new Toy("c", "d");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("A", toy);
        toyStore.addToy("A", toy2);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void toyExi() throws OperationNotSupportedException {
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("A", toy);
        toyStore.addToy("B", toy);
    }
    @Test
    public void toyPresent() throws OperationNotSupportedException {
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("A", toy);
        Assert.assertEquals(toyStore.getToyShelf().get("A").getManufacturer(), "a");
        toyStore.removeToy("A", toy);
        Assert.assertNull(toyStore.getToyShelf().get("A"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeNoShelf() throws OperationNotSupportedException {
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.addToy("A", toy);
        toyStore.removeToy("Q", toy);
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeNoToy() {
        Toy toy = new Toy("a", "b");
        ToyStore toyStore = new ToyStore();
        toyStore.removeToy("A", toy);
    }
}