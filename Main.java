import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            FileManage.loadFile();
            Control.login();
            while (Control.run) {
                Control.mainMenu();
                FileManage.saveFile();
            }
        }
    }
}