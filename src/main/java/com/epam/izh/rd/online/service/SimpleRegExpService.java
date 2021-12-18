package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        String string = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            string = bufferedReader.readLine();
            Pattern pattern = Pattern.compile("\\d{4} \\d{4} \\d{4} \\d{4}");
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                String newString = matcher.group().replaceAll(" \\d{4} \\d{4}", " **** ****");
                string = string.replace(matcher.group(), newString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        String string = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/sensitive_data.txt"))) {
            string = bufferedReader.readLine();
            string = string.replaceAll("\\$\\{payment_amount}", String.valueOf((int) paymentAmount));
            string = string.replaceAll("\\$\\{balance}", String.valueOf((int) balance));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
