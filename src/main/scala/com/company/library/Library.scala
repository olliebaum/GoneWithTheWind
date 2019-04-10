package com.company.library
import scala.collection.mutable.ListBuffer

class Library {

  private var loans = new ListBuffer[Loan]()

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
    loans += new Loan(bookToLend, borrower)
  }
}
