import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val timeTaken = measureTimeMillis {
        startTonsOfCoroutines()
    }
    println("Total Time taken in milliseconds: $timeTaken")
}

fun startTonsOfCoroutines() = runBlocking {
    repeat(10_000) {
        launch {
            println("Launching thread number: $it")
        }
    }
}