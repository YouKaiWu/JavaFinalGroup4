import java.io.*;

public class FileManage {
    public static void loadFile() {
        BufferedReader input = null;
        try {
            // account
            input = new BufferedReader(new FileReader("account.txt"));
            String line = input.readLine();
            while (line != null) {
                String[] list = line.split(" ");
                DataBase.accounts.put(list[0], list[1]);
                line = input.readLine();
            }
            input.close();
            // config
            input = new BufferedReader(new FileReader("config.txt"));
            line = input.readLine();
            while (line != null) {
                String[] list = line.split(":");
                DataBase.configs.put(list[0].trim(), list[1].trim());
                line = input.readLine();
            }
            input.close();
            // data
            input = new BufferedReader(new FileReader("data.txt"));
            line = input.readLine();
            while (line != null) {
                String[] list = line.split(" ");
                Data data = new Data();
                data.id = Integer.valueOf(list[0]);
                data.name = list[1];
                data.brand = list[2];
                data.catalog = list[3];
                data.count = Integer.valueOf(list[4]);
                data.weight = Integer.valueOf(list[5]);
                data.price = Integer.valueOf(list[6]);
                DataBase.datas.add(data);
                line = input.readLine();
            }
            input.close();
            // catalog
            input = new BufferedReader(new FileReader("catalog.txt"));
            line = input.readLine();
            while (line != null) {
                DataBase.catalogs.add(line.trim());
                line = input.readLine();
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile() {
        PrintWriter output = null;
        try {
            // catalog
            output = new PrintWriter(new File("catalog.txt"));
            String content = "";
            for (String catalog : DataBase.catalogs) {
                content += catalog + "\n";
            }
            content = content.substring(0, content.length() - 1);
            output.write(content);
            output.close();
            // data
            output = new PrintWriter(new File("data.txt"));
            content = "";
            for (Data data : DataBase.datas) {
                content += data.id + " " + data.name + " " + data.brand + " " + data.catalog + " " + data.count + " "
                        + data.weight + " " + data.price + "\n";
            }
            content = content.substring(0, content.length() - 1);
            output.write(content);
            output.close();
            // account
            output = new PrintWriter(new File("account.txt"));
            content = "";
            for (String account : DataBase.accounts.keySet()) {
                content += account + " " + DataBase.accounts.get(account) + "\n";
            }
            content = content.substring(0, content.length() - 1);
            output.write(content);
            output.close();
            // config
            output = new PrintWriter(new File("config.txt"));
            content = "";
            for (String config : DataBase.configs.keySet()) {
                content += config+": "+ DataBase.configs.get(config) + "\n";
            }
            content = content.substring(0, content.length() - 1);
            output.write(content);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
