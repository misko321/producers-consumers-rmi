# producers-consumers-rmi

## Description

This project contains a simple implementation of a distributed [Producer-Consumer problem](https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem) based on **Java RMI (Remote Method Invocation)**.

As a mean of practice a pair of semaphores was implemented (i.e. *fair* and *unfair* semaphore) that employ solely Java's `synchronized` primitive.

## Semaphores

There are two available semaphores: `FairSemaphore` and `UnfairSemaphore`. Both are subclasses of `abstract class Semaphore`, which provides typical semaphore actions for acquiring and releasing *critical section*, that is:
```
//acquire specific number of units, default is 1
acquire(int units);
acquire(); === acquire(1);

//release specific number of units, default is 1
release(int units);
release(); === release(1);
```

Those two differ only in implementation details:  

`FairSemaphore` **ensures that no starvation occurs**. It employs a queue that stores which one of the competing threads should enter the critical section next.

`UnfairSemaphore` in turn, **prioritizes not on equity, but on throughput**. Every time there is enough units for any single of waiting threads to go on, that thread is allowed to enter the critical section (if there are more such threads, an undetermined one is getting awaken). Note that this behavior **may lead to starvation** of threads that require more semaphore units than others.

## Producer-consumer code

Producer and consumer code for accessing the buffer that draws on built semaphores is following:

```
cs = new FairSemaphore();
full = new FairSemaphore(0);
empty = new UnfairSemaphore(bufferSize);

//producer
public void add(int count) {
  empty.acquire(count);
  cs.acquire();
  //[ADD ITEM]
  cs.release();
  full.release(count);
}

//consumer
public void remove(int count) {
  full.acquire(count);
  cs.acquire();
  //[REMOVE ITEM]
  cs.release();
  empty.release(count);
}
```
The reason for the `empty` semaphore to be an instance of `UnfairSemaphore` is simple. If it was fair a situation might occur, when Producer that puts more than 1 unit would have insufficient free space in buffer to proceed and would be forced to wait blocking the access. If at the same time Consumer would require all buffer units to work, he would have to wait as well. **That would effectively lead to a deadlock**. However, when `UnfairSemaphore` is used instead, remaining producers (e.g. those that produce 1 unit) can do their job normally and fill the buffer.

## Examples

There are 2 examples in the project:

#### Local semaphores test

The first one (located in *src/local/*) is a test of semaphores with multiple local threads. Analyzing the output of `LocalSemaphoreTest` you can notice that threads are treated fair while competing for buffer access.

#### RMI

The second example (located in *src/rmi/*) utilizes Java RMI to build a server that provides access to shared buffer, alongside with consumers and producers who want to gain that access.

`ProducersConsumers` server takes 2 arguments, i.e. [port number] that the server should run at and the [capacity] of the buffer.  
`Producer` and `Consumer` take 2 arguments, i.e. [address:port] of the RMI server and number of [units] to produce/consume each round.

**Note:** Although the producers and consumers are distributed processes, the critical section itself is acquired and released only locally (on the server). This is NOT a *distributed mutual exclusion*.

## Project file structure
All source code is located under subdirectories of **src/** directory:
 - *local/* contains local test of semaphores (without RMI),
 - *rmi/* contains example of RMI server, producer and consumer,
 - *common/* contains code shared by local and RMI examples,
 - *semaphore/* contains semaphore classes.

## License
This project is released under [MIT License](http://choosealicense.com/licenses/mit/).
