package ru.menikus;

import java.util.TreeMap;

class Converter {

    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabKeyMap = new TreeMap<>();

    public Converter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);

        arabKeyMap.put(100, "C");
        arabKeyMap.put(50, "L");
        arabKeyMap.put(10, "X");
        arabKeyMap.put(9, "IX");
        arabKeyMap.put(5, "V");
        arabKeyMap.put(4, "IV");
        arabKeyMap.put(1, "I");
    }

    boolean isRoman(String number) {
        return romanKeyMap.containsKey(number.charAt(0));
    }
    String intToRoman(int number) {
        String result = "";
        int arabKey;
        do {
            arabKey = arabKeyMap.floorKey(number);
            result += arabKeyMap.get(arabKey);
            number -= arabKey;
        } while (number != 0);
        return result;
    }

   int romanToInt(String s) {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arab = 0;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            if (arab < romanKeyMap.get(arr[i + 1])) {
                result -= arab;
            } else {
                result += arab;
            }
        }
        return result;
    }
}