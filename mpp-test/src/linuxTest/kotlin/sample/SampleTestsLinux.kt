package sample

import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTestsLinux {
  @Test
  fun testHello() {
    assertTrue("Linux" in hello())
  }
}