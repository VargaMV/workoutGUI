package UI;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class StockPanel extends JPanel {

    Game game;
    JLabel moneyLabel;
    JLabel[] exerciseLabels;
    JButton[] buyButtons;

    class BarsJPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int i = 0;
            for (var entry : game.getStocks().entrySet()) {
                String exercise = entry.getKey();
                Integer stockNumber = entry.getValue();
                int all = game.getAllStockNumber(exercise);
                int share = Math.floorDiv(stockNumber * 100, all);

                g2d.setColor(new Color(100, 100, 100));
                g2d.fillRect(20, i * 46 + 35, 300, 20);

                g2d.setColor(new Color(120, 200, 50));
                g2d.fillRect(20, i * 46 + 35, share * 3, 20);
                i++;
            }

        }
    }

    public StockPanel(Game game) {
        this.game = game;
        int exerciseNumber = game.getStocks().size();
        exerciseLabels = new JLabel[exerciseNumber];
        buyButtons = new JButton[exerciseNumber];
        setLayout(new GridLayout());

        JPanel manager = new JPanel();
        add(manager);

        JPanel bars = new BarsJPanel();
        add(bars);

        manager.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        moneyLabel = new JLabel();
        moneyLabel.setText(String.format("Current money : %d", game.getMoney()));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        manager.add(moneyLabel);

        gbc.gridwidth = 1;


        int i = 0;
        for (var entry : game.getStocks().entrySet()) {
            String exercise = entry.getKey();
            exerciseLabels[i] = new JLabel();
            buyButtons[i] = new JButton("Buy");
            buyButtons[i].addActionListener(e -> {
                game.incSpecifiedStock(exercise);
                game.payForStock(exercise);
                updateContent();
                bars.repaint();
            });
            buyButtons[i].setEnabled(game.canIAffordIt(exercise));

            int col = 0;
            int row = i + 1;

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.ipadx = 10;
            gbc.ipady = 10;
            gbc.insets = new Insets(10, 10, 0, 0);
            manager.add(exerciseLabels[i], gbc);

            gbc.gridx = col + 1;
            gbc.gridy = row;
            gbc.insets = new Insets(10, 10, 0, 0);
            gbc.ipadx = 10;
            gbc.ipady = 10;
            manager.add(buyButtons[i], gbc);
            i++;
        }
        updateContent();

        setVisible(true);
    }

    public void updateContent() {
        moneyLabel.setText(String.format("Current money : %d", game.getMoney()));
        int i = 0;
        for (var entry : game.getStocks().entrySet()) {
            String exercise = entry.getKey();
            int stockNumber = entry.getValue();
            int price = game.getNextPrice(exercise);
            int all = game.getAllStockNumber(exercise);
            int share = game.getSharePercentage(exercise);
            exerciseLabels[i].setText(String.format("%s :  Price: %d  Share: %d %% (%d / %d)", exercise, price, share, stockNumber, all));
            buyButtons[i].setEnabled(game.canIAffordIt(exercise));
            i++;
        }
    }
}
