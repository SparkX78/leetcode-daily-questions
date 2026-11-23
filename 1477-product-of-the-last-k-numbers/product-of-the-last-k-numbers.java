import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private final List<Integer> prefix;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            prefix.clear();
            prefix.add(1);
        } else {
            int last = prefix.get(prefix.size() - 1);
            prefix.add(last * num);
        }
    }

    public int getProduct(int k) {
        int n = prefix.size();
        // Fewer than k elements since the most recent 0 means the last k window includes 0
        if (k >= n) {
            return 0;
        }
        return prefix.get(n - 1) / prefix.get(n - 1 - k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */