package mreflx.annotations

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@Retention(RUNTIME)
@Target(CLASS)
@MustBeDocumented
annotation class higherkind(
  val forClass: KClass<*> // what class to create higherkind for
)
