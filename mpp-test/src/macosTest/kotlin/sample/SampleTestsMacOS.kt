package sample

import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTestsMacOS {
  @Test
  fun testHello() {
    assertTrue("MacOS" in hello())
  }
}