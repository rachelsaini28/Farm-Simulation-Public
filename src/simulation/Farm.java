package simulation;

/**
 * This is the Farm class.
 * 
 * It contains most of the methods you will be calling in
 * your JUnit test cases that allow you to create and simulate
 * managing a farm.
 * 
 * @author Rachel Saini
 * @author Hemadharshinii Sendhilvel
 */

public class Farm {
    
    private int length;
    private int width;
    private int profit;
    private FarmTile[][] farmTiles;


    /**
     * The constructor takes in a 2D character array and
     * calls the createFarm() method to create a 2D farmTiles array.
     * 
     * @param charFarmTemplate --> 2D character array
     */
    public Farm(char[][] charFarmTemplate){
        this.farmTiles = createFarm(charFarmTemplate);
        this.length = this.farmTiles.length;
        this.width = this.farmTiles[0].length;
        this.profit = 0;
    }

    /**
     * This method returns a 2D FarmTile array from a 2D character array.
     * 
     * Each character in the character array represents a tile with certain attributes
     * in the FarmTile array.
     * 
     * Each character is checked, and the appropriate characteristics are attributed
     * to the current FarmTile.
     * 
     * @param charFarm --> 2D character array
     * @return --> 2D FarmTile array
     */
    public FarmTile[][] createFarm(char[][] charFarm){

        FarmTile[][] tileFarm = new FarmTile[charFarm.length][charFarm[0].length];
            
        for (int i = 0; i < tileFarm.length; i++){
            for (int j = 0; j < tileFarm[0].length; j++){

                if (charFarm[i][j] == 'E'){
                    tileFarm[i][j] = new FarmTile(null, false);
                    
                }else if (charFarm[i][j] == 'W'){
                    Crop newCrop = new Crop("wheat", "seed");
                    tileFarm[i][j] = new FarmTile(newCrop, false);

                }else if (charFarm[i][j] == 'R'){
                    Crop newCrop = new Crop("wheat", "ripe");
                    tileFarm[i][j] = new FarmTile(newCrop, false);
                     
                }else if (charFarm[i][j] == 'P'){
                    Crop newCrop = new Crop("parsnip", "seed");
                    tileFarm[i][j] = new FarmTile(newCrop, false);

                }else if (charFarm[i][j] == 'Q'){
                    Crop newCrop = new Crop("parsnip", "ripe");
                    tileFarm[i][j] = new FarmTile(newCrop, false);
                        
                }else if (charFarm[i][j] == 'S'){
                    tileFarm[i][j] = new FarmTile(null, true);
                        
                }else if (charFarm[i][j] == 'X'){
                    Crop newCrop = new Crop("wheat", "seed");
                    tileFarm[i][j] = new FarmTile(newCrop, true);
                        
                }else if (charFarm[i][j] == 'Y'){
                    Crop newCrop = new Crop("wheat", "ripe");
                    tileFarm[i][j] = new FarmTile(newCrop, true);
                        
                }else if (charFarm[i][j] == 'A'){
                    Crop newCrop = new Crop("parsnip", "seed");
                    tileFarm[i][j] = new FarmTile(newCrop, true);

                }else if (charFarm[i][j] == 'B'){
                    Crop newCrop = new Crop("parsnip", "ripe");
                    tileFarm[i][j] = new FarmTile(newCrop, true);
                        
                }else{
                    return null;
                }
            }
        }
        return tileFarm;
    }


    /**
     * This method plants crops on the farm.
     * 
     * It traverses through each tile of the desired row and adds
     * the inputted crop on an empty FarmTile. If a tile already has
     * a crop, it gets skipped and its current planted crop remains.
     * 
     * The amount of seeds to plant gets capped off at the last tile in a row.
     * 
     * @param cropName --> name of crop ("wheat" or "parsnip")
     * @param row --> the row number to plant on
     * @param amount --> the number of seeds to plant
     */
    public void plantCrops(String cropName, int row, int amount) {

        if (row < 0 || row >= this.width || amount <= 0){
            return;
        }

        FarmTile tile;
        for (int j = 0; j < this.length; j++){
            tile = this.farmTiles[row][j];

            if (tile.getCrop() == null){
                tile.addCrop(cropName, "seed");
                amount--;
            }

            if (amount == 0){
                break;
            }
        }
    }


    /**
     * This method waters all tiles on a farm.
     * 
     * The farm is traversed and each tile's watered status is set to true.
     */
    public void waterFarm(){
        for (int i = 0; i < this.length; i++){
            for (int j = 0; j < this.width; j++){
                this.farmTiles[i][j].setWateredStatus(true);
            }
        }
    }


    /**
     * This method grows all planted crops on a farm.
     * 
     * The farm is traversed and checks with tiles contain a seedling and are wet.
     * Those crops will then grow into the ripe stage and the tile will be marked dry.
     */
    public void growCrops(){
        FarmTile tile;
        for (int i = 0; i < this.length; i++){
            for (int j = 0; j < this.width; j++){
                
                tile = this.farmTiles[i][j];
                Crop crop = tile.getCrop();

                if (crop != null && tile.getWateredStatus() && crop.getCropStatus().equals("seed")){
                    crop.setCropStatus("ripe");
                }
                tile.setWateredStatus(false);
            }
        }
    }


    /**
     * This method harvests all ripe crops.
     * 
     * The farm is traversed and checks which tiles contain a ripe crop.
     * Those with a ripe crop are harvested and the tile's crop is set to null.
     * 
     * Profit is adjusted accordingly depending on the crop.
     */
    public void harvestCrops(){
        FarmTile tile;
        for (int i = 0; i < this.length; i++){
            for (int j = 0; j < this.width; j++){
                tile = this.farmTiles[i][j];

                if (tile.getCrop() != null){
                    if (tile.getCrop().getCropStatus().equals("ripe")){
                        if (tile.getCrop().getCropName().equals("wheat")){
                            this.profit += 6;
                        }else{
                            this.profit += 3;
                        }
                        tile.setCrop(null);
                    }
                }
            }
        }
    }


    public FarmTile[][] getFarmTiles(){
        return this.farmTiles;
    }


    public int getProfit(){
        return this.profit;
    }


    public int getLength(){
        return this.length;
    }


    public int getWidth(){
        return this.width;
    }

}
