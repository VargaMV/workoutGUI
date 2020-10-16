package UI.mainPanelParts;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class InformationBoard extends JPanel {

    private final Game game;
    private final JLabel currentFieldValueLabel;
    private final JLabel myCurrentValueLabel;
    private final JLabel moneyLabel;

    public InformationBoard(Game game){
        this.game = game;
        setLayout(null);
        setBounds(500,300,300,350);
        setBackground(Color.YELLOW);

        currentFieldValueLabel = new JLabel("");
        currentFieldValueLabel.setBounds(10,20,200,15);
        add(currentFieldValueLabel);

        myCurrentValueLabel = new JLabel("");
        myCurrentValueLabel.setBounds(10,40,200,15);
        add(myCurrentValueLabel);

        moneyLabel = new JLabel("");
        moneyLabel.setBounds(10, 60, 200, 15);
        add(moneyLabel);

        updateInfo();

    }

    public void updateInfo(){
        currentFieldValueLabel.setText(
                String.format(
                        "Current field value: %d",
                        game.getField(game.getCurrentPos()).getValue()
                )
        );
        myCurrentValueLabel.setText(
                String.format(
                        "My current value: %d",
                        game.getCurrentValue()
                )
        );
        moneyLabel.setText(
                String.format(
                        "My current money: %d",
                        game.getMoney()
                )
        );
    }


}
