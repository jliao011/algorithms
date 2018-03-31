package algorithms.intervals;

import java.util.Arrays;
import java.util.Comparator;

import util.structure.Interval;

/*classic greedy problem*/
public class NonOverlapIntervals {
	public int nonOverlapIntervals(Interval[] intervals) {
		// greedy, sort by end then remove conflict
		// longest non overlapping
		// optimize, update end, is O(n)
		if (intervals == null || intervals.length == 0)
			return 0;
		Arrays.sort(intervals, (o1, o2) -> o1.end - o2.end);
		// comparator is quicker than lambda
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.end - o2.end;
			}
		});
		int end = intervals[0].end, count = 1;
		// find longest non-conflict
		for (int i = 1; i < intervals.length; i++) {
			// conflict
			if (intervals[i].start >= end) {
				count++;
				end = intervals[i].end;
			}
		}
		return intervals.length - count;
	}
}
