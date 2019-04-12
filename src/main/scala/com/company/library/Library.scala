package com.company.library

class Library(val books: List[Book] = Books.all) {

  private var loans = scala.collection.mutable.Map[Book, String]()

  def searchTitle(queryText: String): List[Book] = {
    for {
      book <- books;
      if(book.title contains queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def searchAuthor(queryText: String): List[Book] = {
    for {
      book <- books;
      if(book.author contains queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def searchIsbn(queryText: String): List[Book] = {
    for {
      book <- books;
      if(book.ISBN == queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def lend(bookToLend: Book, borrower: String) = {
    if (bookToLend.isReference) {
      throw new Exception("Reference books cannot be loaned out!")
    } else if(checkAvailable(bookToLend)) {
      loans(bookToLend) = borrower
    } else {
      throw new Exception("Book is already on loan!")
    }
  }

  def checkAvailable(book: Book): Boolean = {
    !loans.contains(book)
  }
}
