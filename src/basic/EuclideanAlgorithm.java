package basic;

public class EuclideanAlgorithm {
    /**
     * 求两个数的最大公约数
     *
     * @param p 数 p
     * @param q 数 q
     * @return p, q 的最大公约数
     */
    public static int gcd(int p, int q) {
        if (p == 0) {
            return q;
        }
        int r = q % p;
        return gcd(r, p);
    }

    public static void main(String[] args) {
        System.out.println(gcd(100, 15));
    }
}
