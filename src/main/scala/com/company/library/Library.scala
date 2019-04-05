package com.company.library

class Library {
  def searchTitle(queryText: String): List[Book] = {
    for {
      book <- Books.all;
      if(book.title contains queryText)
    } yield {
      println(book.title)
      book
    }
  }
}
