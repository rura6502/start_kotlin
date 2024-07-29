package io.github.rura6502.start_kotlin.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class _01_HelloWorld {

  /**
   * runBlocking은 CoroutineScope의 자식이다.
   * 모든 코루틴은 CoroutineScope로 시작한다.
   * runBlocking은 그 다음 작업을 blocking 한다.
   */
  @Test
  fun test_runBlocking() {
    runBlocking {
      println(this)
      println(coroutineContext) // CoroutineScope가 가지고있는 코루틴 처리 정보
      println(Thread.currentThread().name)
      println("Hello")
    }
    println("test is done...")
  }

  /**
   * launch는 다른 코루틴 코드에 스레드를 양보한다. runBlocking이 main 스레드를 다 사용할 때 까지 기다린다.
   */
  @Test
  fun test_launch__delay() {
    runBlocking {
      launch {
        println("** launch: ${Thread.currentThread().name}")
        println("2");
      }
      println("** runBlocking: ${Thread.currentThread().name}")
      println("1")
      delay(100L)  // Thread.sleep 과 유사하지만 현재 스레드를 해제한다. 해제한 스레드는 다른 코루틴이 사용할 수 있다.
      println("3")
    }
  }

  @Test
  fun test_delay_2() {
    runBlocking {
      launch {
        println(2)
        delay(100L) // 이런식으로 중단되는 점을 suspension point 라고 함
        println(4)
      }
      println(1)
      delay(500L)
      println(3)
    }
  }

}