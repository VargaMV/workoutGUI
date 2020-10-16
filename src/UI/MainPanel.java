package UI;

import UI.listeners.AddListener;
import UI.listeners.EvolveListener;
import UI.listeners.OccupyListener;
import UI.listeners.PlayerMoveListener;
import UI.mainPanelParts.ActionBoard;
import UI.mainPanelParts.GameField;
import UI.mainPanelParts.InformationBoard;
import UI.mainPanelParts.MiniMap;
import logic.Game;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JPanel {

    private final Game game;
    private JPanel informationPanel;

    public MainPanel(Game game) {
        this.game = game;
        setSize(800, 650);
        setLayout(null);
        createPanels();
        setVisible(true);
    }

    void createPanels() {

        informationPanel = new InformationBoard(game);
        add(informationPanel);

        JPanel miniMap = new MiniMap(game);
        add(miniMap);

        GameField gameFieldPanel = new GameField(game);

        EvolveListener evolveListener = new EvolveListener(gameFieldPanel, game);
        OccupyListener occupyListener = new OccupyListener(new ArrayList<>(Arrays.asList(gameFieldPanel, miniMap, informationPanel)), game);
        AddListener addListener = new AddListener(informationPanel, gameFieldPanel);
        Map<String, ActionListener> listeners = new HashMap<>();
        listeners.put("occupy", occupyListener);
        listeners.put("evolve", evolveListener);
        listeners.put("add", addListener);

        JPanel actionsPanel = new ActionBoard(game, listeners);
        add(actionsPanel);

        PlayerMoveListener moveListener = new PlayerMoveListener(informationPanel, miniMap, actionsPanel);

        gameFieldPanel.setMoveListener(moveListener);
        add(gameFieldPanel);

    }

    public void updateInformationPanel() {
        ((InformationBoard)informationPanel).updateInfo();
    }

}
