package service;

import model.Car;
import model.GameRoom;
import model.Toy;
import model.enums.AgeGroup;
import model.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

class FileServiceTest {

    @Test
    void testSaveAndLoad(@TempDir Path tempDir) throws IOException {
        // 1. Створюємо шлях до тимчасового файлу
        File tempFile = tempDir.resolve("test_toys.csv").toFile();
        String path = tempFile.getAbsolutePath();

        // 2. Готуємо дані
        GameRoom room = new GameRoom(1000);
        Toy car = new Car("TestBMW", 250.0, AgeGroup.TEEN, Size.LARGE);
        room.addToy(car);

        // 3. Зберігаємо
        FileService fileService = new FileService();
        fileService.saveRoom(room, path);

        // 4. Перевіряємо, чи файл створився
        Assertions.assertTrue(tempFile.exists());

        // 5. Завантажуємо назад
        List<Toy> loadedToys = fileService.loadRoom(path);

        // 6. Перевіряємо відповідність
        Assertions.assertEquals(1, loadedToys.size());
        Toy loadedToy = loadedToys.get(0);

        Assertions.assertTrue(loadedToy instanceof Car);
        Assertions.assertEquals("TestBMW", loadedToy.getName());
        Assertions.assertEquals(250.0, loadedToy.getPrice());
    }
}