package sample

import arrow.core.Option

expect class Sample() {
  fun checkMe(): Int
}

expect object Platform {
  val name: Option<String>
}

fun hello(): String = "Hello from ${Platform.name}"