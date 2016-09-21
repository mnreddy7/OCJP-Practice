package org.mnr.threads_19;

public class ThreadDemo {

	public static void main(String[] args) {
		
//		System.out.println("main method");
//		concept();
//		threadLifeCycle();
//		anotherWayOfCreatingThread();
//		threadNameSettings();
		threadPrioritySettings();
	}
	
	public static void concept(){
		/*
		 * Excecuting several tasks simultaneously where each task is a seperate independent part of
		 * A thread in java can be created in two ways
		 * 1. by extending Thread class and overriding its run method
		 * 2. by implementing the Runnable interface.
		 * It is recommanded to use Runnable interface to as by extending Thread class prevents extending
		 * from any other class. Both provide same functionality. It makes sense to use Runnable interface
		 */
		
		 MyThread1	thread1		= new MyThread1();
//		 thread1.start();//start method automatically calls the overriden run in MyThread1 class
		 /*
		  * We can invoke run method directly. But thread behaviour will not be achieved.
		  * By calling start method, jvm first registers our thread with thread scheduler
		  * then allocates required resources and finally call run method of that clas
		  * If we directly call run method, these activities won't happen. It behaves like a normal method
		  * Only thread scheduler can decide which thread to execute if there are multiple threads
		  * It follows certain algorithm. But it is varied from jvm to jvm
		  * order of execution of run() can't be defined for a thread class
		  */
		 
		 /*
		  * Notes:
		  * 1. overloading of run method.
		  * 	run method of any thread class can be overloaded. But it is treated as normal method
		  * 	Thread schedueler wont execute it. It only calls a method with name run , void as return type
		  * 	and no arguements
		  *   Ex: Refer org.mnr.threads_19.MyThread1.run() and org.mnr.threads_19.MyThread1.run(int i)
		  * 
		  */
		 
//		 thread1.run(10);
		 
		 /*
		  * 2. overriding of rum method
		  * 	run() of our thread class need not to be overridden. If not overridden thread scheduler will
		  * 	call the run method of java.lang.Thread.run() which is empty implimentation
		  *   Ex: comment org.mnr.threads_19.MyThread1.run()
		  */
//		 thread1.start();
		 
		 /*
		  * 3. overriding of start() method is not recommanded. Unless there's specific requirement.
		  * 	we must call super.start() in the overridden method otherwise mutithread functionality
		  * 	will be lost if there was ever need to override start method
		  * 
		  */
		 
		 thread1.start();
	}

	public static void threadLifeCycle(){
		/*
		 * Following are the states a thread which is called as thread life cycle
		 * 
		 *      start()				  	 if thread scheduler allocates			    after run()
		 * new --------->ready/runnable -------------------------------> running -----------------> dead
		 *									processor								 execution
		 */
	}

	public static void anotherWayOfCreatingThread(){
		
		/*
		 * A thread can also be created by implementing Runnable interface
		 * It is highly recommanded to use this approach
		 * Ex: Refer org.mnr.threads_19.MyThread2
		 * here we have to pass the implementation of Runnable to thread class
		 * And then call run method on that thread class reference
		 * Note: there's no start method in Runnable interface to override
		 * Hence another thread instance is need to create a thread
		 * if run method is called directly, it acts as a normal method. No thread is created for any type 
		 * thread implementation
		 */
		
		MyRunnable runnable	=	new MyRunnable();
		Thread thread	= new Thread(runnable);
		thread.start();
		
	}

	public static void threadNameSettings(){
		/*
		 * Every thread will have a name provided by jvm/thread scheduler
		 * We can provide our own customized thread name
		 */
		
		MyThread1	thread1		= new MyThread1();
		System.out.println("thread1 name before setting:"+thread1.getName());
		thread1.setName("Naveen-thread");
		thread1.start();
		System.out.println("thread1 name::"+thread1.getName());
		System.out.println("threadSettings() thread name::"+Thread.currentThread().getName());
		
		/*
		 * if setName is called multiple times, last call will be final
		 * latest name will be considered even if that is set after calling start() 
		 */
		
		thread1.setName("dead-thread");
		
	}
	
	public static void threadPrioritySettings(){
		
		/*
		 * Every thread create in java has some priority. Either default generated by JVM or programmer supplied priority
		 * Valid range of thread priority is between  one and ten
		 * any other range is invalid. If that is set to a thread, it will result in IllegalArgumentException
		 * Generally there are three priorities defined in Thread class as constants
		 * 1. Thread.MIN_PRIORITY 	=	1
		 * 2. Thread.NORM_PRIORITY	=	5
		 * 3. Thread.MAX_PRIORITY	=	10
		 * Thread priorities are used by Thread scheduler while allocating the processor to threads
		 * If two threads have same priority, there's no guaruntee to run accordingly
		 * Default priority set by thread scheduler is 5
		 */
			
		MyThread1	thread1		= new MyThread1();
		System.out.println("thread1 priority before setting:"+thread1.getPriority());
		
		MyThread1	thread2		= new MyThread1();
		System.out.println("thread2 priority before setting:"+thread2.getPriority());
		
		MyThread1	thread3		= new MyThread1();
		System.out.println("thread3 priority before setting:"+thread3.getPriority());
		
		thread1.setPriority(10);
		thread2.setPriority(9);
		thread3.setPriority(8);
		 /*
		  * If thread priority is set doesn't lie between one and ten
		  * RE:IllegalArguementException will be thrown
		  */
		thread1.start();
		thread2.start();
		thread3.start();
		
		thread3.setPriority(7);
		/*
		 * priority can be set even after starting the thread.
		 * Most of the times, thread will be executed in the order of their priority
		 * But then again, that totally depends on thread scheduler. It may change the order of execution
		 */
		
		System.out.println(thread1.getName()+" priority:"+thread1.getPriority());
		System.out.println(thread2.getName()+" priority:"+thread2.getPriority());
		System.out.println(thread3.getName()+" priority:"+thread3.getPriority());
		
		/*
		 * Thread priority support changes from platform to platform.
		 */
	}
	
	
}

class MyThread1 extends Thread{
	
	@Override
	public void run(){
		
			System.out.println(Thread.currentThread().getName()+" executed");

	}
//	@Override
//	public void start(){
//		super.start();
//		
//	}
	
	public void run(int i){//This overloaded run method is normal method. It can't be invoked by thread scheduler
		System.out.println("overloaded run method with param "+i);
	}
	
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<=10;i++){
			System.out.println("MyThread2");
		}
		
	}
	
}