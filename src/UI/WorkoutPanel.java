package UI;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class WorkoutPanel extends JPanel {

    private final Game game;
    JLabel[] exerciseLabels;
    JButton[] saveButtons;
    JTextField[] inputFields;

    public WorkoutPanel(Game game) {
        this.game = game;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int exerciseNumber = game.getStocks().size();
        exerciseLabels = new JLabel[exerciseNumber];
        saveButtons = new JButton[exerciseNumber];
        inputFields = new JTextField[exerciseNumber];

        int i = 0;
        for (var entry : game.getValues().entrySet()) {
            String exercise = entry.getKey();
            Integer value = entry.getValue();
            int records = game.getRecords().get(exercise);
            int ownStock = game.getStocks().get(exercise);
            int allStock = game.getAllStockNumber(exercise);
            int myValue = (int) Math.ceil(value * ownStock / (double) allStock);
            exerciseLabels[i] = new JLabel();
            exerciseLabels[i].setText(String.format("%s : %d DefValue: %d OwnValue: %d", exercise, records, value, myValue));
            saveButtons[i] = new JButton("Save");
            int finalI = i;
            saveButtons[i].addActionListener(e -> {
                game.addValue(exercise, Integer.parseInt(inputFields[finalI].getText()));
                inputFields[finalI].setText("");
                updateContent();
            });
            inputFields[i] = new JTextField();

            int col = (i % 2) * 3;
            int row = Math.floorDiv(i, 2);
            gbc.gridx = col;
            gbc.gridy = row;
            gbc.ipadx = 10;
            gbc.ipady = 10;
            gbc.insets = new Insets(10, 0, 0, 0);
            add(exerciseLabels[i], gbc);

            gbc.gridx = col + 1;
            gbc.gridy = row;
            gbc.ipadx = 10;
            gbc.ipady = 10;
            gbc.insets = new Insets(10, 10, 0, 0);
            add(saveButtons[i], gbc);

            gbc.gridx = col + 2;
            gbc.gridy = row;
            gbc.ipadx = 60;
            gbc.ipady = 10;
            gbc.insets = new Insets(10, 10, 0, 0);
            add(inputFields[i], gbc);

            i++;
        }

    }

    public void updateContent() {
        int i = 0;
        for (var entry : game.getValues().entrySet()) {
            String exercise = entry.getKey();
            Integer value = entry.getValue();
            int records = game.getRecords().get(exercise);
            int ownStock = game.getStocks().get(exercise);
            int allStock = game.getAllStockNumber(exercise);
            int myValue = (int) Math.ceil(value * ownStock / (double) allStock);
            exerciseLabels[i].setText(String.format("%s : %d DefValue: %d OwnValue: %d", exercise, records, value, myValue));
            i++;
        }
    }
}
