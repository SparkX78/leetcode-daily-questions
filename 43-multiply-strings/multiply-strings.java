class Solution {

    public String multiply(String num1, String num2) {

        // if any number is 0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();

        // maximum length can be n + m
        int[] result = new int[n + m];

        // multiply from back
        for (int i = n - 1; i >= 0; i--) {

            int digit1 = num1.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {

                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;

                // positions
                int pos1 = i + j;
                int pos2 = i + j + 1;

                // add previous value
                int sum = product + result[pos2];

                // store digit
                result[pos2] = sum % 10;

                // carry
                result[pos1] += sum / 10;
            }
        }

        // build final string
        StringBuilder sb = new StringBuilder();

        // skip leading zeros
        int i = 0;

        while (i < result.length && result[i] == 0) {
            i++;
        }

        while (i < result.length) {
            sb.append(result[i]);
            i++;
        }

        return sb.toString();
    }
}