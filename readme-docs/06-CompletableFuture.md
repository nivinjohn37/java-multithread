## CompletableFuture

---
### Introduction

CompletableFuture is a class in Java that represents a future result of an asynchronous computation. It allows you to perform computations asynchronously, potentially improving application performance by allowing other tasks to be processed while waiting for the asynchronous operation to complete.

### Async Computations and Reactive Programming

CompletableFuture enables reactive programming by allowing you to define and chain asynchronous computations. This approach makes your code more responsive, as it can continue executing other tasks while waiting for asynchronous operations to complete.

### Limitations of Future API

The traditional Future API in Java has limitations, such as the inability to explicitly complete a Future or combine multiple Futures. CompletableFuture addresses these limitations by providing more flexible and powerful features for handling asynchronous computations.

### CompletableFuture and Reactive Programming Principles

- **Responsive**: CompletableFuture allows any code to be responsive by enabling asynchronous execution.
- **Resilient**: It ensures that exceptions in one part of the code do not break the entire application, as they can be handled asynchronously.
- **Elastic**: CompletableFuture provides a pool of threads that can scale up or down based on the workload.
- **Message-Driven**: It enables asynchronous interaction in an event-driven style, improving the efficiency of the application.

### Factory Methods

CompletableFuture provides various factory methods to create instances:

- `supplyAsync`: Executes a Supplier asynchronously and returns a CompletableFuture representing the result.
- `runAsync`: Executes a Runnable asynchronously and returns a CompletableFuture representing the completion of the computation.
- `completedFuture`: Returns a CompletableFuture that is already completed with the given value.

### Completion Stage Methods

Completion stage methods allow you to chain multiple asynchronous computations together. Some common completion stage methods include:

- `thenApply`: Applies a function to the result of the current CompletableFuture and returns a new CompletableFuture with the result of the function.
- `thenAccept`: Accepts the result of the current CompletableFuture as a parameter and performs some action without returning a value.
- `thenCombine`: Combines the results of two CompletableFutures using a BiFunction and returns a new CompletableFuture with the combined result.

### Exception Handling

CompletableFuture provides methods for handling exceptions:

- `exceptionally`: Handles exceptions that occur during the computation and returns a new CompletableFuture with a default value.
- `handle`: Handles both the result and the exception of the current CompletableFuture and returns a new CompletableFuture with the result of the handler function.
- `whenComplete`: Performs an action when the current CompletableFuture completes, either successfully or exceptionally.

### Example Usage

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    return 42;
});

future.thenApply(result -> result * 2)
      .thenAccept(System.out::println)
      .join(); // Wait for the computation to complete
```

In this example, `supplyAsync` creates a CompletableFuture that performs a computation asynchronously. The `thenApply` method then applies a function to the result, and `thenAccept` consumes the result.

---

## CompletableFuture `get()` vs. `join()`

### Introduction

`get()` and `join()` are methods in the CompletableFuture class that allow you to retrieve the result of a CompletableFuture's computation. While both methods serve a similar purpose, there are key differences in their behavior and usage.

### `get()`

- The `get()` method is a blocking call that waits for the computation to complete and returns the result or throws an exception if the computation fails.
- It returns a result of type `T` or throws an `InterruptedException` if the current thread is interrupted while waiting, and an `ExecutionException` if the computation throws an exception.

#### Example Usage of `get()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    return 42;
});

try {
    Integer result = future.get(); // Waits for the computation to complete
    System.out.println("Result: " + result);
} catch (InterruptedException | ExecutionException e) {
    System.err.println("Computation failed: " + e.getMessage());
}
```

### `join()`

- The `join()` method is similar to `get()`, but it does not throw checked exceptions. Instead, it throws an `UncheckedExecutionException`, which is a runtime exception.
- `join()` is a non-blocking call and waits for the computation to complete, returning the result or throwing an unchecked exception if the computation fails.

#### Example Usage of `join()`

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    return 42;
});

Integer result = future.join(); // Waits for the computation to complete
System.out.println("Result: " + result);
```

### When to Use `get()` vs. `join()`

- Use `get()` when you want to handle checked exceptions such as `InterruptedException` and `ExecutionException`.
- Use `join()` when you are confident that the computation will complete successfully and you prefer not to deal with checked exceptions.

---

`get()` and `join()` are both useful methods for retrieving the result of a CompletableFuture's computation. Understanding their differences and when to use each method can help you write more efficient and reliable asynchronous code in Java.

---
CompletableFuture provides a powerful way to work with asynchronous computations in Java, enabling you to write more responsive and efficient code.

---

##Q&A
**Question: Why use CompletableFuture instead of parallel stream in Java?**

**Answer:**

**1. Introduction:**
- Both CompletableFuture and parallel streams can achieve parallelism in Java but have different use cases and characteristics.

**2. CompletableFuture:**
- **Flexibility and Control:** CompletableFuture offers more flexibility and control over asynchronous computations compared to parallel streams.
- **Error Handling:** CompletableFuture provides better support for error handling with methods like exceptionally().
- **Composition:** CompletableFuture allows you to compose multiple asynchronous operations with methods like thenApply(), thenCompose(), and thenCombine().
- **Completion Notification:** CompletableFuture provides methods like isDone(), isCompletedExceptionally(), and join() for checking the status of the asynchronous computation.
- **Dependency Management:** CompletableFuture allows you to define dependencies between asynchronous computations.

**3. Parallel Streams:**
- **Simplicity:** Parallel streams are more concise and easier to use for simple parallel operations on collections.

**4. Use Cases:**
- Use CompletableFuture for complex asynchronous computations requiring fine-grained control and error handling.
- Use parallel streams for simple parallel operations on collections where fine-grained control is not needed.

**Example:**
```java
// Using CompletableFuture
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 1)
        .thenApplyAsync(result -> result + 2)
        .thenApplyAsync(result -> result * 3);

// Using parallel stream
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
int sum = list.parallelStream()
        .mapToInt(i -> i)
        .sum();
```

**Conclusion:**
- Choose CompletableFuture for complex asynchronous computations requiring fine-grained control and error handling.
- Choose parallel streams for simple parallel operations on collections.

**Question: What is the difference between using join() and thenAccept() with CompletableFuture in Java?**

**Answer:**

**1. Introduction:**
- Both join() and thenAccept() are methods in CompletableFuture used to handle the result of an asynchronous computation, but they differ in how they are used and when they block.

**2. join():**
- **Blocking:** join() is a blocking method that waits for the CompletableFuture to complete and returns the result or throws an exception if the computation completed exceptionally.
- **Use Case:** join() is used when you want to block the current thread and obtain the result of the CompletableFuture synchronously.

**3. thenAccept():**
- **Non-Blocking:** thenAccept() is a non-blocking method that registers a callback to be executed asynchronously when the CompletableFuture completes.
- **Use Case:** thenAccept() is used when you want to perform actions asynchronously after the CompletableFuture completes without blocking the current thread.

**4. Example:**
```java
// Using join()
CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 1);
int result1 = future1.join();

// Using thenAccept()
CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 2);
future2.thenAccept(result -> {
    // This code will be executed asynchronously
    System.out.println("Result: " + result);
});
```

**5. Comparison:**
- Use join() when you want to block and obtain the result synchronously.
- Use thenAccept() when you want to perform actions asynchronously after the CompletableFuture completes.

**6. Conclusion:**
- join() is blocking and returns the result synchronously.
- thenAccept() is non-blocking and allows you to perform actions asynchronously after the CompletableFuture completes.