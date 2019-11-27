package LaiOffer;

/**
 * Created by yuehu on 11/9/19.
 * //new one dev
 */
public class BinarySearch {
    public int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch s = new BinarySearch();
        int[] a = {1,3,4,5,0,2,12,10};
        System.out.println(s.binarySearch(a,3));
    }

}
