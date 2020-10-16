package logic;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    private Field[][] fields;
    private Coordinate currentPos;
    private int currentValue;
    private int money;
    private int size;
    private int rangeOfVision;
    private boolean maxRange;

    //TODO: refactor below
    private final Map<String, Integer> stocks = new HashMap<>();
    private final Map<String, Integer> values = new HashMap<>();
    private final Map<String, Integer> records = new HashMap<>();


    public Game() {
        new Game(8);
    }

    public Game(int mapSize) {
        this.size = mapSize;
        Random rand = new Random();
        fields = new Field[size][size];
        currentPos = new Coordinate(rand.nextInt(size), rand.nextInt(size));
        currentValue = 0;
        rangeOfVision = 1;
        maxRange = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field();
                if (rand.nextInt(4) == 0) {
                    fields[i][j].setValue(rand.nextInt(1000) + 1);
                    fields[i][j].setColor(Color.ORANGE);
                }
            }
        }

        String[] exercises = {"push-up", "pull-up", "squat", "jumping jacks", "sit-up"};
        Integer[] exValues = {14, 32, 8, 3, 10};
        int i = 0;
        for (String key : exercises) {
            stocks.put(key, 0);
            records.put(key, 0);
            values.put(key, exValues[i]);
            i++;
        }
    }

    public void updateFieldColor(Color color) {
        Field currentField = fields[currentPos.x][currentPos.y];
        currentField.setColor(color);
    }

    public void updateFieldValue(int value, boolean add) {
        Field currentField = fields[currentPos.x][currentPos.y];
        int prevValue = currentField.getValue();
        currentField.setValue(add ? (prevValue + value) : value);
    }

    public void incRangeOfVision() {
        int prevRange = rangeOfVision;
        if (maxRange) {
            rangeOfVision = Math.min(2, rangeOfVision + 1);
        }
        maxRange = !maxRange || prevRange == 2;
    }

    public void incSpecifiedStock(String exercise) {
        int prevValue = stocks.get(exercise);
        stocks.put(exercise, prevValue + 1);
    }

    public boolean amIWorthy() {
        return (fields[currentPos.x][currentPos.y].getValue() < currentValue)
                || (getField(currentPos).getColor() == Color.green && currentValue > 0);
    }

    public boolean canIAffordIt(String exercise) {
        return money >= PriceCalculator.calculateNext(stocks.get(exercise));
    }

    public void payForStock(String exercise) {
        int price = PriceCalculator.calculate(stocks.get(exercise));
        money -= price;
    }

    public void occupyOrIncrease() {
        if (getField(currentPos).getColor() == Color.green) {
            updateFieldValue(currentValue, true);
        } else {
            updateFieldValue(currentValue, false);
            updateFieldColor(Color.green);
        }
        setCurrentValue(0);
    }

    //GETTERS

    public int getNextPrice(String exercise) {
        return PriceCalculator.calculateNext(stocks.get(exercise));
    }

    public Coordinate getCurrentPos() {
        return currentPos;
    }

    public int getX() {
        return currentPos.x;
    }

    public int getY() {
        return currentPos.y;
    }

    public int getSize() {
        return size;
    }

    public Field[][] getFields() {
        return fields;
    }

    public Field getField(int i, int j) {
        return fields[i][j];
    }

    public Field getField(Coordinate c) {
        return fields[c.x][c.y];
    }

    public int getRangeOfVision() {
        return rangeOfVision;
    }

    public boolean isMaxRange() {
        return maxRange;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getMoney() {
        return money;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    public Map<String, Integer> getValues() {
        return values;
    }

    public Map<String, Integer> getRecords() {
        return records;
    }

    //SETTERS


    public void setCurrentPos(Coordinate currentPos) {
        this.currentPos = currentPos;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public void incMoney(int additionalMoney) {
        this.money += additionalMoney;
    }

    public void decMoney(int spentMoney) {
        this.money -= spentMoney;
    }

    public void incCurrentValue(int additionalValue) {
        this.currentValue += additionalValue;
    }
}
