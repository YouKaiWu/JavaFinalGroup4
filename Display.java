import java.util.ArrayList;

public class Display {
    private static void printFormat(String s, int length) {
        String format = " %-" + Integer.toString(length) + "s";
        System.out.printf(format, s);
    }

    private static void printFormat(int num, int length) {
        String format = " %-" + Integer.toString(length) + "d";
        System.out.printf(format, num);
    }

    private static void printSpace() {
        System.out.print(" ");
    }

    public static void printTitle() {
        printFormat("[ID]", 4);
        if (DataBase.configs.get("show_name").equals("true")) {
            printSpace();
            printFormat("[Name]", 12);
        }
        if (DataBase.configs.get("show_brand").equals("true")) {
            printSpace();
            printFormat("[Brand]", 12);
        }
        if (DataBase.configs.get("show_catalog").equals("true")) {
            printSpace();
            printFormat("[Catalog]", 12);
        }
        if (DataBase.configs.get("show_count").equals("true")) {
            printSpace();
            printFormat("[Count]", 7);
        }
        if (DataBase.configs.get("show_weight").equals("true")) {
            printSpace();
            printFormat("[Weight]", 8);
        }
        if (DataBase.configs.get("show_price").equals("true")) {
            printSpace();
            printFormat("[Price]", 7);
        }
        System.out.println();
    }

    public static void printData(Data data) {
        System.out.printf("%04d", data.id);
        if (DataBase.configs.get("show_name").equals("true")) {
            printSpace();
            printFormat(data.name, 12);
        }
        if (DataBase.configs.get("show_brand").equals("true")) {
            printSpace();
            printFormat(data.brand, 12);
        }
        if (DataBase.configs.get("show_catalog").equals("true")) {
            printSpace();
            printFormat(data.catalog, 12);
        }
        if (DataBase.configs.get("show_count").equals("true")) {
            printSpace();
            printFormat(data.count, 7);
        }
        if (DataBase.configs.get("show_weight").equals("true")) {
            printSpace();
            printFormat(data.weight, 8);
        }
        if (DataBase.configs.get("show_price").equals("true")) {
            printSpace();
            printFormat(data.price, 7);
        }
        System.out.println();
    }

    public static void showAll() {
        printTitle();
        ArrayList<Data> tmp = new ArrayList<>();
        tmp = DataManage.sortedData(DataBase.datas, DataBase.configs.get("show_sort_field"));
        for (Data data : tmp) {
            printData(data);
        }
    }

    public static void showRawData() {
        printFormat("[ID]", 4);
        printSpace();
        printFormat("[Name]", 12);
        printSpace();
        printFormat("[Brand]", 12);
        printSpace();
        printFormat("[Catalog]", 12);
        printSpace();
        printFormat("[Count]", 7);
        printSpace();
        printFormat("[Weight]", 8);
        printSpace();
        printFormat("[Price]", 7);
        System.out.println();
        for (Data data : DataBase.datas) {
            printData(data);
        }
    }

    public static void showCatalogforSearch() {
        System.out.print("Catalogs:");
        for (int i = 1; i <= DataBase.catalogs.size(); i++)
            System.out.print("[" + i + "]." + DataBase.catalogs.get(i - 1) + " ");
        System.out.println();
    }

    public static void showCatalog() {
        System.out.println("[Catalog]");
        ArrayList<String> tmp = new ArrayList<>();
        tmp = DataManage.sortedCatalog(DataBase.catalogs);
        for (String catalog : tmp) {
            System.out.println(catalog);
        }
    }

