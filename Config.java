public class Config {
    public static boolean isBool(String str) {
        return str.equals("1") || str.equals("0");
    }

    public static String SingleColumnUpdate(String update) {
        while (true) {
            update = Main.sc.nextLine();
            if (isBool(update)) {
                if (update.equals("0"))
                    update = "false";
                else
                    update = "true";
                return update;
            }
            System.out.println("Input_error_plaese_input_0_or_1:");
        }
    }

    public static void updateShowColumn() {
        Display.showColumnConfig();
        System.out.println("New_show_name(0/1):");
        String showName = "";
        DataBase.configs.put("show_name", SingleColumnUpdate(showName));
        System.out.println("New_show_brand(0/1):");
        String showBrand = "";
        DataBase.configs.put("show_brand", SingleColumnUpdate(showBrand));
        System.out.println("New_show_catalog(0/1):");
        String showCatalog = "";
        DataBase.configs.put("show_catalog", SingleColumnUpdate(showCatalog));
        System.out.println("New_show_count(0/1):");
        String showCount = "";
        DataBase.configs.put("show_count", SingleColumnUpdate(showCount));
        System.out.println("New_show_weight(0/1):");
        String showWeight = "";
        DataBase.configs.put("show_weight", SingleColumnUpdate(showWeight));
        System.out.println("New_show_price(0/1):");
        String showPrice = "";
        DataBase.configs.put("show_price", SingleColumnUpdate(showPrice));
        Display.showColumnConfig();
    }

    public static void updateSortOrder() {
        Display.showSortOrderConfig();
        System.out.println("Please_input_new_sort_order:");
        while (true) {
            String newOrder = Main.sc.nextLine();
            if (newOrder.equals("asc") || newOrder.equals("des")) {
                DataBase.configs.put("show_sort_order", newOrder);
                break;
            }
            System.out.println("Input_error_plaese_input_asc_or_des:");
        }
        Display.showSortOrderConfig();
    }

    public static void updateSortField() {
        System.out.println("[1].ID [2].Name [3].Brand [4].Catalog [5].Count [6].Weight [7].Price");
        System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
        String cmd = Main.sc.nextLine();
        switch (Integer.valueOf(cmd)) {
            case 0:
                return;
            case 1:
                cmd = "id";
                break;
            case 2:
                cmd = "Name";
                break;
            case 3:
                cmd = "Brand";
                break;
            case 4:
                cmd = "Catalog";
                break;
            case 5:
                cmd = "Count";
                break;
            case 6:
                cmd = "Weight";
                break;
            case 7:
                cmd = "Price";
                break;
            case 99:
                System.exit(0);
        }
        DataBase.configs.put("show_sort_field", cmd);
        System.out.println("Sorted_by:" + cmd);
    }

    public static void updateShowDefaltPerpage() {
        System.out.println("show_defalt_perpage:" + DataBase.configs.get("show_defalt_perpage"));
        System.out.println("new_show_defalt_perpage:");
        String defaultPage = Main.sc.nextLine();
        if (defaultPage.length() != 0)
            DataBase.configs.put("show_defalt_perpage", defaultPage);
        System.out.println("show_defalt_perpage:" + DataBase.configs.get("show_defalt_perpage"));
    }
}