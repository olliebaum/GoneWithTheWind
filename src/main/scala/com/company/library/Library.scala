package com.company.library

class Library(val books: List[Book] = Books.all, bookClass = Book) {

  private var loans = scala.collection.mutable.Map[Book, String]()

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

  def lend(bookToLend: Book, borrower: String): Unit = {
    if (bookToLend.isReference) {
      throw new Exception("Reference books cannot be loaned out!")
    } else if(checkAvailable(bookToLend)) {
      loans(bookToLend) = borrower
      println(bookToLend.title + " lent to " + borrower)
    } else {
      throw new Exception("Book is already on loan!")
    }
  }

  def checkAvailable(book: Book): Boolean = {
    !loans.contains(book)
  }
}
