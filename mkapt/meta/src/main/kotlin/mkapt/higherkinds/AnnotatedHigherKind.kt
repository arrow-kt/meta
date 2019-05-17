package mkapt.higherkinds

import mkapt.common.utils.ClassOrPackageDataWrapper
import javax.lang.model.element.TypeElement

class AnnotatedHigherKind(
  val classElement: TypeElement,
  val classOrPackageProto: ClassOrPackageDataWrapper
)
