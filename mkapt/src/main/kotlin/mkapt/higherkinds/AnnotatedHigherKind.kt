package mkapt.higherkinds

import mkapt.internal.ClassOrPackageDataWrapper
import javax.lang.model.element.TypeElement

class AnnotatedHigherKind(
  val classElement: TypeElement,
  val classOrPackageProto: ClassOrPackageDataWrapper
)