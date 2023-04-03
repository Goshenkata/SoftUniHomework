package archeologicalExcavations;

import org.junit.Assert;
import org.junit.Test;

public class ExcavationTests {
    @Test
    public void testArcheologist(){
        Archaeologist archaeologist = new Archaeologist("Panko", 20.2);
        Assert.assertEquals(archaeologist.getName(), "Panko");
        Assert.assertEquals(archaeologist.getEnergy(), 20.2,0.1);
    }
    @Test
    public void testExcavation(){
        Excavation excavation = new Excavation("ex", 20);
        Assert.assertEquals(excavation.getName(), "ex");
        Assert.assertEquals(excavation.getCapacity(), 20);
        Assert.assertEquals(excavation.getCount(), 0);
    }
    @Test(expected = NullPointerException.class)
    public void throwsOnEmptyName(){
        new Excavation("", 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void CapacityCantBeNegative(){
        new Excavation("bb", -10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void fullCapacity(){
        Excavation excavation = new Excavation("bb", 1);
        excavation.addArchaeologist(new Archaeologist("a",1));
        excavation.addArchaeologist(new Archaeologist("b",2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exists(){
        Excavation excavation = new Excavation("bb", 3);
        excavation.addArchaeologist(new Archaeologist("a",1));
        excavation.addArchaeologist(new Archaeologist("a",2));
    }

    @Test
    public void removeSucc(){
        Excavation excavation = new Excavation("bb", 3);
        excavation.addArchaeologist(new Archaeologist("a",1));
        Assert.assertEquals(1,excavation.getCount());
        Assert.assertTrue(excavation.removeArchaeologist("a"));
        Assert.assertEquals(0,excavation.getCount());
        excavation.addArchaeologist(new Archaeologist("a",1));
        excavation.removeArchaeologist("b");
        Assert.assertEquals(1,excavation.getCount());

    }
}
