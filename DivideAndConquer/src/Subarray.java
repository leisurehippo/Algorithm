/**
 * 求解数组的最大子数组(分治策略)
 * 找到数组中间位置mid,则最大子数组必然是下列三种情况之一:
 * 1.完全位于子数组A[low,mid]中
 * 2.完全位于子数组A[mid+1,high]中
 * 3.跨越了中间点
 * 前两种情况可以递归求解,第三种情况只需求出形如A[i..mid]和A[mid+1...j]
 * 的最大子数组即可
 * Created by wj on 16/10/17.
 */
public class Subarray {
    /**
     * 跨越中点的最大子数组求解
     * @param A
     * @param low
     * @param mid
     * @param high
     * @return
     */
    public static int[] CrossingSubarray(int[] A, int low, int mid, int high){
        int left_max = Integer.MIN_VALUE,right_max = Integer.MIN_VALUE;
        int sum = 0;
        int left_index = 0,right_index = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > left_max){
                left_max = sum;
                left_index = i;
            }
        }
        sum = 0;
        for (int i = mid+1; i <= high; i++) {
            sum += A[i];
            if (sum > right_max){
                right_max = sum;
                right_index = i;
            }
        }
        int []result = {left_index, right_index, left_max + right_max};
        return result;
    }

    /**
     * 求解数组的最大子数组
     * @param A
     * @param low
     * @param high
     * @return
     */
    public static int[] Subarray(int[] A, int low, int high){
        //递归终止条件
        if (low == high){
            int[] result = {low,high,A[low]};
            return result;
        }else{
            int mid = (low + high) / 2;
            //分别求出三种情况的最大值并进行比较
            int [] left  = Subarray(A, low, mid);
            int [] right = Subarray(A, mid+1, high);
            int [] cross = CrossingSubarray(A, low, mid, high);
            if (left[2] >= right[2] && left[2] >= cross[2])
                return left;
            if (right[2] >= left[2] && right[2] >= cross[2])
                return right;
            else
                return cross;
        }
    }
    public static void main(String[] args) {
        //test
        int [] arr = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        int len = arr.length;
        int [] result = Subarray(arr,0,len-1);
        System.out.println("left-index: "+result[0]+" right-index: "+result[1]+" sum: "+result[2]);
    }
}
