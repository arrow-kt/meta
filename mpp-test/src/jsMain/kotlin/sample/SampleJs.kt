package sample

import arrow.core.Option
import arrow.core.some

actual class Sample {
  actual fun checkMe() = 12
}

actual object Platform {
  actual val name: Option<String> = "JS".some()
}