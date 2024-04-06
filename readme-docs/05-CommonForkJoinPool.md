## Common Fork Join Pool

### Introduction

The Common Fork Join Pool is a shared pool of worker threads used by the Java Fork/Join Framework to execute parallel tasks. It is also utilized by the Streams API for parallel stream operations and by CompletableFuture for asynchronous tasks.

### Double-Ended Work Queue

- The Common Fork Join Pool uses a double-ended work queue to store and manage tasks. Tasks are added to the tail of the queue and can be taken from both ends for execution.

### Shared Work Queue

- The Common Fork Join Pool maintains a shared work queue among worker threads. Worker threads can steal tasks from other threads' queues when their own queue is empty, enabling load balancing and efficient task distribution.

### Worker Threads

- Worker threads in the Common Fork Join Pool are responsible for executing tasks from the work queue. Each worker thread is associated with a single processor core, ensuring that tasks are executed concurrently on different cores.

### Relation between Threads and Processor Cores

- The number of worker threads in the Common Fork Join Pool is typically one less than the total number of processor cores. This is done to leave one core free for the main thread of the application, which initializes the computation.

### Continuous Pooling Behavior

- The Common Fork Join Pool's continuous pooling behavior helps keep all processor cores of the machine as busy as possible. It achieves this by ensuring that worker threads are always executing tasks, minimizing idle time.

### Usage by Parallel Streams and CompletableFuture

- Parallel streams in Java use the Common Fork Join Pool for parallel execution of stream operations. CompletableFuture also utilizes the Common Fork Join Pool for executing asynchronous tasks.

### Parallelism and Threads in Common Fork Join Pool

- Increasing the parallelism in the Common Fork Join Pool can improve the performance of parallel operations. The method `ForkJoinPool.getCommonPoolParallelism()` returns the default parallelism level, which is usually one less than the total number of processor cores.

### Deadlock Scenario Prevention

- The Common Fork Join Pool is designed to prevent deadlock scenarios by employing work-stealing behavior. This ensures that worker threads can always find tasks to execute, even if their own queue is empty.

### Configuring Parallelism in Parallel Streams

- You can modify the default parallelism level in parallel streams to control the number of threads used for parallel execution. This can be done using the `java.util.concurrent.ForkJoinPool.common.parallelism` system property or by setting the parallelism level directly in the stream pipeline.

### When to Use Parallel Streams

- Use parallel streams when the split and combine strategy is faster than sequential processing. For example, when processing large collections or performing computationally intensive tasks.
- Use parallel streams when the computation takes a significant amount of time to complete and can be divided into independent parts that can be executed concurrently.
- Use parallel streams when the machine has multiple processor cores, as parallel execution can take advantage of these cores to speed up processing.

### When Not to Use Parallel Streams

- Avoid using parallel streams with linked lists, as they do not split efficiently for parallel processing. Instead, use data structures like ArrayList or arrays.
- Avoid using parallel streams for small datasets, as the overhead of parallelism may outweigh the benefits. Sequential processing may be more efficient in such cases.
- Avoid using parallel streams for operations that involve auto-boxing and unboxing, as this can lead to performance degradation. Use primitive streams (e.g., IntStream) instead.
- Avoid using parallel streams for operations like iterate and limit, which are not suitable for parallel execution due to their sequential nature.

### Conclusion

The Common Fork Join Pool and parallelism in the Java Fork/Join Framework and Streams API provide powerful tools for concurrent and parallel programming. Understanding their behavior and usage can help you write more efficient and scalable Java applications.

### References:
https://dzone.com/articles/be-aware-of-forkjoinpoolcommonpool
---