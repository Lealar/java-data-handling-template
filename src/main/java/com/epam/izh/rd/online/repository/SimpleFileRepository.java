package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        File[] files = file.listFiles();
        int fileCounter = 0;
        if (files != null) {
            for (File value : files) {
                if (value.isDirectory()) {
                    fileCounter += countFilesInDirectory(value.toString().replace("src\\main\\resources\\", ""));
                } else {
                    fileCounter++;
                }
            }
        }
        return fileCounter;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File file = new File("src/main/resources/" + path);
        File[] files = file.listFiles();
        int fileCounter = 1;
        if (files != null) {
            for (File value : files) {
                if (value.isDirectory()) {
                    fileCounter += countDirsInDirectory(value.toString().replace("src\\main\\resources\\", ""));
                }
            }
        }
        return fileCounter;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File directoryToCopy = new File(from);
        File fileTo = new File(to);
        boolean isDirCreate = new File(fileTo.getParent()).mkdirs();
        try {
            Files.copy(directoryToCopy.toPath(), fileTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File file = new File("target/classes/" + path);
        boolean isCreated;
        File file1 = new File(file, name);
        try {
            isCreated = file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return isCreated;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
