import java.lang.StringBuilder
import kotlinx.coroutines.experimental.*

object Esto {
    @JvmStatic
    fun main(args: Array<String>) {
        println("whatsapp")


        runBlocking<Unit> {
            launch {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // just quit after delay
        }

        runBlocking<Unit> {
            val startTime = System.currentTimeMillis()
            val job = launch {
                var nextPrintTime = startTime
                var i = 0
                while (i < 5) { // computation loop, just wastes CPU
                    // print a message twice a second
                    if (System.currentTimeMillis() >= nextPrintTime) {
                        println("I'm sleeping ${i++} ...")
                        nextPrintTime += 500L
                    }
                }
            }
            delay(1300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancelAndJoin() // cancels the job and waits for its completion
            println("main: Now I can quit.")
        }

    }
}