package algorithms.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import util.structure.Interval;

public class MeetingRooms {
	public int maxSchedules(Interval[] intervals, Interval range) {
		if (intervals == null || intervals.length == 0)
			return 0;
		// sort on end time
		Arrays.sort(intervals, (o1, o2) -> o1.end - o2.end);
		int count = 0;
		Interval prev = null;
		for (int i = 0; i < intervals.length; i++) {
			if (intervals[i].start < range.start || intervals[i].end > range.end)
				continue;
			if (prev == null || intervals[i].start >= prev.end) {
				count++;
				prev = intervals[i];
			}
		}
		return count;
	}

	public int minMeetingRooms(Interval[] intervals) {
		// greedy, sort on start first
		// then use heap on end time to track rooms
		if (intervals == null || intervals.length == 0)
			return 0;
		// sort start time and init end sorted heap
		Arrays.sort(intervals, new StartComparator());
		PriorityQueue<Interval> heap = new PriorityQueue<>(new EndComparator());
		// start
		heap.offer(intervals[0]);
		Interval prev = intervals[0], curr = null;
		for (int i = 1; i < intervals.length; i++) {
			// find min end room, cannot use peek
			prev = heap.poll();
			curr = intervals[i];
			// can put in this min end room, update end time
			if (curr.start >= prev.end)
				prev.end = curr.end;
			else	// sign a new room
				heap.offer(curr);
			heap.offer(prev);
		}
		return heap.size();
	}

	class StartComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}

	class EndComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.end - o2.end;
		}
	}
}