    public static void showByCatalog() {
        System.out.println("Catalogs:");
        for (int i = 1; i <= DataBase.catalogs.size(); i++)
            System.out.print("[" + i + "]." + DataBase.catalogs.get(i - 1) + " ");
        System.out.println();
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        System.out.println("Input_catalog_to_show:");
        String catalog;
        while (true) {
            catalog = Main.sc.nextLine();
            if (catalog.equals("0"))
                return;
            else if (catalog.equals("99"))
                System.exit(0);
            else if (Integer.valueOf(catalog) > 0 && Integer.valueOf(catalog) <= DataBase.catalogs.size())
                break;
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        catalog = DataBase.catalogs.get(Integer.valueOf(catalog) - 1);
        ArrayList<Data> datas = new ArrayList<>();
        for (int i = 0; i < DataBase.datas.size(); i++) {
            if (catalog.equals(DataBase.datas.get(i).catalog)) {
                datas.add(DataBase.datas.get(i));
            }
        }
        datas = DataManage.sortedData(datas, DataBase.configs.get("show_sort_field"));
        printTitle();
        for (Data data : datas) {
            printData(data);
        }
    }

    public static int boolToInt(String str) {
        if (str.equals("true"))
            return 1;
        else
            return 0;
    }

    public static void showColumnConfig() {
        System.out.print("[1].Show_name:" + boolToInt(DataBase.configs.get("show_name")));
        System.out.print(" [2].Show_brand:" + boolToInt(DataBase.configs.get("show_brand")));
        System.out.print(" [3].Show_catalog:" + boolToInt(DataBase.configs.get("show_catalog")));
        System.out.print(" [4].Show_count:" + boolToInt(DataBase.configs.get("show_count")));
        System.out.print(" [5].Show_weight:" + boolToInt(DataBase.configs.get("show_weight")));
        System.out.print(" [6].Show_price:" + boolToInt(DataBase.configs.get("show_price")));
        System.out.println();
    }

    public static void showSortOrderConfig() {
        System.out.println("show_sort_order:" + DataBase.configs.get("show_sort_order"));
    }

    public static void showAccount() {
        Display.printFormat("[Account]", 12);
        Display.printFormat("[Password]", 12);
        System.out.println();
        for (String account : DataBase.accounts.keySet()) {
            Display.printFormat(account, 12);
            Display.printFormat(DataBase.accounts.get(account), 12);
            System.out.println();
        }
    }

    public static void printOnePage(int currentPage, int dataPerPage, ArrayList<Data> datas) {
        int start = currentPage * dataPerPage;
        for (int i = start; i < Math.min(datas.size(), (start + dataPerPage)); i++)
            printData(datas.get(i));
    }

    public static void showByPage() {
        System.out.println("Choose_show_per_page:");
        System.out.println("[3].3_data_per_page [5].5_data_per_page [10].10_data_per_page");
        System.out.println("[d].default [0].Go_back_to_main_menu [99].Exit_system");
        int dataPerPage = 0;
        String cmd = Main.sc.nextLine();
        if (cmd.equals("3") || cmd.equals("5") || cmd.equals("10"))
            dataPerPage = Integer.valueOf(cmd);
        else if (cmd.equals("0"))
            return;
        else if (cmd.equals("99"))
            System.exit(0);
        else if (cmd.equals("d")) {
            dataPerPage = Integer.valueOf(DataBase.configs.get("show_defalt_perpage"));
        }
        int currentPage = 0;
        int lastPage = (DataBase.datas.size() - 1) / dataPerPage;
        ArrayList<Data> datas = new ArrayList<>();
        datas = DataManage.sortedData(DataBase.datas, DataBase.configs.get("show_sort_field"));
        while (true) {
            printOnePage(currentPage, dataPerPage, datas);
            String submenu = "";
            if (currentPage != 0) {
                submenu += "[1].Last_page";
            }
            if (currentPage != lastPage) {
                submenu += " [2].Next_page";
            }
            submenu += " [0].Go_back_to_main_menu [99].Exit_system";
            System.out.println(submenu.trim());
            String Pagecmd;
            while (true) {
                Pagecmd = Main.sc.nextLine();
                if (Pagecmd.equals("0"))
                    return;
                else if (Pagecmd.equals("99"))
                    System.exit(0);
                else if (Pagecmd.equals("1")) {
                    currentPage--;
                    break;
                } else if (Pagecmd.equals("2")) {
                    currentPage++;
                    break;
                } else {
                    System.out.println("Error_wrong_command");
                    System.out.println("Please_enter_again:");
                }
            }
        }
    }
}