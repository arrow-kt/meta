package sample

import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTests {
  @Test
  fun testMe() {
    assertTrue(Sample().checkMe() > 0)
  }

  @Test
  fun testHello() {
    assertTrue("None" in hello())
  }
}