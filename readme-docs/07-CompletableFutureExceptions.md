## CompletableFuture Exception Handling

### Functional Style of Handling Exceptions

CompletableFuture provides several methods for handling exceptions in a functional style. These methods allow you to catch exceptions and recover from them, or catch exceptions and not recover, depending on your requirements.

### `handle()`

The `handle()` method allows you to catch an exception and recover from it by returning a default value. It takes a `BiFunction` that handles both the result and the exception, and returns a new CompletableFuture with the result of the function.

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    throw new RuntimeException("Exception occurred");
});

CompletableFuture<Integer> result = future.handle((res, ex) -> {
    if (ex != null) {
        System.err.println("Exception occurred: " + ex.getMessage());
        return -1; // Recover by returning a default value
    }
    return res;
});
```

### `exceptionally()`

The `exceptionally()` method is similar to `handle()`, but it only handles exceptions and does not have access to the result. It takes a `Function` that handles the exception and returns a new CompletableFuture with a default value.

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    throw new RuntimeException("Exception occurred");
});

CompletableFuture<Integer> result = future.exceptionally(ex -> {
    System.err.println("Exception occurred: " + ex.getMessage());
    return -1; // Recover by returning a default value
});
```

### `whenComplete()`

The `whenComplete()` method allows you to perform an action when the CompletableFuture completes, either successfully or exceptionally. It takes a `BiConsumer` that handles both the result and the exception, but it does not return a CompletableFuture.

```java
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Perform some computation asynchronously
    throw new RuntimeException("Exception occurred");
});

future.whenComplete((res, ex) -> {
    if (ex != null) {
        System.err.println("Exception occurred: " + ex.getMessage());
    } else {
        System.out.println("Result: " + res);
    }
});
```

### Difference Between `handle()` and `exceptionally()`

- `handle()` allows you to recover from an exception by returning a default value or another result. It has access to both the result and the exception.
- `exceptionally()` only allows you to handle the exception and return a default value. It does not have access to the result.

---

By using `handle()`, `exceptionally()`, and `whenComplete()`, you can handle exceptions in a functional style, making your code more resilient and easier to maintain.