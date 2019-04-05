package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class LibrarySpec extends FunSuite {

  test("Library #searchTitle can find a book by partial title") {
    val libr1 = new Library()
    libr1.searchTitle("Code")(0).title shouldBe "Da Vinci Code,The"
  }

}
