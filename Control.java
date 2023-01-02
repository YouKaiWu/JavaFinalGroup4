
public class Control {

    public static void login() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Account:");
            String account = Main.sc.nextLine();
            System.out.println("Password:");
            String password = Main.sc.nextLine();
            System.out.println("Verify_string:" + DataBase.configs.get("verify_string"));
            System.out.println("Input_Verify_string:");
            String verifyString = Main.sc.nextLine();
            if (loginSuccess(account, password, verifyString)) {
                System.out.println("Login_success");
                run = true;
                return;
            } else
                System.out.println("Error_wrong_account_password_or_verify_string");
            if (!loginSuccess(account, password, verifyString) && i == 2)
                System.exit(0);
        }

    }

    private static boolean loginSuccess(String account, String password, String verifyString) {
        if (!DataBase.accounts.containsKey(account) || !DataBase.accounts.get(account).equals(password)
                || !verifyString.equals(DataBase.configs.get("verify_string")))
            return false;
        return true;
    }

    private static boolean mainMenuWrongCmd = false;

    public static void mainMenu() {
        if (!mainMenuWrongCmd) {
            System.out.println("****************************************");
            System.out.println("1.Show_a 2.Show_p 3.Show_by_c 4.Search 5.Mod 6.Del 7.Add_storage");
            System.out.println("8.Add_cat 9.Show_cat 10.Set_field 11.Set_page 12.Set_order 13.Set_sort");
            System.out.println("14.Show_r 15.Opt 16.Show_acc 17.Add_acc 18.Del_acc 19.Mod_acc 20.Logout 99.Exit");
            System.out.println("****************************************");
        }
        String cmd = Main.sc.nextLine();
        switch (cmd) {
            case "1":
                mainMenuWrongCmd = false;
                Display.showAll();
                minorMenu();
                break;
            case "2":
                mainMenuWrongCmd = false;
                Display.showByPage();
                break;
            case "3":
                mainMenuWrongCmd = false;
                Display.showByCatalog();
                minorMenu();
                break;
            case "4":
                mainMenuWrongCmd = false;
                DataManage.search();
                break;
            case "5":
                mainMenuWrongCmd = false;
                DataManage.modifyContact();
                minorMenu();
                break;
            case "6":
                mainMenuWrongCmd = false;
                DataManage.deleteContact();
                minorMenu();
                break;
            case "7":
                mainMenuWrongCmd = false;
                DataManage.addContact();
                minorMenu();
                break;
            case "8":
                mainMenuWrongCmd = false;
                DataManage.addCatalog();
                minorMenu();
                break;
            case "9":
                mainMenuWrongCmd = false;
                Display.showCatalog();
                minorMenu();
                break;
            case "10":
                mainMenuWrongCmd = false;
                Config.updateShowColumn();
                minorMenu();
                break;
            case "11":
                mainMenuWrongCmd = false;
                Config.updateShowDefaltPerpage();
                minorMenu();
                break;
            case "12":
                mainMenuWrongCmd = false;
                Config.updateSortOrder();
                minorMenu();
                break;
            case "13":
                mainMenuWrongCmd = false;
                Config.updateSortField();
                minorMenu();
                break;
            case "14":
                mainMenuWrongCmd = false;
                Display.showRawData();
                minorMenu();
                break;
            case "15":
                mainMenuWrongCmd = false;
                DataManage.dataOptimize();
                minorMenu();
                break;
            case "16":
                mainMenuWrongCmd = false;
                Display.showAccount();
                minorMenu();
                break;
            case "17":
                mainMenuWrongCmd = false;
                DataManage.addAccount();
                minorMenu();
                break;
            case "18":
                mainMenuWrongCmd = false;
                DataManage.deleteAccount();
                minorMenu();
                break;
            case "19":
                mainMenuWrongCmd = false;
                DataManage.modifyAccount();
                minorMenu();
                break;
            case "20":
                mainMenuWrongCmd = false;
                Control.logout();
                break;
            case "99":
                System.exit(0);
            default:
                System.out.println("Error_wrong_command");
                System.out.println("Please_enter_again:");
                mainMenuWrongCmd = true;
        }

    }

    private static boolean minorMenuWrongCmd = false;

    public static void minorMenu() {
        if (!minorMenuWrongCmd)
            System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        while (true) {
            String cmd = Main.sc.nextLine();
            switch (cmd) {
                case "0":
                    minorMenuWrongCmd = false;
                    return;
                case "99":
                    System.exit(0);
                default:
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again:");
                    minorMenuWrongCmd = true;
            }
            System.out.println();
        }
    }

    public static boolean run = false;

    public static void logout() {
        System.out.println("Please_confirm_to_logout_y_or_n:");
        while (true) {
            String cmd = Main.sc.nextLine();
            if (cmd.equals("y")) {
                run = false;
                break;
            } else if (cmd.equals("n"))
                break;
            System.out.println("Error_input");
            System.out.println("Please_input_again:");
        }
    }

}
