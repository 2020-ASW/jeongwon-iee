import java.util.Arrays;
import java.util.PriorityQueue;
class Job {
	int start;
	int duration;

	public Job(int start, int duration) {
		this.start = start;
		this.duration = duration;
	}
}

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> priorityQueue = new PriorityQueue<>((job1, job2) -> job1.duration-job2.duration);
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        int answer = 0;
		int time = 0;
		int index = 0;

		while (index < jobs.length || !priorityQueue.isEmpty()) {
			while (index < jobs.length && jobs[index][0] <= time) { // 이미 뭔가 실행중...
				priorityQueue.add(new Job(jobs[index][0], jobs[index][1]));
				index++;
			}
			if (priorityQueue.isEmpty()) {
				time = jobs[index][0];
			} else {
				Job job = priorityQueue.poll();
				answer += (time - job.start + job.duration);
				time += job.duration;
			}
		}
		return answer / jobs.length;
    }
}
