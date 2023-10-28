package model.application;

import model.entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Product> list = new ArrayList<>();
        String path = "C:\\Users\\jonas\\Projeto_Temp\\readFile\\source\\product.csv";
        String pathFolder = "C:\\Users\\jonas\\Projeto_Temp\\readFile\\source";

        try (//bloco try para ler o arquivo e pegar as informações
             BufferedReader br = new BufferedReader(new FileReader(path));


        ) {
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                String[] infoProduct = line.split(",");
                list.add(new Product(infoProduct[0], Double.parseDouble(infoProduct[1]), Integer.parseInt(infoProduct[2])));
                line = br.readLine();

            }
            boolean success = new File(pathFolder + "\\out").mkdir();
            try (    //bloco para criar um arquivo no pasta out criada acima
                     BufferedWriter bf = new BufferedWriter(new FileWriter(pathFolder + "\\out" + "\\summary.csv"))

            ) {
                for (Product item : list) {
                    String nameItem = item.getName();
                    String quantityItem = String.format("%.2f", item.getQuantity() * item.getPrice());
                    String newInfo = String.join(", ", nameItem, quantityItem);
                    bf.write(newInfo);
                    bf.newLine();
                }

            }
            System.out.println("Directory created successfully: " + success);
        } catch (Exception e) {
            System.out.println("enexpered error : " + e.getMessage());
        }
    }
}