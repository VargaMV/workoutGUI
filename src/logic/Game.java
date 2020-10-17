package logic;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Field[][] fields;
    private Coordinate currentPos;
    private int currentValue;
    private int money = 1000;
    private int size;
    private int rangeOfVision;
    private boolean maxRange;

    //fix values
    private final Map<String, Integer> values = new HashMap<>();

    //changing values
    private final Map<String, Integer> stocks = new HashMap<>();
    private final Map<String, Integer> allStocks = new HashMap<>();
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

        File data = new File("data.csv");
        try (Scanner scanner = new Scanner(data)) {
            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                String[] parts = dataLine.split(",");
                String name = parts[0];
                int value = Integer.parseInt(parts[1]);
                values.put(name, value);
                records.put(name, 0);
                stocks.put(name, 0);
                allStocks.put(name, rand.nextInt(50) + 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        int prevValueAll = allStocks.get(exercise);
        allStocks.put(exercise, prevValueAll + 1);
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

    public void addValue(String exercise, int reps) {
        int prevValue = records.get(exercise);
        records.put(exercise, prevValue + reps);
        currentValue += Math.ceil(values.get(exercise) * getSharePercentage(exercise) / 100.0) * reps;
    }

    //GETTERS

    public int getNextPrice(String exercise) {
        return PriceCalculator.calculateNext(stocks.get(exercise));
    }

    public int getAllStockNumber(String exercise) {
        return allStocks.get(exercise);
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

    public Map<String, Integer> getAllStocks() {
        return allStocks;
    }

    public int getSharePercentage(String exercise) {
        return (int) Math.floor(stocks.get(exercise) * 100 / (double) allStocks.get(exercise));
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
