package UI.listeners;

import UI.mainPanelParts.GameField;
import UI.mainPanelParts.InformationBoard;
import UI.mainPanelParts.MiniMap;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OccupyListener implements ActionListener {

    JPanel map;
    JPanel miniMap;
    JPanel info;
    Game game;

    public OccupyListener(List<JPanel> panels, Game game) {
        map = panels.get(0);
        miniMap = panels.get(1);
        info = panels.get(2);
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.occupyOrIncrease();
        ((InformationBoard)info).updateInfo();
        ((GameField)map).drawMap();
        ((MiniMap)miniMap).drawMiniMap();
    }
}
