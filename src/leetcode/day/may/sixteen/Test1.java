package leetcode.day.may.sixteen;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 示例 1: nums1 = [1, 3] nums2 = [2] 中位数是 2.0 示例 2: nums1 = [1, 2] nums2 = [3, 4]
 * 中位数是 (2 + 3)/2 = 2.5
 * 
 * @author Administrator
 *
 */
public class Test1 {

	public static void main(String[] args) {
//		int[] a = {1,1 };
//		int[] b = {1,2 };
//		System.out.println(findMedianSortedArrays1(a, b));
//		int[] c = {1,2 };
//		int[] d = {1,2 };
//		System.out.println(findMedianSortedArrays1(c, d));
//		int[] c = {10};
//		int[] d = {9999 };
//		System.out.println(findMedianSortedArrays1(c, d));
		int[] c = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
		int[] d = {0,6};
		System.out.println(findMedianSortedArrays1(c, d));
	}

	public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		int maxnum = 0;
		int minnum = 0;
		// nums1为空
		if (nums1.length == 0) {
			minnum = nums2[0];
			maxnum = nums2[nums2.length-1];
		}
		// nums2为空
		else if (nums2.length == 0) {
			minnum = nums1[0];
			maxnum = nums1[nums1.length-1];
		} else {
			if (nums1[0] < nums2[0])
				minnum = nums1[0];
			else
				minnum = nums2[0];
			
			if (nums1[nums1.length - 1] > nums2[nums2.length - 1])
				maxnum = nums1[nums1.length - 1];
			else
				maxnum = nums2[nums2.length - 1];
		}
		
		double midnum = (maxnum + minnum) / 2;
		if((nums2.length+nums1.length)==2) {
			midnum = (maxnum + minnum);
			return midnum/2;
		}
		int midleft = -1;
		int midright = maxnum;
		for (int i = 0; i < nums1.length; i++) {
			if (midnum > nums1[i]) {
				if ((midnum - midleft) > (midnum - nums1[i])) {
					midleft = nums1[i];
				}
			} else if (midnum < nums1[i]) {
				if ((midnum - midright) < (midnum - nums1[i])) {
					midright = nums1[i];
				}
			} else {
				if(i>0) {
					if(nums1[i] == nums1[i-1]&&midleft == nums1[i])
						midright = nums1[i];
					else
						midleft = nums1[i];
				}else {
					midleft = nums1[i];
				}
			}
		}
		for (int i = 0; i < nums2.length; i++) {
			if (midnum > nums2[i]) {
				if ((midnum - midleft) > (midnum - nums2[i])) {
					midleft = nums2[i];
				}
			} else if (midnum < nums2[i]) {
				if ((midnum - midright) < (midnum - nums2[i])) {
					midright = nums2[i];
				}
			} else {
				if(i>0) {
					if(nums2[i] == nums2[i-1]&&midleft == nums2[i])
						midright = nums2[i];
					else
						midleft = nums2[i];
				} else {
					midleft = nums2[i];
				}
			}
		}
		if ((maxnum + minnum) % 2 == 0)
			return midnum;
		else {
			if(midright-midleft>1)
				return midleft;
			midnum = (midleft + midright);
			return midnum/2;
		}
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int[] sum = new int[nums1.length + nums2.length];
		int sumindex = 0;
		int point = 0;
		int i = 0;
		if (nums1.length >= nums2.length)
			i = nums1.length;
		else
			i = nums2.length;
		// nums1为空
		if (nums1.length == 0) {
			for (int j = 0; j < nums2.length; j++) {
				sum[sumindex] = nums2[j];
				sumindex++;
			}
		}
		// nums2为空
		else if (nums2.length == 0) {
			for (int j = 0; j < nums1.length; j++) {
				sum[sumindex] = nums1[j];
				sumindex++;
			}
		}
		// 都不为空
		else if (nums2[0] > nums1[nums1.length - 1]) {
			for (int j = 0; j < nums1.length; j++) {
				sum[sumindex] = nums1[j];
				sumindex++;
			}
			for (int j = 0; j < nums2.length; j++) {
				sum[sumindex] = nums2[j];
				sumindex++;
			}
		} else if (nums1[0] > nums2[nums2.length - 1]) {
			for (int j = 0; j < nums2.length; j++) {
				sum[sumindex] = nums2[j];
				sumindex++;
			}
			for (int j = 0; j < nums1.length; j++) {
				sum[sumindex] = nums1[j];
				sumindex++;
			}
		} else {
			for (int m = 0; m < i; m++) {
				if (i == nums1.length) {
					if (m < nums2.length)
						if (nums1[m] > nums2[m]) {
							sum[sumindex] = nums2[m];
							sumindex++;
							sum[sumindex] = nums1[m];
						} else {
							sum[sumindex] = nums1[m];
							sumindex++;
							sum[sumindex] = nums2[m];
						}
					else
						sum[sumindex] = nums1[m];
				} else {
					if (m < nums1.length)
						if (nums1[m] > nums2[m]) {
							sum[sumindex] = nums2[m];
							sumindex++;
							sum[sumindex] = nums1[m];
						} else {
							sum[sumindex] = nums1[m];
							sumindex++;
							sum[sumindex] = nums2[m];
						}
					else
						sum[sumindex] = nums2[m];
				}
				sumindex++;
			}
			for (int j = 0; j < sum.length; j++) {
				System.out.println("排序前" + sum[j]);
			}
			for (int j = 1; j < sum.length - 1; j++) {
				if (sum[j - 1] > sum[j + 1]) {
					point = sum[j - 1];
					sum[j - 1] = sum[j + 1];
					sum[j + 1] = point;
				}
				if (sum[j] > sum[j + 1]) {
					point = sum[j];
					sum[j] = sum[j + 1];
					sum[j + 1] = point;
				}
			}
			for (int j = 0; j < sum.length; j++) {
				System.out.println("排序后" + sum[j]);
			}
			if (sum.length > 7) {
				if (sum[(sum.length - 1) / 2 - 1] > sum[(sum.length - 1) / 2]) {
					point = sum[(sum.length - 1) / 2 - 1];
					sum[(sum.length - 1) / 2 - 1] = sum[(sum.length - 1) / 2];
					sum[(sum.length - 1) / 2] = point;
				}
				if (sum[(sum.length - 1) / 2 + 1] < sum[(sum.length - 1) / 2]) {
					point = sum[(sum.length - 1) / 2 + 1];
					sum[(sum.length - 1) / 2 + 1] = sum[(sum.length - 1) / 2];
					sum[(sum.length - 1) / 2] = point;
				}
			}
			for (int j = 0; j < sum.length; j++) {
				System.out.println("排序后" + sum[j]);
			}
		}
		sumindex = sumindex - 1;
		if (sumindex % 2 == 0) {
			return sum[sumindex / 2];
		} else {
			double d = ((sum[(sumindex + 1) / 2]) + (sum[(sumindex + 1) / 2 - 1]));
			return d / 2;
		}
	}
}
