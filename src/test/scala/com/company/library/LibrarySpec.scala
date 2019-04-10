package com.company.library

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class LibrarySpec extends FunSuite {

  test("Library #searchTitle can find a book by partial title") {
    val library1 = new Library()
    library1.searchTitle("Code")(0).title shouldBe "Da Vinci Code,The"
  }

  test("Library #searchAuthor can find a book by partial name") {
    val library1 = new Library()
    library1.searchAuthor("Mosse")(0).title shouldBe "Labyrinth"
  }

  test("Library #searchIsbn can find a book by full ISBN") {
    val library1 = new Library()
    library1.searchIsbn("nggzbsum")(0).title shouldBe "Life of Pi"
  }

  test("Library #searchIsbn can't find a book by partial ISBN") {
    val library1 = new Library()
    library1.searchIsbn("nggzbsu") shouldBe List()
  }

}
