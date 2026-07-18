package simulation;

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.*;

public class Driver {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel titlePanel;
    private JButton startButton;

    private JPanel gamePanel;

    private JLabel background;

    private JButton plantButton;
    private JButton waterButton;
    private JButton growButton;
    private JButton harvestButton;

    private JLabel profitLabel;
    private JLabel profitValue;

    private JLabel wheatCounterLabel;
    private JLabel wheatCounterValue;

    private JLabel parsnipCounterLabel;
    private JLabel parsnipCounterValue;

    private JLabel emptyFarmMessageLabel;

    private JPanel farmPanel;
    private JLabel[][] groundLabels;
    private JLabel[][] cropLabels;

    private Farm farm;

    private int wheatCounter;
    private int parsnipCounter;

    private JLabel checkboxLabelEven = new JLabel();
    private JLabel checkboxLabelOdd = new JLabel();

    private java.net.URL checkedBox_imgURL = getClass().getResource("/assets/checkedBox.png");
    private ImageIcon checkedBoxIcon = new ImageIcon(checkedBox_imgURL);
    private Image scaledCheckedBox = checkedBoxIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    private ImageIcon scaledCheckedBoxIcon = new ImageIcon(scaledCheckedBox);

    private java.net.URL uncheckedBox_imgURL = getClass().getResource("/assets/uncheckedBox.png");
    private ImageIcon uncheckedBoxIcon = new ImageIcon(uncheckedBox_imgURL);
    private Image scaledUncheckedBox = uncheckedBoxIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    private ImageIcon scaledUncheckedBoxIcon = new ImageIcon(scaledUncheckedBox);

    private ImageIcon windowLogo = loadImage("parsnipLogo.png");
    private ImageIcon wetTile = loadImage("wet.png");
    private ImageIcon dryTile = loadImage("dry.png");
    private ImageIcon seedWheat = loadImage("seed-wheat.png");
    private ImageIcon ripeWheat = loadImage("ripe-wheat.png");
    private ImageIcon seedParsnip = loadImage("seed-parsnip.png");
    private ImageIcon ripeParsnip = loadImage("ripe-parsnip.png");

    public Driver() {

        JFrame frame = new JFrame("Farm Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(windowLogo.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.setPreferredSize(new Dimension(960, 540));

        char[][] emptyFarm = {
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'}
        };

        farm = new Farm(emptyFarm);

        createTitlePanel();
        createGamePanel();

        mainPanel.add(titlePanel, "TITLE");
        mainPanel.add(gamePanel, "GAME");

        frame.add(mainPanel);
        
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void createTitlePanel() {

        titlePanel = new JPanel();
        titlePanel.setLayout(null);
        
        java.net.URL title_imgURL = getClass().getResource("/assets/title-screen.png");
        ImageIcon titleImage = new ImageIcon(title_imgURL);
        Image scaledTitleImage = titleImage.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        titleImage = new ImageIcon(scaledTitleImage);

        JLabel background = new JLabel(titleImage);
        background.setBounds(0, 0, 960, 540);
        background.setLayout(null);

        java.net.URL start_imgURL = getClass().getResource("/assets/start.png");
        ImageIcon startIcon = new ImageIcon(start_imgURL);
        Image scaledStartButton = startIcon.getImage().getScaledInstance(216, 90, Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledStartButton);

        startButton = new JButton(startIcon);
        startButton.setBounds(372, 360, 216, 90);
        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "GAME");
        });

