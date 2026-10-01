# Spring AOP Labs

Hands-on labs for learning **Aspect-Oriented Programming (AOP) in Spring**, built step by step: first the Proxy pattern by hand, then Spring's classic AOP API, then the annotation style.

The idea behind all three labs: keep the business classes clean and attach cross-cutting concerns (logging, timing, caching, error handling) from the outside, using proxies.

> Part of my NTI training. Plain Spring Core is used throughout, **no Spring Boot**.

---

## Labs

| # | Lab | Focus |
|---|-----|-------|
| 1 | [Lap1 - JDK Dynamic Proxy](./Lap1%20-%20JDK%20Dynamic%20Proxy) | Build a proxy by hand with `java.lang.reflect.Proxy` and `InvocationHandler` |
| 2 | [Lap2 - Spring AOP Advice Types](./Lap2%20-%20Spring%20AOP%20Advice%20Types) | Spring's classic advice interfaces, `ProxyFactory`, `ProxyFactoryBean`, pointcuts |
| 3 | [Lap3 - Spring AOP Annotations](./Lap3%20-%20Spring%20AOP%20Annotations) | `@Aspect`, the 5 advice annotations, `@Pointcut`, custom `@Cacheable` |

---

## Lab 1: JDK Dynamic Proxy

Plain Java, no Spring. A `NotificationService` (`sendEmail`, `sendSms`) is wrapped by a dynamic proxy.

- `LoggingHandler` (an `InvocationHandler`) logs the method name and arguments before the call, and the return value after it
- Timing is measured inside the same handler
- The real method is called with `method.invoke(target, args)`. Passing `proxy` instead of `target` would cause infinite recursion
- `InvocationTargetException` is unwrapped so callers see the original exception

**Concepts:** Proxy pattern, `Proxy.newProxyInstance`, `InvocationHandler`, reflection.

---

## Lab 2: Spring AOP Advice Types

An `InventoryService` (`checkStock`, `reserveStock`) is advised using Spring's classic interfaces.

| Advice | Interface | Behavior |
|--------|-----------|----------|
| Before | `MethodBeforeAdvice` | Logs every call (name + arguments) |
| After returning | `AfterReturningAdvice` | Logs the return value of `checkStock` |
| Throws | `ThrowsAdvice` | Logs exceptions thrown by `reserveStock` |
| Around | `MethodInterceptor` | Times every call, `try/finally` for "runs no matter what" |

- The proxy is built first with `ProxyFactory`, then wired as a container-managed bean with `ProxyFactoryBean` + `@Configuration`
- **Bonus:** `NameMatchMethodPointcut` restricts the before-advice to `reserveStock` only

**Expected order** (each advice wraps the next one):

```
success:    [BEFORE] -> [AROUND-START] -> [AROUND-FINALLY] -> [AFTER-RETURNING]
exception:  [BEFORE] -> [AROUND-START] -> [AROUND-FINALLY] -> [AFTER-THROWING]
```

**Concepts:** Advice, Pointcut, Advisor, `ProxyFactory`, `ProxyFactoryBean`.

---

## Lab 3: Spring AOP Annotations

The same mechanism as Lab 2, with the annotation style. The target is a `ProductService` with no interface, so Spring uses a **CGLIB** proxy.

**Part A: the 5 core advice annotations**
1. `@Before`
2. `@AfterReturning`
3. `@AfterThrowing`
4. `@After`
5. A single `@Around` that replaces all four above

**Part B: declarative features with `@annotation()`**
6. A custom `@Cacheable` annotation + `CachingAspect` (cache miss on the first call, cache hit on the next)

Also shows the **self-invocation** limitation: a call to `this.findProduct()` from inside the same class bypasses the proxy, so no advice runs.

**Concepts:** `@Aspect`, `@EnableAspectJAutoProxy`, `execution()`, `@Pointcut`, `@annotation()`, `ProceedingJoinPoint`, CGLIB, self-invocation.

---

## Classic API vs. Annotations

| Lab 2 (classic) | Lab 3 (annotations) |
|-----------------|---------------------|
| `MethodBeforeAdvice` | `@Before` |
| `AfterReturningAdvice` | `@AfterReturning` |
| `ThrowsAdvice` | `@AfterThrowing` |
| `MethodInterceptor` | `@Around` |
| `try/finally` inside an interceptor | `@After` |
| `NameMatchMethodPointcut` | `execution(...)`, `@annotation(...)` |
| `ProxyFactory` / `ProxyFactoryBean` | `@EnableAspectJAutoProxy` |

Both styles produce the same thing underneath: **Spring AOP runtime proxies** (JDK dynamic proxy or CGLIB).

---

## Tech Stack

- Java 17+
- Spring Framework 6.1.0 (`spring-context`, `spring-aop`)
- AspectJ Weaver 1.9.21 (used only for the annotation types and the `execution()` parser, no AspectJ weaving)
- Maven

---

## How to Run

Open a lab folder in your IDE (IntelliJ IDEA / Eclipse / VS Code) and run its `main` class, or use Maven from inside the lab folder:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=<main-class>
```

| Lab | Main class |
|-----|-----------|
| Lab 1 | `com.example.lab1.Lab1Main` |
| Lab 2, ProxyFactory | `com.example.lab2.Lab2ProxyFactoryMain` |
| Lab 2, ProxyFactoryBean | `com.example.lab2.Lab2SpringMain` |
| Lab 2, bonus | `com.example.lab2.Lab2BonusMain` |
| Lab 3, four advices | `com.example.lab3.Lab3PartAFourAdvicesMain` |
| Lab 3, `@Around` | `com.example.lab3.Lab3PartAAroundMain` |
| Lab 3, `@Cacheable` | `com.example.lab3.Lab3PartBCacheMain` |

---

## Key Takeaways

- A proxy lets you add behavior without touching the real class
- Spring AOP only advises method calls on **Spring-managed beans**, through a proxy
- Forgetting `proceed()` (or `method.invoke`) means the real method never runs
- `@Transactional` and Spring's `@Cacheable` are built on this same `@Around` mechanism
- Self-invocation bypasses the proxy, which is the most common AOP gotcha

---

## Author

**Abdelrahman Osama**: [@Abdelrahman-Osama174](https://github.com/Abdelrahman-Osama174)