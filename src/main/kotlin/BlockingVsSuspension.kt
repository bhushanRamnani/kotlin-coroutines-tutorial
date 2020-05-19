import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println("Running blocking function")
    measureTimeMillis {
        runBlockingFunction()
    }.let {
        println("Time Taken: $it")
    }

    println("\nRunning suspending function")
    measureTimeMillis {
        runSuspendingFunction()
    }.let {
        println("Time taken: $it")
    }
}

fun runBlockingFunction() = runBlocking {
    // Start a coroutine
    launch(coroutineContext) {
        println("In start : ${getThreadName()}")
        Thread.sleep(200)
        println("In ended : ${getThreadName()}")
    }

    run {
        println("Out start: ${getThreadName()}")
        Thread.sleep(300)
        println("Out ended: ${getThreadName()}")
    }
}

fun runSuspendingFunction() = runBlocking {
    // Start a coroutine
    launch(coroutineContext) {
        println("In start : ${getThreadName()}")
        delay(200)
        println("In ended : ${getThreadName()}")
    }

    run {
        println("Out start: ${getThreadName()}")
        delay(300)
        println("Out ended: ${getThreadName()}")
    }
}

fun getThreadName(): String = Thread.currentThread().name