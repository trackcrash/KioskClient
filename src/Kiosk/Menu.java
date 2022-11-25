package Kiosk;

public class Menu {
    private int menuID;
    private String menuName;
    private String menuDescription;
    private String menuImage;
    private int menuPrice;
    private int count;

    public Menu(int menuID, String menuName, String menuDescription, int menuPrice, int count, String menuImage) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.menuPrice = menuPrice;
        this.menuImage = menuImage;
        this.count = count;
    }

    //Getters and Setters
    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

}