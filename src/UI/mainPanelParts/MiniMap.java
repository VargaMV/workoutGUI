package UI.mainPanelParts;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class MiniMap extends JPanel{
    private Game game;
    public MiniMap(Game game){
        this.game = game;

        setBounds(520,20,260,260);
        setLayout(null);

        drawMiniMap();
    }
    public void drawMiniMap(){
        removeAll();
        repaint();
        revalidate();
        for(int i = 0; i < game.getSize(); i++){
            for(int j = 0; j < game.getSize(); j++){
                JButton mapButton = new JButton("");
                mapButton.setBounds(10 + (i + 1) * 20, (j + 1) * 20, 10, 10);
                mapButton.setEnabled(false);
                mapButton.setBackground(game.getField(i, j).getColor());
                if(game.getX() == i && game.getY() == j){
                    mapButton.setEnabled(true);
                    mapButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                }
                add(mapButton);
            }
        }
    }
}
