package UI.listeners;
import UI.mainPanelParts.GameField;
import UI.mainPanelParts.InformationBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

    private final JPanel infoPanel;
    private JPanel map;

    public AddListener(JPanel infoPanel, JPanel map) {
        this.infoPanel = infoPanel;
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((InformationBoard)infoPanel).updateInfo();
        ((GameField)map).drawMap();
    }
}
