package ru.menikus;

import java.util.Scanner;

class Calc {
    public static void main(String[] args) throws Ex {

        Converter converter = new Converter();

        String[] znaki = {"+", "-", "*", "/"};

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите выражение: ");

        do {
            String virajenie = sc.nextLine();
            int znakIndex = -1;
            for (int i = 0; i <= virajenie.length(); i++) {
                if (virajenie.contains(znaki[i])) {
                    znakIndex = i;
                    break;
                }
            }

            if (znakIndex == -1) {
                throw new Ex("Неверный знак!");
            }

            String[] regexZnaki = {"\\+", "-", "\\*", "/"};
            String[] numbers = virajenie.split(regexZnaki[znakIndex]);

            if (numbers.length != 2) {
                throw new Ex("Неккоректное количество операндов");
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
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));
                } else System.out.println(result);
            } else {
                throw new Ex("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
            }
        }
        while (sc.hasNext());
    }
}