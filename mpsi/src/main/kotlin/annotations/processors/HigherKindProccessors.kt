package annotations.processors

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class HigherKindProcessor : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
      for (i in element.children) {
          println(i.toString())
      }

  }
}