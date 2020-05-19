import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        startTonsOfThreads()
    }
    println("Total Time taken in milliseconds: $timeTaken")
}

fun startTonsOfThreads() = runBlocking {
    repeat(10_000) {
        thread {
            println("Launching thread number: $it")
        }
    }
}