package algorithms.intervals;

import java.util.*;
import util.structure.*;

public class InsertInterval {
	/*
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * You may assume that the intervals were initially sorted according to their
	 * start times.
	 * Example 1:
	 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	 * Example 2:
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as
	 * [1,2],[3,10],[12,16].
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 */
	public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		int idx = 0, n = intervals.size();
		while (idx < n && intervals.get(idx).end < newInterval.start)
			result.add(intervals.get(idx++));
		// here modify nenwInterval
		// here curr.end >= new.start
		while (idx < n && intervals.get(idx).start <= newInterval.end) {
			Interval curr = intervals.get(idx++);
			newInterval.start = Math.min(newInterval.start, curr.start);
			newInterval.end = Math.max(newInterval.end, curr.end);
		}
		result.add(newInterval);
		// rest curr.start > newInterval.end
		while (idx < n)
			result.add(intervals.get(idx++));
		return result;
	}
}
