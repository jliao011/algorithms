package algorithms.dp;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxSubarray {
	private static final Logger logger = LoggerFactory.getLogger(MaxSubarray.class);

	/*
	 * Input: [-2,1,-3,4,-1,2,1,-5,4],
	 * Output: 6
	 * Explanation: [4,-1,2,1] has the largest sum = 6.
	 */
	public static int[] maxSubarray(int[] nums) {
		int currMax = 0, max = Integer.MIN_VALUE;
		int maxHead = 0, maxTail = 0, head = 0, tail = 0;
		for (int i = 0; i < nums.length; i++) {
			// continue array
			if (currMax + nums[i] > nums[i]) {
				tail = i;
				currMax += nums[i];
			} else {
				currMax = nums[i];
				head = i;
				tail = i;
			}
			if (currMax > max) {
				max = currMax;
				maxHead = head;
				maxTail = tail;
			}
		}
		int[] result = new int[maxTail - maxHead + 1];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[maxHead + i];
		logger.info("max {}", max);
		return result;
	}

	/*
	 * from i to j:
	 * mod = (sum[j] - sum[i]) % m
	 * mod = (sum[j]%m - sum[i]%m + m) % m
	 * (1) pre[i] < pre[j], pre[i] == 0;
	 * (2) pre[i] > pre[j], find min(pre[i])
	 */
	public static int[] maxMod(int[] nums, int m) {
		if (nums == null || nums.length == 0)
			return new int[0];
		// save prefix mod and index pair
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		int maxMod = 0, preMod = 0, head = 0, tail = 0;
		for (int i = 0; i < nums.length; i++) {
			preMod = (preMod + nums[i]) % m;
			if (preMod > maxMod) {
				maxMod = preMod;
				head = 0;
				tail = i;
			}
			// find smallest key larger than preMod
			Entry<Integer, Integer> entry = treeMap.ceilingEntry(preMod);
			if (entry != null) {
				// -1 % 5 is -1, have to add m
				int mod = (preMod - entry.getKey() + m) % m;
				if (mod > maxMod) {
					maxMod = mod;
					head = entry.getValue() + 1;
					tail = i;
				}
			}
			treeMap.put(preMod, i);
		}
		int[] result = new int[tail - head + 1];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[i + head];
		logger.info("max mod is {}", maxMod);
		return result;
	}

	public static int[] maxModBF(int[] nums, int m) {
		if (nums == null || nums.length == 0)
			return new int[0];
		int[] mods = new int[nums.length];
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			mods[i] = sum % m;
		}
		logger.info("pre: {}", mods);
		int maxMod = 0, maxHead = 0, maxTail = 0;
		for (int head = 0; head < nums.length; head++) {
			for (int tail = head; tail < nums.length; tail++) {
				int prev = head == 0 ? 0 : mods[head - 1];
				int mod = (mods[tail] - prev + m) % m;
				logger.info("from {} to {} mod {}", head, tail, mod);
				if (mod > maxMod) {
					maxMod = mod;
					maxHead = head;
					maxTail = tail;
				}
			}
		}
		int[] result = new int[maxTail - maxHead + 1];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[i + maxHead];
		logger.info("max mod is {}", maxMod);
		return result;
	}

	public static void test() {
		int[] input = new int[] { 3, 4, 9, 5, 9 };
		logger.info("input {}", input);
		logger.info("result {}", maxMod(input, 7));
	}
}
