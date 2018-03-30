package algorithms.sort;

import java.util.*;
import util.structure.Interval;

/*Given a collection of intervals, merge all overlapping intervals.
For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].*/
public class MergeIntervals {
	public static List<Interval> mergeIntervals(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		List<Interval> result = new ArrayList<>();
		Interval prev = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval curr = intervals.get(i);
			if (prev.end >= curr.start)
				// [[1,4],[2,3]]
//				prev = new Interval(prev.start, Math.max(prev.end, curr.end));
				prev.end = Math.max(prev.end, curr.end);
			else {
				result.add(prev);
				prev = curr;
			}
		}
		result.add(prev);
		return result;
	}

	// optimized format
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		List<Interval> result = new ArrayList<>();
		Interval prev = null;
		for (Interval curr : intervals) {
			if (prev == null || prev.end < curr.start) {
				result.add(curr);
				prev = curr;
			} else
				prev.end = Math.max(prev.end, curr.end);
		}
		return result;
	}
}
