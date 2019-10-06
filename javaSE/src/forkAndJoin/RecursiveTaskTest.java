package forkAndJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskTest extends RecursiveTask<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 10; //阈值
	private int start;
	private int end;
	
	public RecursiveTaskTest(int start,int end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = (end - start) <= THRESHOLD;
		if(canCompute){
			for(int i = start; i <= end; i++){
				sum += i;
			}
		}else{
			int middle = (end + start) / 2;
			RecursiveTaskTest subTask1 = new RecursiveTaskTest(start,middle);
			RecursiveTaskTest subTask2 = new RecursiveTaskTest(middle + 1,end);
			
			subTask1.fork();
			subTask2.fork();
			
			Integer result1 = subTask1.join();
			Integer result2 = subTask2.join();
			
			sum = result1 + result2;
		}
		return sum;
	}
	
	
	public static void main(String[] args){
		ForkJoinPool fjPool = new ForkJoinPool();
		RecursiveTaskTest task = new RecursiveTaskTest(0,100);
		Future<Integer> result = fjPool.submit(task);
		try{
			System.out.println(result.get());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
