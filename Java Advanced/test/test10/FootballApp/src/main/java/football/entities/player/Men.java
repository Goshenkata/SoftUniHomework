package football.entities.player;

public class Men extends BasePlayer{
    public Men(String name, String nationality, int strength) {
        super(name, nationality, 85.0, strength);
    }

    @Override
    public void stimulation() {
        super.strength += 145;
    }
}
