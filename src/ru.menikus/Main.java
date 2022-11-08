package ru.menikus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Ex {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");

        do {
            String input = sc.nextLine();
            System.out.println(calc(input));
        } while (sc.hasNext());
    }

    public static String calc(String input) throws Ex {

        Converter converter = new Converter();
        System.out.println("Input: ");
        System.out.println(input);


        String[] znaki = {"+", "-", "*", "/"};
        String virajenie = input;

        int znakIndex = -1;
        for (int i = 0; i <= virajenie.length(); i++) {
            if (virajenie.contains(znaki[i])) {
                znakIndex = i;
                break;
            }
        }

        if (znakIndex == -1) {
            throw new Ex("строка не является математической операцией!");
        }

        String[] regexZnaki = {"\\+", "-", "\\*", "/"};
        String[] numbers = virajenie.replaceAll("\\s", "").split(regexZnaki[znakIndex]);

        if (numbers.length != 2) {
            throw new Ex("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        }

        int a;
        int b;

        if (converter.isRoman(numbers[0]) == converter.isRoman(numbers[1])) {

            boolean isRoman = converter.isRoman(numbers[0]);
            if (isRoman) {
                a = converter.romanToInt(numbers[0]);
                b = converter.romanToInt(numbers[1]);

            } else {
                a = Integer.parseInt(numbers[0]);
                b = Integer.parseInt(numbers[1]);
            }

            if (a < 1 || a > 10 || b < 1 || b > 10) {
                throw new Ex("Введите другие цифры в интервале от 1 до 10");
            }
            int result = 0;

            switch (znaki[znakIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        throw new ArithmeticException("На ноль делить низя");
                    }
                    result = a / b;
                    break;
            }
            System.out.println("Output: ");
            if (isRoman) {
                try {
                    return (converter.intToRoman(result));
                } catch (NullPointerException ex) {
                    throw new Ex("т.к. в римской системе нет отрицательных чисел");
                }
            } else return (Integer.toString(result));
        } else {
            throw new Ex("т.к. используются одновременно разные системы счисления");
        }
    }
}