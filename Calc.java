import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите уравнене: ");
        String example = scanner.nextLine();
        System.out.println(parse(example));
    }

    public static String parse(String expression) throws Exception {
        int a;
        int b;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два числа!!!");
        oper = detectOperation(expression);
        //  если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // конвертируем оба числа в арабские для вычесления действия
            a = Roman.convertToArabian(operands[0]);
            b = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            a = Integer.parseInt(operands[0]);
            b = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // если одно число римское,а другое - арабское
        else
            throw new Exception("Числа должны быть в одном формате");

        if (a > 10 || b > 10)
            throw new Exception("Числа должныбыть от 1 до 10");

        int arabian = calc(a,b,oper);
        if (isRoman) {
            // если римское число получилось меньше или равно нулю,генерируем ошибку
            if (arabian <= 0)
                throw new Exception("Римское число должо быть больше нуля");

            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else
            // Конвертируем арабское число в тип String
            result = String.valueOf(arabian);

        // возвращаем результат
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}
     class Roman {
         static String[] romanArray = new String[]{
                 "0", "I", "II","III","IV","V", "VI", "VII", "VIII", "IX", "X",
                 "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XII", "XXIII", "XXIV",
                 "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                 "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII",
                 "LIII", "LIV", "LV", "LVI","LVII","LVIII","LIX","LX", "LXI", "LXII", "LXIII", "LXIV", "LXV","LXVI","LXVII","LXVIII",
                 "LXIX", "LXX", "LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX", "LXXX", "LXXXI",
                 "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV",
                 "XCV","XCVI","XCVII","XCVIII","XCIX","C" };

         public static boolean isRoman(String val) {
             for (int i = 0; i < romanArray.length; i++)
                 if (val.equals(romanArray[i]))
                     return true;
             return false;
         }

         public static int convertToArabian(String roman) {
             for (int i = 0; i < romanArray.length; i++)
                 if (roman.equals(romanArray[i]))
                     return i;
             return -1;
         }
         public static String convertToRoman(int arabian) {
             return romanArray[arabian];
         }

     }


