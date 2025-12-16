package service;

import model.Car;
import model.Doll;
import model.GameRoom;
import model.Toy;
import model.enums.AgeGroup;
import model.enums.Material;
import model.enums.Size;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    /**
     * Зберігає список іграшок у файл.
     */
    public void saveRoom(GameRoom room, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Toy toy : room.getToys()) {
                StringBuilder sb = new StringBuilder();
                // Визначаємо тип іграшки
                if (toy instanceof Car) {
                    sb.append("CAR,");
                } else if (toy instanceof Doll) {
                    sb.append("DOLL,");
                }

                // Додаємо загальні поля
                sb.append(toy.getName()).append(",");
                sb.append(toy.getPrice()).append(",");
                sb.append(toy.getAgeGroup()).append(",");

                // Додаємо специфічні поля
                if (toy instanceof Car) {
                    sb.append(((Car) toy).getSize()); // SMALL, MEDIUM...
                } else if (toy instanceof Doll) {
                    sb.append(((Doll) toy).getMaterial()); // PLASTIC, WOOD...
                }

                // Записуємо рядок у файл
                writer.write(sb.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Завантажує іграшки з файлу.
     */
    public List<Toy> loadRoom(String filename) throws IOException {
        List<Toy> loadedToys = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Очікуємо формат: TYPE,NAME,PRICE,AGE,PARAM
                if (parts.length < 5) continue;

                String type = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                AgeGroup age = AgeGroup.valueOf(parts[3]);
                String param = parts[4];

                if ("CAR".equals(type)) {
                    loadedToys.add(new Car(name, price, age, Size.valueOf(param)));
                } else if ("DOLL".equals(type)) {
                    loadedToys.add(new Doll(name, price, age, Material.valueOf(param)));
                }
            }
        }
        return loadedToys;
    }
}