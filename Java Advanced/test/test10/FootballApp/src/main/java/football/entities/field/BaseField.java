package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public int sumEnergy() {
        int sum = 0;
        for (Supplement supplement : supplements) {
            sum += supplement.getEnergy();
        }
        return sum;
    }

    @Override
    public void addPlayer(Player player) {
        if (players.size() + 1 <= capacity) {
            players.add(player);
            return;
        }
        throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player : players) {
            player.stimulation();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder out = new StringBuilder();
        out.append(System.lineSeparator());
        out.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        out.append(System.lineSeparator());
        out.append(String.format("Player: %s", players.isEmpty() ? "none" : players.stream().map(Player::getName).collect(Collectors.joining(" "))));
        out.append(System.lineSeparator());
        out.append(String.format("Supplement: %d", supplements.size()));
        out.append(System.lineSeparator());
        out.append(String.format("Energy: %d", sumEnergy()));
        return out.toString();
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public String getName() {
        return name;
    }
}
