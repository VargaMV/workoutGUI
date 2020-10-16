package UI.listeners;

import UI.mainPanelParts.ActionBoard;
import UI.mainPanelParts.InformationBoard;
import UI.mainPanelParts.MiniMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerMoveListener implements ActionListener {

    JPanel infoPanel;
    JPanel miniMap;
    JPanel actionPanel;

   public PlayerMoveListener(JPanel infoPanel, JPanel miniMap, JPanel actionPanel){
        this.infoPanel = infoPanel;
        this.miniMap = miniMap;
        this.actionPanel = actionPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((MiniMap)miniMap).drawMiniMap();
        ((InformationBoard)infoPanel).updateInfo();
        ((ActionBoard)actionPanel).updateButtons();
    }
}
