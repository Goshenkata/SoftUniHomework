package robots;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceTests {
    private Service service;
    @Before
    public void init() {
        this.service = new Service("service", 2);
    }
    @Test
    public void testRobot() {
        Robot robot = new Robot("a");
        Assert.assertEquals(robot.getName(), "a");
        Assert.assertTrue(robot.isReadyForSale());
        robot.setReadyForSale(false);
        Assert.assertFalse(robot.isReadyForSale());
    }
    @Test
    public void testService() {
        Assert.assertEquals(service.getName(), "service");
        Assert.assertEquals(service.getCapacity(), 2);
        Assert.assertEquals(service.getCount(), 0);
    }
    @Test(expected = NullPointerException.class)
    public void invalidName() {
        Service service1 = new Service("",2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void invalidCapacity() {
        Service service1 = new Service("a",-2);
    }
    @Test
    public void addRobot() {
        Robot robot = new Robot("rob");
        Robot robo2 = new Robot("rob2");
        service.add(robot);
        Assert.assertEquals(service.getCount(),1);
        service.add(robo2);
        Assert.assertEquals(service.getCount(),2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void capacityError() {
        Robot robot = new Robot("rob");
        Robot robo2 = new Robot("rob2");
        Robot robo3 = new Robot("rob3");
        service.add(robot);
        service.add(robo2);
        service.add(robo3);
    }
    @Test
    public void removeRobot() {
        Robot robot = new Robot("rob");
        Assert.assertEquals(service.getCount(),0);
        service.add(robot);
        Assert.assertEquals(service.getCount(),1);
        service.remove("rob");
        Assert.assertEquals(service.getCount(),0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeInvalidRobot() {
        service.remove("rob");
    }
    @Test
    public void forSale() {
        Robot rob = new Robot("rob");
        service.add(rob);
        Assert.assertTrue(rob.isReadyForSale());
        Robot robot = service.forSale("rob");
        Assert.assertFalse(robot.isReadyForSale());
    }
    @Test(expected = IllegalArgumentException.class)
    public void forSaleInvalid() {
        service.forSale("rob");
    }
    @Test
    public void reportR(){
        Robot rob1 = new Robot("rob1");
        Robot rob2 = new Robot("rob2");
        service.add(rob1);
        Assert.assertEquals("The robot rob1 is in the service service!", service.report());
        service.add(rob2);
        Assert.assertEquals("The robot rob1, rob2 is in the service service!", service.report());
    }
}