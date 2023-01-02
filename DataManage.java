import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManage {

    public static ArrayList<String> sortedCatalog(ArrayList<String> catalogs) {
        ArrayList<String> tmp = new ArrayList<>();
        tmp = catalogs;
        Collections.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        return tmp;
    }

    public static ArrayList<Data> sortedData(ArrayList<Data> data, String field) {
        ArrayList<Data> tmp = new ArrayList<>();
        tmp = data;
        switch (field) {
            case "id":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.id - b.id;
                        return b.id - a.id;
                    }
                });
                break;
            case "Name":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.name.compareTo(b.name);
                        return b.name.compareTo(a.name);
                    }
                });
                break;
            case "Brand":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.brand.compareTo(b.brand);
                        return b.name.compareTo(a.name);
                    }
                });
                break;
            case "Catalog":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.catalog.compareTo(b.catalog);
                        return b.catalog.compareTo(a.catalog);
                    }
                });
                break;
            case "Count":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.count - b.count;
                        return b.count - a.count;
                    }
                });
                break;
            case "Weight":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.weight - b.weight;
                        return b.weight - a.weight;
                    }
                });
                break;
            case "Price":
                Collections.sort(tmp, new Comparator<Data>() {
                    public int compare(Data a, Data b) {
                        if (DataBase.configs.get("show_sort_order").equals("asc"))
                            return a.price - b.price;
                        return b.price - a.price;
                    }
                });
                break;
        }
        return tmp;
    }

    public static void addCatalog() {
        String newCatalog;
        while (true) {
            System.out.println("Please_input_new_catalog:");
            newCatalog = Main.sc.nextLine();
            if (DataBase.catalogs.contains(newCatalog))
                System.out.println("Error_catalog_existed");
            if (newCatalog.length() > 12)
                System.out.println("Error_catalog_too_long");
            if (!DataBase.catalogs.contains(newCatalog) && !(newCatalog.length() > 12))
                break;
        }
        newCatalog = newCatalog.substring(0, 1).toUpperCase() + newCatalog.substring(1);
        DataBase.catalogs.add(newCatalog);
        System.out.println("Add_catalog_" + newCatalog + "_success");
    }

    public static boolean check(String str, int len) {
        String regex = "[^0-9]{1," + len + "}";
        return str.matches(regex);
    }

    public static boolean check(int str, int len) {
        String regex = "\\d{1," + len + "}";
        return Integer.toString(str).matches(regex);
    }

    public static void search() {
        while (true) {
            System.out.println("Search by:");
            System.out.println("[1].ID [2].Name [3].Brand");
            System.out.println("[0].Go_back_to_main_menu [99].Exit_system");
            System.out.println("Input_target:");
            String cmd = Main.sc.nextLine();
            String info;
            ArrayList<Data> datas = new ArrayList<>();
            switch (cmd) {
                case "1":
                    while (true) {
                        info = Main.sc.nextLine();
                        if (check(Integer.valueOf(info), 4)) {
                            for (Data data : DataBase.datas) {
                                if (data.id == Integer.valueOf(info)) {
                                    datas.add(data);
                                }
                            }
                            break;
                        }
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                    break;
                case "2":
                    while (true) {
                        info = Main.sc.nextLine();
                        if (check(info, 12)) {
                            for (Data data : DataBase.datas) {
                                if (data.name.equals(info)) {
                                    datas.add(data);
                                }
                            }
                            break;
                        }
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                    break;
                case "3":
                    while (true) {
                        info = Main.sc.nextLine();
                        if (check(info, 12)) {
                            for (Data data : DataBase.datas) {
                                if (data.brand.equals(info)) {
                                    datas.add(data);
                                }
                            }
                            break;
                        }
                        System.out.println("Error_wrong_data");
                        System.out.println("Please_input_again:");
                    }
                    break;
                case "99":
                    System.exit(0);
                    break;
            }
            if (datas.size() == 0) {
                System.out.println("Error_no_result");
            } else {
                System.out.println("Search_result:");
                Display.printTitle();
                for (Data data : datas) {
                    Display.printData(data);
                }
            }
            System.out.println("[1].Restart_search [0].Go_back_to_main_menu [99].Exit_system");
            String submenuCmd = Main.sc.nextLine();
            if (submenuCmd.equals("0")) {
                return;
            } else if (submenuCmd.equals("99")) {
                System.exit(0);
            }
        }

    }

    public static void addContact() {
        System.out.println("Name:");
        String name;
        while (true) {
            name = Main.sc.nextLine();
            if (check(name, 12)) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        System.out.println("Brand:");
        String brand;
        while (true) {
            brand = Main.sc.nextLine();
            if (check(brand, 12)) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        Display.showCatalogforSearch();
        System.out.println("Catalog:");
        String catalog;
        while (true) {
            catalog = Main.sc.nextLine();
            if (Integer.valueOf(catalog) > 0 && Integer.valueOf(catalog) <= DataBase.catalogs.size()) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        catalog = DataBase.catalogs.get(Integer.valueOf(catalog) - 1);
        System.out.println("Count:");
        String count;
        while (true) {
            count = Main.sc.nextLine();
            if (check(Integer.valueOf(count), 7)) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        System.out.println("Weight:");
        String weight;
        while (true) {
            weight = Main.sc.nextLine();
            if (check(Integer.valueOf(weight), 6)) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        System.out.println("Price:");
        String price;
        while (true) {
            price = Main.sc.nextLine();
            if (check(Integer.valueOf(price), 6)) {
                break;
            }
            System.out.println("Error_wrong_data");
            System.out.println("Please_input_again:");
        }
        Data data = new Data();
        data.id = DataBase.datas.size() + 1;
        data.name = name;
        data.brand = brand;
        data.catalog = catalog;
        data.count = Integer.valueOf(count);
        data.weight = Integer.valueOf(weight);
        data.price = Integer.valueOf(price);
        DataBase.datas.add(data);
        System.out.println("Add_contact_success");
    }

    public static void deleteContact() {
        System.out.println("Input_ID_to_be_deleted:");
        String id = Main.sc.nextLine();
        for (int i = 0; i < DataBase.datas.size(); i++) {
            if (Integer.valueOf(id) == DataBase.datas.get(i).id) {
                DataBase.datas.remove(i);
                System.out.println("Delete_data_success");
                return;
            }
        }
        System.out.println("Error_no_such_data");
    }

    public static void modifyContact() {
        System.out.println("Input_ID_to_be_modified:");
        System.out.println("Search_result:");
        String id = Main.sc.nextLine();
        for (int i = 0; i < DataBase.datas.size(); i++) {
            if (Integer.valueOf(id) == DataBase.datas.get(i).id) {
                Display.printTitle();
                Display.printData(DataBase.datas.get(i));
                System.out.println("New_name:");
                String name = Main.sc.nextLine();
                if (name.length() != 0)
                    DataBase.datas.get(i).name = name;
                System.out.println("New_brand:");
                String brand = Main.sc.nextLine();
                if (brand.length() != 0)
                    DataBase.datas.get(i).brand = brand;
                Display.showCatalogforSearch();
                System.out.println("New_catalog:");
                String catalog = Main.sc.nextLine();
                if (catalog.length() != 0)
                    DataBase.datas.get(i).catalog = DataBase.catalogs.get(Integer.valueOf(catalog) - 1);
                System.out.println("New_count:");
                String count = Main.sc.nextLine();
                if (count.length() != 0)
                    DataBase.datas.get(i).count = Integer.valueOf(count);
                System.out.println("New_weight:");
                String weight = Main.sc.nextLine();
                if (weight.length() != 0)
                    DataBase.datas.get(i).weight = Integer.valueOf(weight);
                System.out.println("New_price:");
                String price = Main.sc.nextLine();
                if (price.length() != 0)
                    DataBase.datas.get(i).price = Integer.valueOf(price);
            }
        }
        System.out.println("Modify_data_success");
    }

    public static void dataOptimize() {
        System.out.println("Please_confirm_data_optimize_y_or_n:");
        String cmd = Main.sc.nextLine();
        if (cmd.equals("y")) {
            System.out.println("Data_optimize_success");
            DataBase.datas = DataManage.sortedData(DataBase.datas, DataBase.configs.get("show_sort_field"));
        } else
            System.out.println("Data_optimize_denied");
    }

    public static void addAccount() {
        System.out.println("New_account:");
        String account = Main.sc.nextLine();
        System.out.println("New_password:");
        String password = Main.sc.nextLine();
        DataBase.accounts.put(account, password);
    }

    public static void deleteAccount() {
        System.out.println("Delete_account:");
        while (true) {
            String account = Main.sc.nextLine();
            if (DataBase.accounts.containsKey(account)) {
                DataBase.accounts.remove(account);
                System.out.println("Delete_account_success");
                return;
            }
            System.out.println("No_account_please_try_again:");
        }
    }

    public static void modifyAccount() {
        System.out.println("Modify_account:");
        while (true) {
            String account = Main.sc.nextLine();
            if (DataBase.accounts.containsKey(account)) {
                System.out.println("New_account:");
                String newAccount = Main.sc.nextLine();
                System.out.println("New_password:");
                String newPassword = Main.sc.nextLine();
                DataBase.accounts.remove(account);
                DataBase.accounts.put(newAccount, newPassword);
                System.out.println("Modify_account_success");
                return;
            }
            System.out.println("No_account_please_try_again:");
        }
    }
}