class Solution {
    public long sumAndMultiply(int n) {
        int sum = 0;
        int x = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit > 0) {
                x = x * 10 + digit;
                sum += digit;
            }

            n = n / 10;
        }

        return (long) sum * reverse(x);
    }

    public int reverse(int num) {
        int reverse = 0;

        while (num != 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }

        return reverse;
    }
}