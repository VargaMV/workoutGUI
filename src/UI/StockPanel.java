package UI;

import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.util.jar.Attributes;

public class StockPanel extends JPanel {

    Game game;
    JLabel moneyLabel;
    JLabel [] exerciseLabels;
    JButton [] buyButtons;

    public StockPanel(Game game) {
        this.game = game;
        int exerciseNumber = game.getStocks().size();
        exerciseLabels = new JLabel[exerciseNumber];
        buyButtons = new JButton[exerciseNumber];

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        moneyLabel = new JLabel();
        moneyLabel.setText(String.format("Current money : %d", game.getMoney()));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(moneyLabel);


        int i = 0;
        for (var entry : game.getStocks().entrySet()) {
            String exercise = entry.getKey();
            Integer stockNumber = entry.getValue();
            int price = game.getNextPrice(exercise);
            exerciseLabels[i] = new JLabel();
            exerciseLabels[i].setText(String.format("%s : %d Price: %d", exercise, stockNumber, price));
            buyButtons[i] = new JButton("Buy");
            buyButtons[i].addActionListener(e -> {
                game.incSpecifiedStock(exercise);
                game.payForStock(exercise);
                updateContent();
            });
            buyButtons[i].setEnabled(game.canIAffordIt(exercise));

            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.ipadx = 10;
            gbc.ipady = 10;
            gbc.insets = new Insets(10,0,0,0);
            add(exerciseLabels[i], gbc);

            gbc.gridx = 1;
            gbc.gridy = i + 1;
            gbc.insets = new Insets(10,10,0,0);
            gbc.ipadx = 10;
            gbc.ipady = 10;
            add(buyButtons[i], gbc);
            i++;
        }

        setVisible(true);
    }

    public void updateContent() {
        moneyLabel.setText(String.format("Current money : %d", game.getMoney()));
        int i = 0;
        for (var entry : game.getStocks().entrySet()) {
            String exercise = entry.getKey();
            Integer stockNumber = entry.getValue();
            int price = game.getNextPrice(exercise);
            exerciseLabels[i].setText(String.format("%s : %d Price: %d", exercise, stockNumber, price));
            buyButtons[i].setEnabled(game.canIAffordIt(exercise));
            i++;
        }
    }
}
