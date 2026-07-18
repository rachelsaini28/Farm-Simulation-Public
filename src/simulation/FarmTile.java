package simulation;

public class FarmTile {
    
    private Crop crop;
    private boolean watered;

    public FarmTile(Crop crop, boolean watered){
        this.crop = crop;
        this.watered = watered;
    }

    public void addCrop(String name, String status){
        this.crop = new Crop(name, status);
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop(){
        return this.crop;
    }

    public void setWateredStatus(boolean wateredStatus){
        this.watered = wateredStatus;
    }

    public boolean getWateredStatus(){
        return this.watered;
    }

}
