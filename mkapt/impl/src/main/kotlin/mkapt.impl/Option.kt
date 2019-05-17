import mkapt.annotations.higherkind

/**
 * Represents optional values. Instances of `Option`
 * are either an instance of $some or the object $none.
 */
@higherkind
sealed class Option<out A> : OptionOf<A> {

  companion object {

    /**
     * Lifts a pure [A] value to [Option]
     *
     * {: data-executable='true'}
     *
     * ```kotlin:ank
     * import arrow.core.Option
     * fun main(args: Array<String>) {
     * //sampleStart
     * val result: Option<Int> = Option.just(1)
     * //sampleEnd
     * println(result)
     * }
     * ```
     *
     */
    fun <A> just(a: A): Option<A> = Some(a)

    fun <A> fromNullable(a: A?): Option<A> = if (a != null) Some(a) else None

    operator fun <A> invoke(a: A): Option<A> = Some(a)

    fun <A> empty(): Option<A> = None

  }

  /**
   * Returns true if the option is [None], false otherwise.
   * @note Used only for performance instead of fold.
   */
  abstract fun isEmpty(): Boolean

  /**
   * alias for [isDefined]
   */
  fun nonEmpty(): Boolean = isDefined()

  /**
   * Returns true if the option is an instance of [Some], false otherwise.
   * @note Used only for performance instead of fold.
   */
  fun isDefined(): Boolean = !isEmpty()

  /**
   * Returns a [Some<$B>] containing the result of applying $f to this $option's
   * value if this $option is nonempty. Otherwise return $none.
   *
   * @note This is similar to `flatMap` except here,
   * $f does not need to wrap its result in an $option.
   *
   * @param f the function to apply
   * @see flatMap
   */
  inline fun <B> map(f: (A) -> B): Option<B> =
    flatMap { a -> Some(f(a)) }

  fun <B> mapFilter(f: (A) -> Option<B>): Option<B> =
    flatMap { a -> f(a).fold({ empty<B>() }, { just(it) }) }

  inline fun <R> fold(ifEmpty: () -> R, ifSome: (A) -> R): R = when (this) {
    is None    -> ifEmpty()
    is Some<A> -> ifSome(t)
  }

  /**
   * Returns the result of applying $f to this $option's value if
   * this $option is nonempty.
   * Returns $none if this $option is empty.
   * Slightly different from `map` in that $f is expected to
   * return an $option (which could be $none).
   *
   * @param f the function to apply
   * @see map
   */
  inline fun <B> flatMap(f: (A) -> OptionOf<B>): Option<B> =
    when (this) {
      is None -> this
      is Some -> f(t).fix()
    }

  fun toList(): List<A> = fold(::emptyList) { listOf(it) }

  infix fun <X> and(value: Option<X>): Option<X> = if (isEmpty()) {
    None
  } else {
    value
  }
}

object None : Option<Nothing>() {
  override fun isEmpty() = true

  override fun toString(): String = "None"
}

data class Some<out T>(val t: T) : Option<T>() {
  override fun isEmpty() = false

  override fun toString(): String = "Some($t)"
}

/**
 * Returns this option's if the option is nonempty, otherwise
 * returns another option provided lazily by `default`.
 *
 * @param alternative the default option if this is empty.
 */
inline fun <A> OptionOf<A>.orElse(alternative: () -> Option<A>): Option<A> =
  if (fix().isEmpty()) alternative() else fix()

infix fun <T> OptionOf<T>.or(value: Option<T>): Option<T> = if (fix().isEmpty()) {
  value
} else {
  fix()
}

fun <T> T?.toOption(): Option<T> = this?.let { Some(it) } ?: None

fun <A> Boolean.maybe(f: () -> A): Option<A> =
  if (this) {
    Some(f())
  } else {
    None
  }

fun <A> A.some(): Option<A> = Some(this)

fun <A> none(): Option<A> = None

fun <T> Iterable<T>.firstOrNone(): Option<T> = this.firstOrNull().toOption()

fun <T> Iterable<T>.firstOrNone(predicate: (T) -> Boolean): Option<T> =
  this.firstOrNull(predicate).toOption()

fun <T> Iterable<T>.singleOrNone(): Option<T> = this.singleOrNull().toOption()

fun <T> Iterable<T>.singleOrNone(predicate: (T) -> Boolean): Option<T> =
  this.singleOrNull(predicate).toOption()

fun <T> Iterable<T>.lastOrNone(): Option<T> = this.lastOrNull().toOption()

fun <T> Iterable<T>.lastOrNone(predicate: (T) -> Boolean): Option<T> =
  this.lastOrNull(predicate).toOption()

fun <T> Iterable<T>.elementAtOrNone(index: Int): Option<T> = this.elementAtOrNull(index).toOption()
