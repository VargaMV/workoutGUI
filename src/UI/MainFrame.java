package UI;

import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {


    private final Container containerPanel;
    private JPanel mainPanel;
    private JPanel stockPanel;
    private JPanel workoutPanel;
    private final CardLayout cardLayout;
    private Game game;

    public MainFrame() {
        super("Workout GUI");
        this.game = new Game(9);
        cardLayout = new CardLayout();
        this.mainPanel = new MainPanel(game);
        this.stockPanel = new StockPanel(game);


        containerPanel = new JPanel(cardLayout);
        containerPanel.setSize(800, 650);
        containerPanel.add(mainPanel, "main");
        containerPanel.add(stockPanel, "stock");

        add(containerPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        createMenuBar();
        setVisible(true);
    }

    void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        JMenu panelsMenu = new JMenu("Panels");
        panelsMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(panelsMenu);

        JMenuItem mainPanelItem = new JMenuItem("Main");
        mainPanelItem.addActionListener(e -> switchToMain());
        panelsMenu.add(mainPanelItem);

        JMenuItem stocksPanelItem = new JMenuItem("Manage Stocks");
        stocksPanelItem.addActionListener(e -> switchToStocks());
        panelsMenu.add(stocksPanelItem);
    }

    public void switchToStocks(){
        cardLayout.show(containerPanel, "stock");
        ((StockPanel)stockPanel).updateContent();
    }

    public void switchToMain(){
        cardLayout.show(containerPanel, "main");
        ((MainPanel)mainPanel).updateInformationPanel();
    }
}
