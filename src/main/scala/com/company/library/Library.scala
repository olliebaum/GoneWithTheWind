package com.company.library

class Library {
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
}
