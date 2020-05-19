import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    measureTimeMillis {
        getList()
    }.let { println("Time taken for list generation: $it") }

    measureTimeMillis {
        getFlow().collect {
            println("Collecting $it")
        }
    }.let { println("Time taken for flow generation: $it") }
}

suspend fun getList(): List<Int> {
    val list = mutableListOf<Int>()

    for (i in 1..5) {
        delay(200)
        println("Adding $i to list")
        list.add(i)
    }
    return list
}

suspend fun getFlow(): Flow<Int> = flow {
    for(i in 1..5) {
        delay(200)
        println("Emitting $i")
        emit(i)
    }
}