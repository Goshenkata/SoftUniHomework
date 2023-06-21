package robotService.core;

import robotService.common.ConstantMessages;
import robotService.common.ExceptionMessages;
import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ControllerImpl implements Controller{
    private Collection<Service> services;
    private SupplementRepository supplements;

    public ControllerImpl() {
        services = new ArrayList<>();
        supplements = new SupplementRepository();
    }

    @Override
    public String addService(String type, String name) {
        Optional<Service> any = services.stream().filter(f -> f.getName().equals(name)).findAny();
        if (any.isPresent()) {
            throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        Service service;
        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SERVICE_TYPE);
        }
        services.add(service);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }
        supplements.addSupplement(supplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalStateException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }
        for (Service service : services) {
            if (service.getName().equals(serviceName)) {
                service.addSupplement(supplement);
                supplements.removeSupplement(supplement);
            }
        }
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        boolean isMale = false;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                isMale = true;
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_ROBOT_TYPE);
        }
        for (Service service : services) {
            if (service.getName().equals(serviceName)) {
                if ((isMale && service.getClass().getSimpleName().equals("MainService"))
                        || (!isMale && service.getClass().getSimpleName().equals("SecondaryService"))) {
                    service.addRobot(robot);
                    return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
                }
            }
        }
        return ConstantMessages.UNSUITABLE_SERVICE;
    }

    @Override
    public String feedingRobot(String serviceName) {
        int count = 0;
        for (Service service : services) {
            if (service.getName().equals(serviceName)) {
                count = service.getRobots().size();
                for (Robot robot : service.getRobots()) {
                    robot.eating();
                }
                break;
            }
        }
        return String.format(ConstantMessages.FEEDING_ROBOT, count);
    }

    @Override
    public String sumOfAll(String serviceName) {
        double sum = 0;
        for (Service service : services) {
            if (service.getName().equals(serviceName)) {
                for (Robot robot : service.getRobots()) {
                    sum += robot.getPrice();
                }
                for (Supplement supplement : service.getSupplements()) {
                    sum += supplement.getPrice();
                }
                break;
            }
        }
        return String.format(ConstantMessages.VALUE_SERVICE,serviceName, sum);
    }

    @Override
    public String getStatistics() {
        List<String> out = new ArrayList<>();
        for (Service service : services) {
            out.add(service.getStatistics());
        }
        return String.join(System.lineSeparator(), out);
    }
}
