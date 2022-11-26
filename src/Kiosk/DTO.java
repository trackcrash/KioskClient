package Kiosk;

public class DTO {
    //DTO
    private int menuID;
    private String menuName;
    private String menuDescription;
    private int menuPrice;
    private int count;
    private String menuImage;



    public DTO(int menuID, String menuName, String menuDescription, int menuPrice, int count, String menuImage) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.menuPrice = menuPrice;
        this.count = count;
        this.menuImage = menuImage;
    }
}
