package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int inspectedSpots = 0;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        discovererRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        Spot spot = spotRepository.byName(spotName);
        OperationImpl operation = new OperationImpl();
        Collection<Discoverer> collection = discovererRepository.getCollection();
        List<Discoverer> list =
                collection
                        .stream()
                        .filter(d -> d.getEnergy() > 45)
                        .collect(Collectors.toUnmodifiableList());

        if (list.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        operation.startOperation(spot, list);
        inspectedSpots++;
        return String.format(ConstantMessages.INSPECT_SPOT, spotName, collection.stream().filter(d -> d.getEnergy()==0).count());
    }

    @Override
    public String getStatistics() {
        StringBuilder out = new StringBuilder();
        out.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, inspectedSpots));
        out.append(System.lineSeparator());
        out.append(ConstantMessages.FINAL_DISCOVERER_INFO);
        for (Discoverer discoverer : discovererRepository.getCollection()) {
            out.append(System.lineSeparator());
            out.append(String.format(ConstantMessages.FINAL_DISCOVERER_NAME, discoverer.getName()));
            out.append(System.lineSeparator());
            out.append(String.format(ConstantMessages.FINAL_DISCOVERER_ENERGY, discoverer.getEnergy()));
            out.append(System.lineSeparator());
            Collection<String> exhibits = discoverer.getMuseum().getExhibits();
            if (exhibits.isEmpty()) {
                out.append(String.format(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));
            } else {
                out.append(
                        String.format(
                                ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                                String.join(ConstantMessages.FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, exhibits)
                        ));
            }
        }
        return out.toString();
    }
}
