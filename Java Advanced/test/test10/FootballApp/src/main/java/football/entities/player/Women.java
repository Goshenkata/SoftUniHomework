package football.entities.player;

public class Women extends BasePlayer{
    public Women(String name, String nationality, int strength) {
        super(name, nationality, 60.0, strength);
    }

    @Override
    public void stimulation() {
        super.strength += 115;
    }
}