        background.add(startButton);
        titlePanel.add(background);

    }

    private void createGamePanel() {

        gamePanel = new JPanel();
        gamePanel.setLayout(null);

        java.net.URL background_imgURL = getClass().getResource("/assets/farm-background.png");
        ImageIcon backgroundImage = new ImageIcon(background_imgURL);
        Image scaledBackgroundImage = backgroundImage.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(scaledBackgroundImage);

        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 960, 540);
        background.setLayout(null);

        checkboxLabelEven.setBounds(810, 200, 200, 100);
        checkboxLabelEven.setText("<html>even-indexed <br/>rows contain <br/>only wheat<html>");
        checkboxLabelEven.setFont(new Font("SansSerif", Font.BOLD, 11));
        checkboxLabelEven.setForeground(Color.BLACK);

        checkboxLabelOdd.setBounds(810, 320, 200, 100);
        checkboxLabelOdd.setText("<html>odd-indexed <br/>rows contain <br/>only parsnips");
        checkboxLabelOdd.setFont(new Font("SansSerif", Font.BOLD, 11));
        checkboxLabelOdd.setForeground(Color.BLACK);

        background.add(checkboxLabelEven);
        background.add(checkboxLabelOdd);

        createProfitDisplay(background);
        createWheatDisplay(background);
        createParsnipDisplay(background);

        createEmptyFarmMessageDisplay(background);
        createButtons(background);
        createFarmPanel(background);
        updateFarmDisplay();

        gamePanel.add(background);

    }

    private void createProfitDisplay(JLabel background) {

        java.net.URL profit_imgURL = getClass().getResource("/assets/profit.png");
        ImageIcon profitIcon = new ImageIcon(profit_imgURL);
        Image scaledProfitIcon = profitIcon.getImage().getScaledInstance(300, 48, Image.SCALE_SMOOTH);
        profitIcon = new ImageIcon(scaledProfitIcon);

        profitLabel = new JLabel(profitIcon);
        profitLabel.setBounds(620, 20, 300, 48);
        profitLabel.setLayout(null);
        
        profitValue = new JLabel("$0");
        profitValue.setBounds(125, 9, 100, 30);
        profitValue.setFont(new Font("SansSerif", Font.BOLD, 22));

        profitLabel.add(profitValue);
        background.add(profitLabel);

    }

    private void createWheatDisplay(JLabel background) {

        java.net.URL wheatCounter_imgURL = getClass().getResource("/assets/wheatCounter.png");
        ImageIcon wheatCounterIcon = new ImageIcon(wheatCounter_imgURL);
        Image scaledWheatCounterIcon = wheatCounterIcon.getImage().getScaledInstance(1050, 350, Image.SCALE_SMOOTH);
        wheatCounterIcon = new ImageIcon(scaledWheatCounterIcon);

        wheatCounterLabel = new JLabel(wheatCounterIcon);
        wheatCounterLabel.setBounds(-410, -120, 1050, 350);
        wheatCounterLabel.setLayout(null);
        
        wheatCounterValue = new JLabel("0");
        wheatCounterValue.setBounds(552, 147, 50, 50);
        wheatCounterValue.setFont(new Font("SansSerif", Font.BOLD, 22));
        wheatCounterValue.setForeground(Color.BLACK);

        wheatCounterLabel.add(wheatCounterValue);
        
        background.add(wheatCounterLabel);

    }

    private void createParsnipDisplay(JLabel background) {

        java.net.URL parsnipCounter_imgURL = getClass().getResource("/assets/parsnipCounter.png");
        ImageIcon parsnipCounterIcon = new ImageIcon(parsnipCounter_imgURL);
        Image scaledParsnipCounterIcon = parsnipCounterIcon.getImage().getScaledInstance(1050, 350, Image.SCALE_SMOOTH);
        parsnipCounterIcon = new ImageIcon(scaledParsnipCounterIcon);

        parsnipCounterLabel = new JLabel(parsnipCounterIcon);
        parsnipCounterLabel.setBounds(-230, -120, 1050, 350);
        parsnipCounterLabel.setLayout(null);
        
        parsnipCounterValue = new JLabel("0");
        parsnipCounterValue.setBounds(570, 147, 50, 50);
        parsnipCounterValue.setFont(new Font("SansSerif", Font.BOLD, 22));
        parsnipCounterValue.setForeground(Color.BLACK);

        parsnipCounterLabel.add(parsnipCounterValue);
        
        background.add(parsnipCounterLabel);

    }

    private void createEmptyFarmMessageDisplay(JLabel background) {

        java.net.URL emptyFarmMessage_imgURL = getClass().getResource("/assets/your-farm-is-empty.png");
        ImageIcon emptyFarmMessageIcon = new ImageIcon(emptyFarmMessage_imgURL);
        Image scaledEmptyFarmMessageIcon = emptyFarmMessageIcon.getImage().getScaledInstance(1050, 300, Image.SCALE_SMOOTH);
        emptyFarmMessageIcon = new ImageIcon(scaledEmptyFarmMessageIcon);

        emptyFarmMessageLabel = new JLabel(emptyFarmMessageIcon);
        emptyFarmMessageLabel.setBounds(-320, 330, 1050, 300);
        emptyFarmMessageLabel.setLayout(null);
        
        background.add(emptyFarmMessageLabel);

    }

    private String rowDialogue(){
        String rowInput = JOptionPane.showInputDialog(gamePanel, "Select row to plant on (0-4):");
        if (rowInput == null){
            return null;
        }

        int row;
        if(Pattern.matches("^[0-9]+$", rowInput)){
            row = Integer.parseInt(rowInput);
        }else{
            JOptionPane.showMessageDialog(gamePanel, "Please enter a valid row number between 0 and 4.");
            return rowDialogue();
        }

        if (row < 0 || row > 4){
            JOptionPane.showMessageDialog(gamePanel, "Please enter a valid row number between 0 and 4.");
            return rowDialogue();
        }
        if (row >= 0 && row <= 4){
            return rowInput;
        }
        return null;
    }

    private String amountDialogue(){
        String amountInput = JOptionPane.showInputDialog(gamePanel, "Amount to plant:");
        if (amountInput == null){
            return null;
        }

        int amount;
        if(Pattern.matches("^[0-9]+$", amountInput)){
            amount = Integer.parseInt(amountInput);
        }else{
            JOptionPane.showMessageDialog(gamePanel, "Please enter a valid number greater than 0.");
            return amountDialogue();
        }

        if (amount <= 0){
            JOptionPane.showMessageDialog(gamePanel, "Please enter a valid number greater than 0.");
            return amountDialogue();
        }
        if (amount > 0){
            return amountInput;
        }
        return null;
    }

    private void createButtons(JLabel background) {
        
        java.net.URL plant_imgURL = getClass().getResource("/assets/plant.png");
        ImageIcon plantIcon = new ImageIcon(plant_imgURL);
        Image scaledPlantButton = plantIcon.getImage().getScaledInstance(209, 51, Image.SCALE_SMOOTH);
        plantIcon = new ImageIcon(scaledPlantButton);

        plantButton = new JButton(plantIcon);
        plantButton.setBounds(100, 120, 209, 51);

        plantButton.addActionListener(e -> {
            int cropCounter = 0;
            int row;
            int amount;

            String crop = JOptionPane.showInputDialog(gamePanel, "Plant a crop (\"wheat\" or \"parsnip\"):");
            if (crop == null){
                return;
            }
            if (crop.equals("wheat") || crop.equals("parsnip")) {
                String rowInput = rowDialogue();
                if (rowInput == null){
                    return;
                }

                row = Integer.parseInt(rowInput);

                if (row >= 0 && row <= 4) {
                    String amountInput = amountDialogue();
                    if (amountInput == null){
                        return;
                    }

                    amount = Integer.parseInt(amountInput);

                    if (amount > 0){
                        if (amount > 5){
                            amount = 5;
                        }

                        FarmTile[][] tiles = farm.getFarmTiles();
                        for (int i = 0; i < tiles.length; i++){
                            if (tiles[row][i].getCrop() != null){
                                cropCounter++;
                            }
                        }

                        if (cropCounter + amount > 5){
                                amount = 5 - cropCounter;
                        }

                        if (cropCounter == 5){
                            JOptionPane.showMessageDialog(gamePanel, "Row is full!");
                        }
                        
                        farm.plantCrops(crop, row, amount);
                        updateFarmDisplay();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(gamePanel, "Please type in the name of the crop you wish to plant exactly as shown.");
            }

        });
        
        background.add(plantButton);

        java.net.URL water_imgURL = getClass().getResource("/assets/water.png");
        ImageIcon waterIcon = new ImageIcon(water_imgURL);
        Image scaledWaterButton = waterIcon.getImage().getScaledInstance(209, 51, Image.SCALE_SMOOTH);
        waterIcon = new ImageIcon(scaledWaterButton);

        waterButton = new JButton(waterIcon);
        waterButton.setBounds(100, 205, 209, 51);

        waterButton.addActionListener(e -> {
            farm.waterFarm();
            updateFarmDisplay();
        });
        
        background.add(waterButton);

        java.net.URL grow_imgURL = getClass().getResource("/assets/grow.png");
        ImageIcon growIcon = new ImageIcon(grow_imgURL);
        Image scaledGrowButton = growIcon.getImage().getScaledInstance(209, 51, Image.SCALE_SMOOTH);
        growIcon = new ImageIcon(scaledGrowButton);

        growButton = new JButton(growIcon);
        growButton.setBounds(100, 290, 209, 51);

        growButton.addActionListener(e -> {
            farm.growCrops();
            updateFarmDisplay();
        });
        
        background.add(growButton);

        java.net.URL harvest_imgURL = getClass().getResource("/assets/harvest.png");
        ImageIcon harvestIcon = new ImageIcon(harvest_imgURL);
        Image scaledHarvestButton = harvestIcon.getImage().getScaledInstance(209, 51, Image.SCALE_SMOOTH);
        harvestIcon = new ImageIcon(scaledHarvestButton);

        harvestButton = new JButton(harvestIcon);
        harvestButton.setBounds(100, 375, 209, 51);

        harvestButton.addActionListener(e -> {
            farm.harvestCrops();
            wheatCounter = 0;
            wheatCounterValue.setText("0");
            parsnipCounter = 0;
            parsnipCounterValue.setText("0");
            updateFarmDisplay();
        });
        
        background.add(harvestButton);

    }

    private void createFarmPanel(JLabel background) {

        farmPanel = new JPanel(new GridLayout(5, 5, 0, 0));
        farmPanel.setBounds(395, 95, 400, 400);

        groundLabels = new JLabel[5][5];
        cropLabels = new JLabel[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JLayeredPane tile = new JLayeredPane();
                tile.setPreferredSize(new Dimension(80, 80));

                JLabel ground = new JLabel();
                ground.setBounds(0, 0, 80, 80);

                JLabel crop = new JLabel();
                crop.setBounds(0, 0, 80, 80);

                groundLabels[i][j] = ground;
                cropLabels[i][j] = crop;

                tile.add(ground, Integer.valueOf(0));
                tile.add(crop, Integer.valueOf(1));

                farmPanel.add(tile);
            }
        }

        background.add(farmPanel);

    }

    private ImageIcon loadImage(String fileName) {
        java.net.URL imgURL = getClass().getResource("/assets/" + fileName);
        ImageIcon icon = new ImageIcon(imgURL);
        Image scaledIcon = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledIcon);

        return icon;
    }


    private void updateFarmDisplay() {

        boolean farmEmpty = true;

        wheatCounter = 0;
        parsnipCounter = 0;

        FarmTile[][] tiles = farm.getFarmTiles();

        String[][] cropsInRow = new String[5][5];
        boolean[] rowHasAppCrop = new boolean[5];
        boolean[] rowIsNull = {true, true, true, true, true};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                FarmTile tile = tiles[i][j];

                if (tile.getCrop() != null){
                    farmEmpty = false;
                    rowIsNull[i] = false;
                }

                if (tile.getWateredStatus()) {
                    groundLabels[i][j].setIcon(wetTile);
                    if (tile.getCrop() != null){}
                } else {
                    groundLabels[i][j].setIcon(dryTile);
                }

                if (tile.getCrop() == null) {
                    cropLabels[i][j].setIcon(null);
                    cropsInRow[i][j] = "null";
                } else if (tile.getCrop().getCropName().equals("wheat")) {

                    cropsInRow[i][j] = "wheat";
                    wheatCounter++;
                    wheatCounterValue.setText(String.valueOf(wheatCounter));
                    
                    if (tile.getCrop().getCropStatus().equals("seed")) {
                        cropLabels[i][j].setIcon(seedWheat);
                    } else {
                        cropLabels[i][j].setIcon(ripeWheat);
                    }

                } else {

                    cropsInRow[i][j] = "parsnip";
                    parsnipCounter++;
                    parsnipCounterValue.setText(String.valueOf(parsnipCounter));

                    if (tile.getCrop().getCropStatus().equals("seed")) {
                        cropLabels[i][j].setIcon(seedParsnip);
                    } else {
                        cropLabels[i][j].setIcon(ripeParsnip);
                    }

                }
            }
        }

        

        boolean hasWrongCrop;
        boolean correctCropExists;
        for (int i = 0; i < 5; i++){
            hasWrongCrop = false;
            correctCropExists = false;
            for (int j = 0; j < 5; j++){

                if (i % 2 == 0){
                    if (cropsInRow[i][j].contains("parsnip")){
                        hasWrongCrop = true;
                    }else{
                        if (cropsInRow[i][j].contains("wheat")){
                            correctCropExists = true;
                        }
                    }
                
                }else{
                    if (cropsInRow[i][j].contains("wheat")){
                        hasWrongCrop = true;
                    }else{
                        if (cropsInRow[i][j].contains("parsnip")){
                            correctCropExists = true;
                        }
                    }

                }

            }

            if (correctCropExists && (hasWrongCrop == false)){
                rowHasAppCrop[i] = true;
            }else{
                rowHasAppCrop[i] = false;
            }
            
        }

        if (!(rowIsNull[0] && rowIsNull[2] && rowIsNull[4])
            && (rowHasAppCrop[0] || rowIsNull[0])
            && (rowHasAppCrop[2] || rowIsNull[2])
            && (rowHasAppCrop[4] ||  rowIsNull[4])
        ){
            checkboxLabelEven.setIcon(scaledCheckedBoxIcon);
        }else{
            checkboxLabelEven.setIcon(scaledUncheckedBoxIcon);
        }

        if ((rowHasAppCrop[1] && rowHasAppCrop[3])
            || (rowHasAppCrop[1] && rowIsNull[3])
            || (rowHasAppCrop[3] && rowIsNull[1])
        ){
                checkboxLabelOdd.setIcon(scaledCheckedBoxIcon);
        }else{
            checkboxLabelOdd.setIcon(scaledUncheckedBoxIcon);
        }

        if (farmEmpty){
            emptyFarmMessageLabel.setVisible(true);
        }else{
            emptyFarmMessageLabel.setVisible(false);
        }

        profitValue.setText("$" + farm.getProfit());
        profitValue.setForeground(new Color(34, 17, 34));
        profitValue.setFont(new Font("Comic Sans", Font.BOLD, 20));
    }

    public static void main(String[] args) {
        new Driver();
    }

}