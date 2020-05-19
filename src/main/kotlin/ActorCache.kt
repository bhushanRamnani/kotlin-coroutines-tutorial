import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor

sealed class CacheMessage
class PutItem(val name: String, val age: Int): CacheMessage()
class GetItem(val name: String, val response: CompletableDeferred<Int?>): CacheMessage()

// This function launches a new counter actor
fun CoroutineScope.cacheActor() = actor<CacheMessage> {
    val cacheMap = mutableMapOf<String, Int>() // actor state

    for (msg in channel) { // iterate over incoming messages
        when (msg) {
            is PutItem -> cacheMap[msg.name] = msg.age
            is GetItem -> msg.response.complete(cacheMap[msg.name])
        }
    }
}

fun main() = runBlocking<Unit> {
    val cache = cacheActor() // create the actor
    withContext(Dispatchers.Default) {
        async { cache.send(PutItem("Tom", 12)) }
        async { cache.send(PutItem("Lisa", 10)) }
        async {
            var age: Int? = null
            while (age == null) {
                val response = CompletableDeferred<Int?>()
                cache.send(GetItem("Tom", response))
                age = response.await()
                println("Tom's age = $age")
            }
        }
    }
    cache.close()
}