package com.company.library

class Library {

  private var loans = scala.collection.mutable.Map[Book, String]()

  def searchTitle(queryText: String): List[Book] = {
    for {
      book <- Books.all;
      if(book.title contains queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def searchAuthor(queryText: String): List[Book] = {
    for {
      book <- Books.all;
      if(book.author contains queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def searchIsbn(queryText: String): List[Book] = {
    for {
      book <- Books.all;
      if(book.ISBN == queryText)
    } yield {
      println(book.title + " by " + book.author)
      book
    }
  }
  def lend(bookToLend: Book, borrower: String) = {
    loans(bookToLend) = borrower
  }
}
