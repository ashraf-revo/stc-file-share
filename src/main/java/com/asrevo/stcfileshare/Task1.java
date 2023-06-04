package com.asrevo.stcfileshare;

import java.util.Scanner;

public class Task1 {
    public static String solution(String input) {
        input = input.replaceAll("\\(", "|-").replaceAll("\\)", "-|");
        Scanner scanner = new Scanner(input).useDelimiter("\\|");
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNext()) {
            {
                String token = scanner.next();
                if (token.startsWith("-") && token.endsWith("-")) {
                    String reverse = reverse(token.replace("-", ""));
                    builder.append("(").append(reverse).append(")");
                }
                else {
                    builder.append(token);
                }
            }

        }
        return builder.toString();

    }

    private static String reverse(String token) {
        char[] charArray = token.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = charArray.length; i > 0; i--) {
            stringBuilder.append(charArray[i - 1]);
        }
        return stringBuilder.toString();
    }

}
