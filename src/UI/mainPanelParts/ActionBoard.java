package UI.mainPanelParts;

import UI.MainFrame;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class ActionBoard extends JPanel {

    private Game game;

    JButton occupyButton;
    JLabel messageLabel;

    public ActionBoard(Game game, Map<String,ActionListener> listeners) {
        this.game = game;
        setLayout(null);
        setBounds(0,500,500,120);
        setBackground(Color.BLUE);

        occupyButton = new JButton("Occupy");
        occupyButton.setBounds(10, 10, 80, 30);
        occupyButton.addActionListener(e -> updateButtons());
        occupyButton.addActionListener(listeners.get("occupy"));
        add(occupyButton);

        JButton evolveButton = new JButton("Evolve");
        evolveButton.setBounds(10, 50, 80, 30);
        evolveButton.addActionListener(listeners.get("evolve"));
        add(evolveButton);

        JTextField valueInput = new JTextField();
        valueInput.setBounds(210, 10, 40, 30);
        add(valueInput);

        JButton addButton = new JButton("Add");
        addButton.setBounds(110, 10, 80, 30);
        addButton.addActionListener(listeners.get("add"));
        addButton.addActionListener(e -> {
            try {
                int oldValue = game.getCurrentValue();
                int newValue = Integer.parseInt(valueInput.getText());
                game.setCurrentValue(oldValue + newValue);
                valueInput.setText("");
                messageLabel.setText("");
                updateButtons();
            } catch (NumberFormatException ex) {
                messageLabel.setText("Integer number needed!");
            }
        });
        add(addButton);

        JTextField moneyInput = new JTextField();
        moneyInput.setBounds(210, 50, 40, 30);
        add(moneyInput);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(110, 50, 80, 30);
        convertButton.addActionListener(listeners.get("add"));
        convertButton.addActionListener(e -> {
            int value = game.getCurrentValue();
            try {
                int amountConverted = Integer.parseInt(moneyInput.getText());
                amountConverted = Math.min(value, amountConverted);
                game.setCurrentValue(value - amountConverted);
                game.incMoney(amountConverted);
                moneyInput.setText("");
                messageLabel.setText("");
                updateButtons();
            } catch (NumberFormatException ex) {
                if("".equals(moneyInput.getText())) {
                    game.setCurrentValue(0);
                    game.incMoney(game.getCurrentValue());
                    updateButtons();
                }
                else {
                    messageLabel.setText("Integer number needed!");
                }
            }

        });
        add(convertButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(260, 10, 200, 30);
        add(messageLabel);

        /*JButton toStocksButton = new JButton("Manage Stocks");
        toStocksButton.setBounds(260, 50, 180, 30);
        toStocksButton.addActionListener(e -> {
            MainFrame.switchToStocks();
        });
        add(toStocksButton);*/

        updateButtons();
    }

    public void updateButtons() {
        occupyButton.setEnabled(game.amIWorthy());
    }
}
