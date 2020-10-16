package UI;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class WorkoutPanel extends JPanel {

    private final Game game;
    JLabel[] exerciseLabels;
    JButton[] saveButtons;

    public WorkoutPanel(Game game) {
        this.game = game;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int exerciseNumber = game.getStocks().size();
        exerciseLabels = new JLabel[exerciseNumber];
        saveButtons = new JButton[exerciseNumber];

        int i = 0;
        for (var entry : game.getValues().entrySet()) {
            String exercise = entry.getKey();
            Integer value = entry.getValue();
            int all = game.getRecords().get(exercise);
            exerciseLabels[i] = new JLabel();
            exerciseLabels[i].setText(String.format("%s : %d Value: %d", exercise, all, value));
            saveButtons[i] = new JButton("Save");
            saveButtons[i].addActionListener(e -> updateContent());

            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.ipadx = 10;
            gbc.ipady = 10;
            gbc.insets = new Insets(10, 0, 0, 0);
            add(exerciseLabels[i], gbc);

            gbc.gridx = 1;
            gbc.gridy = i + 1;
            gbc.insets = new Insets(10, 10, 0, 0);
            gbc.ipadx = 10;
            gbc.ipady = 10;
            add(saveButtons[i], gbc);
            i++;
        }

    }

    public void updateContent() {
        int i = 0;
        for (var entry : game.getValues().entrySet()) {
            String exercise = entry.getKey();
            Integer value = entry.getValue();
            int all = game.getRecords().get(exercise);
            exerciseLabels[i].setText(String.format("%s : %d Value: %d", exercise, all, value));
            i++;
        }
    }
}
