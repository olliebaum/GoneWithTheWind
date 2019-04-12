package com.company.library
import java.time.LocalDate;
import java.time.format.DateTimeFormatter

class Library(val books: List[Book] = Books.all) {

  private var loans = scala.collection.mutable.Map[Book, Map[String, String]]()

  private val today = LocalDate.now()
  private val todayStr = today.format(DateTimeFormatter.ISO_LOCAL_DATE)

  private def search(queryText: String, searchType: String): List[Book] = {
    for {
      book <- books
      searchField = searchType match {
        case "title" => book.title
        case "isbn" => book.ISBN
        case "author" => book.author
      }
      if(searchField contains queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }

  def searchTitle(queryText: String): List[Book] = {
    this.search(queryText, "title")
  }
  def searchAuthor(queryText: String): List[Book] = {
    this.search(queryText, "author")
  }
  def searchIsbn(queryText: String): List[Book] = {
    this.search(queryText, "isbn")
  }

  def lend(bookToLend: Book, borrower: String, date: String = todayStr): Unit = {
    if (bookToLend.isReference) {
      throw new Exception("Reference books cannot be loaned out!")
    } else if(checkAvailable(bookToLend)) {
      loans(bookToLend) = Map("borrower" -> borrower, "loanDate" -> date)
      println(bookToLend.title + " lent to " + borrower)
    } else {
      throw new Exception("Book is already on loan!")
    }
  }

  def checkAvailable(book: Book): Boolean = {
    !loans.contains(book)
  }

  def giveBack(bookToReturn: Book): Unit = {
    if (checkAvailable(bookToReturn)) {
      throw new Exception("Book was not on loan in the first place!")
    } else {
      loans -= bookToReturn
    }
  }

  def getBorrower(bookOnLoan: Book): String = {
    loans(bookOnLoan)("borrower")
  }

  def isOverdue(book: Book): Boolean = {
    LocalDate.parse(loans(book)("loanDate")).plusDays(14).isBefore(today)
  }

  def getOverdue(): List[Book] = {
    val overdueBooks = for {
      (loanedBook, v) <- loans
      if (isOverdue(loanedBook))
    } yield {loanedBook}
    overdueBooks.toList
  }

  def getOverdueBorrowers(): List[String] = {
    for {overdueBook <- getOverdue()} yield { getBorrower(overdueBook) }
  }

  def getAllOnLoan(): List[Book]= {
    val onLoanBooks = for {(loanedBook, v) <- loans} yield {loanedBook}
    onLoanBooks.toList
  }

  def getCurrentBorrowers(): List[String] = {
    val currentBorrowers = for {(k, v) <- loans} yield {v("borrower")}
    currentBorrowers.toList
  }

}
