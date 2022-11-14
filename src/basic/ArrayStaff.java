package basic;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public class ArrayStaff {
    public static int max(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int avg(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum / arr.length;
    }

    public static int[] copy1(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static int[] copy2(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }

    public static int[] copy3(int[] arr) {
        return arr.clone();
    }

    public static int[] copy4(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static int[][] squareMatrixMul(int[][] arr1, int[][] arr2) {
        int N = arr1.length;
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {4, 1, 2, 3, 4, 5, 6, 8};
        System.out.println(max(a));
        System.out.println(avg(a));

        int[] sample = new int[10_000_000];
        for (int j = 0; j < sample.length; j++) {
            sample[j] = new Random(System.currentTimeMillis()).nextInt();
        }
        // 基本数据类型可以实现深拷贝，引用类型数组通过以下 api 拷贝的都是浅拷贝
        // 引用类型数组的 引用类型需要实现 Cloneable 接口，或者 Serializable 接口
        System.out.println("System.arraycopy");
        benchmark(ArrayStaff::copy2, sample);
        System.out.println("clone");
        benchmark(ArrayStaff::copy3, sample);
        System.out.println("Arrays.copyOf: min() + arraycopy");
        benchmark(ArrayStaff::copy4, sample);
        System.out.println("for");
        benchmark(ArrayStaff::copy1, sample);

        System.out.println("System.arraycopy > clone > Arrays.copyOf > for");

        reverse(a);
        print(a);
        int[][] square1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int[][] square2 = {
                {0, 1, 1},
                {0, 1, 1},
                {1, 0, 0},
        };
        print(squareMatrixMul(square1, square2));
    }

    public static void benchmark(Function<int[], int[]> function, int[] sample) {
        long sum = 0;
        long n = 1000;
        for (int i = 0; i < n; i++) {
            long startTime = System.nanoTime();
            function.apply(sample);
            sum += System.nanoTime() - startTime;
        }
        System.out.println(sum / n);
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(int[][] arrs) {
        System.out.println("Matrix:");
        for (int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
