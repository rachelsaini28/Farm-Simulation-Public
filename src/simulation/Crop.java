package simulation;

public class Crop {

    private String cropName;
    private String cropStatus;

    public Crop(String cropName, String cropStatus) {
        this.cropName = cropName;
        this.cropStatus = cropStatus;
    }

    public String getCropName() {
        return this.cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropStatus() {
        return this.cropStatus;
    }

    public void setCropStatus(String cropStatus) {
        this.cropStatus = cropStatus;
    }

}
