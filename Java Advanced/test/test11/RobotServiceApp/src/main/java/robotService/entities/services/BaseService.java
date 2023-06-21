package robotService.entities.services;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService implements Service {
    private String name;
    private int capacity;
    Collection<Supplement> supplements;
    Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        supplements = new ArrayList<>();
        robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        Optional<Robot> any = robots.stream().filter(r -> r.getName().equals(robot.getName())).findAny();
        if (robots.size() + 1 <= capacity && any.isPresent()) {
            robots.add(robot);
            return;
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : robots) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {
        int sum = 0;
        for (Supplement supplement : supplements) {
            sum += supplement.getHardness();
        }
        return sum;
    }

    @Override
    public String getStatistics() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("%s %s:", name, this.getClass().getSimpleName()));
        out.append(System.lineSeparator());
        out.append(String.format("Robots: %s", robots.isEmpty() ? "none" : robots.stream().map(Robot::getName).collect(Collectors.joining(" "))));
        out.append(System.lineSeparator());
        out.append(String.format("Supplements: %d Hardness: %d", supplements.size(),sumHardness()));
        return out.toString().trim();
    }
}
