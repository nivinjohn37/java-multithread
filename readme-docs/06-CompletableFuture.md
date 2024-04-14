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

CompletableFuture provides a powerful way to work with asynchronous computations in Java, enabling you to write more responsive and efficient code.