package goldDigger.models.disocverer;

public class Archaeologist extends BaseDiscoverer{
    private static final double ARCHAEOLOGIST_DISCOVERER_ENERGY = 60;
    public Archaeologist(String name) {
        super(name,ARCHAEOLOGIST_DISCOVERER_ENERGY );
    }
}
