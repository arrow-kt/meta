package mkapt.mpp.common

class ForOption private constructor() {
  companion object
}
typealias OptionOf<A> = Kind<ForOption, A>

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> OptionOf<A>.fix(): Option<A> =
  this as Option<A>

