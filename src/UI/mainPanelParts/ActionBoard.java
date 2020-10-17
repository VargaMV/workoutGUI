package UI.mainPanelParts;

import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class ActionBoard extends JPanel {

    private final Game game;

    JButton occupyButton;
    JLabel messageLabel;

    public ActionBoard(Game game, Map<String, ActionListener> listeners) {
        this.game = game;
        setLayout(null);
        setBounds(0, 500, 500, 120);
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

        JTextField moneyInput = new JTextField();
        moneyInput.setBounds(210, 10, 40, 30);
        add(moneyInput);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(110, 10, 80, 30);
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
                if ("".equals(moneyInput.getText())) {
                    game.incMoney(game.getCurrentValue());
                    game.setCurrentValue(0);
                    updateButtons();
                } else {
                    messageLabel.setText("Integer number needed!");
                }
            }

        });
        add(convertButton);

        messageLabel = new JLabel("");
        messageLabel.setBounds(260, 10, 200, 30);
        add(messageLabel);

        updateButtons();
    }

    public void updateButtons() {
        occupyButton.setEnabled(game.amIWorthy());
    }
}
