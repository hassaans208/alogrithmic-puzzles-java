package Problem.SubArrays;
import java.util.Arrays;

/**
 * EqualSumSubarrays
 */
public class EqualSumSubarrays {

    public void iterateArray(int bi, int li, int[] array, int[][] splitHalf, int no) {
        int j = 0;

        splitHalf[no] = new int[li - bi];

        try {
            for (int i = bi; i < li; i++) {
                splitHalf[no][j] = array[i];
                j++;
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    public void splitArrays(int[] array, int si, int ei, int[] sum, int[][] split, boolean[] notPossible) {

        if (si == ei + 1) {
            if (sum[0] == sum[1]) {
                // iterate two splits and return
                notPossible[0] = false;
                EqualSumSubarrays getSplit = new EqualSumSubarrays();
                getSplit.iterateArray(0, si, array, split, 0);

                getSplit.iterateArray(ei + 1, array.length, array, split, 1);
                // return notPossible;
            } else {
                // return not possible
                notPossible[0] = true;
            }
        } else {

            if (sum[0] < sum[1] || sum[0] == 0) {
                sum[0] += array[si];
                si++;
            }

            if (sum[1] < sum[0] || sum[1] == 0) {
                sum[1] += array[ei];
                ei--;
            }

            splitArrays(array, si, ei, sum, split, notPossible);
        }
    }

    public void getEqualSumSubarrays(int[] array, int[][] split, boolean[] notPossible) {
        int si = 0;
        int ei = array.length - 1;
        int[] sum = new int[2];

        EqualSumSubarrays splitArraysCall = new EqualSumSubarrays();
        splitArraysCall.splitArrays(array, si, ei, sum, split, notPossible);
    }

    public static void run(int[] array) {
        boolean[] notPossible = new boolean[1];

        int[][] split = new int[2][];

        EqualSumSubarrays getSubarraysCall = new EqualSumSubarrays();
        getSubarraysCall.getEqualSumSubarrays(array, split, notPossible);

        if (notPossible[0]) {
            System.out.println("Not Possible");
        } else {
            System.out.println("[ " + Arrays.toString(split[0]) + ", " + Arrays.toString(split[1]) + " ]");
        }
    }

    public static void main(String[] args) {

        int[] array = { 50, 20, 30, 40 };
        EqualSumSubarrays.run(array);

        // pseudocode
        // { 1 2 3 4 }, { 5 5 }
        /**
         * set pointer i: 0
         * set pointer j: n - 1 where n = array.length
         * int[] sum: [0, 0]
         * 
         * func (array, si, ei, sum): int[] | String =>{
         * // array -> complete array
         * // si -> 4
         * // ei -> 3
         * // sum -> [6,10]
         * int split: int[1][2]
         * 4 3 + 1
         * if si == ei + 1:
         * 10 === 10
         * if sum[0] === sum[1]:
         * 0 4
         * for(i = 0;i < si; i++):
         * split[0].push(array[i])
         * 
         * for(i = ei + 1;i < arrat.lenght; i++):
         * split[1].push(array[i])
         * 
         * 
         * return split; // split: {1,2,3,4}, {5,5} answer
         * else:
         * return "Not Possible";
         * 6 < 10 or 0 === 0
         * if sum[0] < sum[1] || sum[0] === 0:
         * 6 + 4
         * sum[0] += array[si]
         * 4
         * si++;
         * 10 < 10 || 5 === 0
         * if sum[1] < sum[0] || sum[1] === 0:
         * 5 + 5
         * sum[1] += array[ei];
         * 3
         * ei--;
         * array, 4, 3, [10,10]
         * $this->func(array, i, j, sum)
         * 
         * }
         * 
         */

    }
}