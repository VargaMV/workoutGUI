package UI.mainPanelParts;

import UI.customComponents.FieldButton;
import logic.Coordinate;
import logic.Field;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameField extends JPanel {
    private final Game game;
    private ActionListener listener;

    public GameField(Game game){
        this.game = game;
        setLayout(null);
        setBounds(0,0,500,500);
        setBackground(Color.GRAY);
        drawMap();
        listener = e -> {
            System.out.println("MoveListener needs to be set");
        };

    }

    public void setMoveListener(ActionListener listener) {
        this.listener = listener;
    }

    public void drawMap(){
        removeAll();
        repaint();
        revalidate();

        Coordinate pos = game.getCurrentPos();
        int posX = game.getX();
        int posY = game.getY();
        int size = game.getSize();
        Field[][] fields = game.getFields();

        int minX = Math.max(0, posX - 2);
        int maxX = Math.min(size-1, posX + 2);
        int minY = Math.max(0, posY - 2);
        int maxY = Math.min(size-1, posY + 2);
        for(int i = minX; i <= maxX; i++){
            for(int j = minY; j <= maxY; j++){
                FieldButton button = new FieldButton(i + "" + j);
                button.setBounds((3 + i - posX) * 75, (3 + j - posY) * 75, 50, 50);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                int finalJ = j;
                int finalI = i;
                button.addActionListener(listener);
                button.addActionListener(e -> {
                    game.setCurrentPos(new Coordinate(finalI, finalJ));
                    drawMap();
                });
                add(button);
                button.setBackground(Color.GRAY);
                if(pos.distance(i, j, game.isMaxRange()) <= game.getRangeOfVision()){
                    button.setBorderPainted(true);
                    button.setBorder(BorderFactory.createLineBorder(fields[i][j].getColor(),3));
                }

                if(pos.distance(i, j,false) > 1) {
                    button.setEnabled(false);
                } else if(pos.distance(i, j,false) == 1) {
                    button.setBorderPainted(true);
                    if(game.getCurrentValue() > 0) {
                        button.setEnabled(false);
                    } else {
                        button.setBackground(fields[i][j].getColor());
                    }
                } else if(pos.distance(i, j,false) == 0) {
                    button.setEnabled(false);
                    button.setBorderPainted(true);
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                    button.setBackground(fields[i][j].getColor());
                }
            }
        }

        JLabel posLabel = new JLabel();
        posLabel.setText(String.format("Current position: (%d, %d)",posX, posY));
        posLabel.setBounds(20, 10, 200, 30);
        add(posLabel);
    }

    public void setFieldColor(Color color){
        game.updateFieldColor(color);
        drawMap();
    }

    public void incRangeOfVision(){
        game.incRangeOfVision();
        drawMap();
    }
}

