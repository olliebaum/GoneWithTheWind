package com.company.library

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.scalatest.Matchers._

class LibrarySpec extends FunSuite with BeforeAndAfter {

  var library1: Library = _
  before {
    library1 = new Library(List(
      Book("Da Vinci Code,The", "Brown, Dan", "pidtkl"),
      Book("Labyrinth", "Mosse, Kate", "hlpumcxw"),
      Book("Life of Pi", "Martel, Yann", "nggzbsum"),
      Book("Programming in Scala", "Odersky, Martin", "scygzxyc", true)
    ))
  }
  test("Library #searchTitle can find a book by partial title") {
    library1.searchTitle("Code")(0).title shouldBe "Da Vinci Code,The"
  }

  test("Library #searchAuthor can find a book by partial name") {
    library1.searchAuthor("Mosse")(0).title shouldBe "Labyrinth"
  }

  test("Library #searchIsbn can find a book by full ISBN") {
    library1.searchIsbn("nggzbsum")(0).title shouldBe "Life of Pi"
  }

  test("#checkAvailable is false when book is loaned out") {
    library1.lend(library1.books(0), "Desmond Tutu")
    library1.checkAvailable(library1.books(0)) shouldBe false
  }

  test("#checkAvailable is true when book hasn't been loaned out") {
    library1.checkAvailable(library1.books(0)) shouldBe true
  }

  test("#lend throws exception when book is on loan") {
    library1.lend(library1.books(0), "Desmond Tutu")
    val thrown = intercept[Exception] {
      library1.lend(library1.books(0), "Jeremy Corbyn")
    }
    thrown.getMessage shouldBe "Book is already on loan!"
  }

  test("#lend throws exception if you try to loan a reference book") {
    val thrown = intercept[Exception] {
      library1.lend(library1.books.last, "Theresa May")
    }
    thrown.getMessage shouldBe "Reference books cannot be loaned out!"
  }

  test("#giveBack makes a book available again") {
    library1.lend(library1.books(0), "James Dean")
    library1.giveBack(library1.books(0))
    library1.checkAvailable(library1.books(0)) shouldBe true
  }

  test("#getBorrower returns name of current borrower") {
    library1.lend(library1.books(0), "Les Dawson")
    library1.getBorrower(library1.books(0)) shouldBe "Les Dawson"
  }
}
