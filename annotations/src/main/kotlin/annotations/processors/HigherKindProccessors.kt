package annotations.processors

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

abstract class HigherKindProcessor : Annotator {

  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    sayHello()
  }

  abstract fun sayHello()
}