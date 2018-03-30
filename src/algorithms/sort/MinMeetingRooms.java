package algorithms.sort;

import util.structure.Interval;
import java.util.*;

/*Given an array of meeting time intervals consisting of start and end times 
[[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.*/
public class MinMeetingRooms {
	public static int minMeetingRooms(Interval[] intervals) {
		if (intervals.length <= 1)
			return intervals.length;
		// sort earliest start time
		Arrays.sort(intervals, (o1, o2) -> o1.start - o2.start);
		// sort earliest end time
		PriorityQueue<Interval> heap = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
		heap.offer(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			// find earliest prev earliest stop
			Interval prev = heap.poll();    // cannot peek here
			// if curr earliest start has no conflict, merge
			if (intervals[i].start >= prev.end)
				// if peek(), heap not updated
				prev.end = intervals[i].end;
			else
				// more rooms needed
				heap.offer(intervals[i]);
			heap.offer(prev);
		}
		return heap.size();
	}

	// use only two array
	public static int minMeetingRoomsII(Interval[] intervals) {
		if (intervals.length <= 1)
			return intervals.length;
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int count = 0, end = 0;
		for (int i = 0; i < intervals.length; i++) {
			if (starts[i] < ends[end])
				count++;
			else
				end++;
		}
		return count;
	}
}
