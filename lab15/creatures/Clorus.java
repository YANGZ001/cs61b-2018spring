package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {

    private double repEnergyRetained = 0.5;
    private double repEnergyGiven = 0.5;

    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param n
     */
    public Clorus(String n) {
        super(n);
    }

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    @Override
    public void move() {
        energy = energy - 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    @Override
    public Creature replicate() {
        energy = energy * repEnergyRetained;
        double babyEnergy = energy * repEnergyGiven;
        return new Clorus(babyEnergy);
    }

    @Override
    public void stay() {
        energy = energy - 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        if (plips.size() >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        }

        if (energy >= 1.0) {
            replicate();
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }

        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }

    @Override
    public Color color() {
        int r = 34;
        int g = 0;
        int b = 231;
        return color(r, g, b);
    }

    @Override
    public String name() {
        return "clorus";
    }
}
