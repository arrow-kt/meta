package mkapt.higherkinds

import mkapt.annotations.higherkind

val higherKindsAnnotationKClass = higherkind::class
val higherKindsAnnotationClass = higherKindsAnnotationKClass.java
val higherKindsAnnotationName = "@" + higherKindsAnnotationClass.simpleName
