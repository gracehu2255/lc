package LaiOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yuehu on 12/8/19.
 * find the smallest k elements in an unsorted array
 * Assumptions 1) array is not null 2) k >= 0 and k < array.length
 * Comparable 和Comparator两个接口
 * https://www.cnblogs.com/skywang12345/p/3324788.html
 *
 * Implement PriorityQueue through Comparator in Java
 * https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
 */
public class KSmallestInSortedArray {
    //Method 1: k sized max heap
    public int[] kSmallestI(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                //offer the first k element into max heap if it is
                //Notice: if you want to use the heapify, the only thing you can do is to call a
                //certain constructor of PriorityQueue
                maxHeap.offer(array[i]);
            } else if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }

        int[] result = new int[k];
        for (int i = k -1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;

    }


    //Method 2: quick select
    public int[] kSmallestII(int[] array, int k) {
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        //quickselect to find the kth smallest element
        //after calling this method, the first k elements in the array are
        //the k smallest ones(but not sorted)
        quickSelect(array, 0, array.length - 1, k-1);
        //copy out the first k elements and sort them
        int[] result = Arrays.copyOf(array,k);
        Arrays.sort(result);
        return result;
    }

    private void quickSelect(int[] array, int left, int right, int target) {
        // like quick sort we need to do the partition using pivot value
        int mid = partition(array, left,right);
        //unlike the quick sort, we only need to do quickselect on at most one
        //partition
        //if the pivot is already the kth smallest element, we directly return
        if (mid == target) {
            return;
        //only need to recursively call quick select on the left partition
        //if the kth smallest one is in the left partition
        } else if (target < mid) {
            quickSelect(array, left,mid - 1, target);
            //only need to recursively call quick select on the right partition
            //if the kth smallest one is in the right partition
        }else {
            quickSelect(array,mid+1, right, target);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int start = left;
        int end  = right - 1;
        while (start <= end) {
            if (array[start] < pivot) {
                start++;
            } else if (array[end] >= pivot) {
                end--;
            } else {
                swap(array, start++, end--);
            }
        }
        swap(array, start, right);
        return start;
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        int[] s = {1,2,13,4,15,6,12,4,5,7};
        KSmallestInSortedArray a = new KSmallestInSortedArray();
        System.out.println(Arrays.toString(a.kSmallestI(s, 5)));
    }
}
