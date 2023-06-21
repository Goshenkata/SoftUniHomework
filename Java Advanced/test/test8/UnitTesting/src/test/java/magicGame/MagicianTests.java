package magicGame;

import org.junit.Assert;
import org.junit.Test;

public class MagicianTests {
    @Test
    public void testMagic() {
        Magic magic = new Magic("a",1);
        Assert.assertEquals(magic.getName(), "a");
        Assert.assertEquals(magic.getBullets(), 1);
    }
    @Test
    public void testMagician() {
        Main.main(null);
        Magic magic = new Magic("a",1);
        Assert.assertEquals(magic.getName(), "a");
        Assert.assertEquals(magic.getBullets(), 1);

        Magician magician = new Magician("a",2);
        Assert.assertEquals(magician.getUsername(), "a");
        Assert.assertEquals(magician.getHealth(), 2);
        Assert.assertEquals(magician.getMagics().size(),0);
        magician.addMagic(magic);
        Assert.assertEquals(magician.getMagics().size(),1);
        Assert.assertNull(magician.getMagic("b"));
        Assert.assertEquals(magician.getMagic("a").getName(), "a");
        Assert.assertTrue(magician.removeMagic(magic));
        Assert.assertEquals(magician.getMagics().size(),0);
        Assert.assertFalse(magician.removeMagic(magic));
        magician.takeDamage(1);
        Assert.assertEquals(magician.getHealth(), 1);
        magician.takeDamage(1);
        Assert.assertEquals(magician.getHealth(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testMagicianNull() {
        Magician magician = new Magician("",1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMagicianIllegal() {
        Magician magician = new Magician("a",-1);
    }
    @Test(expected = IllegalStateException.class)
    public void testMagicianDead() {
        Magician magician = new Magician("a",1);
        magician.takeDamage(2);
        magician.takeDamage(2);
    }
    @Test(expected = NullPointerException.class)
    public void testAddMagicNull() {
        Magician magician = new Magician("a",1);
        magician.addMagic(null);
    }
}
