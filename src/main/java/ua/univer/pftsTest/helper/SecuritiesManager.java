package ua.univer.pftsTest.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ua.univer.pftsTest.tables.Securities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Утилитный класс для обработки списков Securities и работы с файлами конфигурации.
 */
@Slf4j
@UtilityClass
public class SecuritiesManager {

    /**
     * Подсчет количества вхождений каждого secCode.
     */
    public Map<String, Long> countOccurrencesBySecCode(List<Securities> securities) {
        log.info("Начало подсчета вхождений secCode. Размер списка: {}",
                (securities != null ? securities.size() : 0));
        if (securities == null) return Collections.emptyMap();
        // Создаем счетчик
        AtomicLong counter = new AtomicLong();
        Map<String, Long> result = securities.stream()
                .filter(s -> s.getSecCode() != null)
                .peek(s -> counter.incrementAndGet())
                .collect(Collectors.groupingBy(
                        Securities::getSecCode,
                        Collectors.counting()
                ));
        log.info("Элементов после фильтрации: {}", counter.get());
        log.info("Подсчет завершен. Найдено уникальных кодов: {}", result.size());
        return result;
    }

    /**
     * Экспорт данных в файл формата secCode=decimals.
     */
    public void exportDecimalsToFile(List<Securities> securities, String filePath) throws IOException {
        log.info("Начало экспорта decimals в файл: {}", filePath);

        if (securities == null) {
            log.warn("Передан пустой список для экспорта.");
            return;
        }

        Map<String, Integer> dataMap = securities.stream()
                .filter(s -> s.getSecCode() != null && s.getDecimals() != null)
                .collect(Collectors.toMap(
                        Securities::getSecCode,
                        Securities::getDecimals,
                        (existing, replacement) -> replacement
                ));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
                writer.write(String.format("%s=%d", entry.getKey(), entry.getValue()));
                writer.newLine();
            }
            log.info("Файл успешно записан. Количество строк: {}", dataMap.size());
        } catch (IOException e) {
            log.error("Критическая ошибка при записи файла {}: {}", filePath, e.getMessage());
            throw e;
        }
    }

    /**
     * Импорт данных из файла в Map.
     */
    public Map<String, Integer> importDecimalsFromFile(String filePath) throws IOException {
        log.info("Попытка импорта данных из файла: {}", filePath);

        File file = new File(filePath);
        if (!file.exists()) {
            log.error("Файл не найден по пути: {}", filePath);
            return Collections.emptyMap();
        }

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            Map<String, Integer> result = lines
                    .filter(line -> line.contains("=") && !line.trim().startsWith("#"))
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(
                            parts -> parts[0].trim(),
                            parts -> {
                                String key = parts[0].trim();
                                String val = parts[1].trim();
                                try {
                                    return Integer.parseInt(val);
                                } catch (NumberFormatException e) {
                                    log.warn("Пропуск некорректной записи в файле: {}={} (ожидалось целое число)", key, val);
                                    return 0;
                                }
                            },
                            (existing, replacement) -> {
                                log.debug("Дубликат ключа {}. Замена старого значения на новое.", existing);
                                return replacement;
                            }
                    ));

            log.info("Импорт завершен. Загружено записей: {}", result.size());
            return result;
        } catch (IOException e) {
            log.error("Ошибка при чтении файла {}: {}", filePath, e.getMessage());
            throw e;
        }
    }
}
