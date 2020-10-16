package UI.listeners;

import UI.mainPanelParts.GameField;
import logic.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvolveListener implements ActionListener {

    private final JPanel field;
    private Game game;

    public EvolveListener (JPanel field, Game game) {
        this.field = field;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.incRangeOfVision();
        ((GameField)field).drawMap();
    }
}
