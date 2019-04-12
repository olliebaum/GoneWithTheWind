# GoneWithTheWind
Tech test for KiwiPower. A simple library applications that allows for managing of books.

- There are two classes: `Library` and `Book`
- `Books` is an object containing a list of books under the attribute `all`

## Usage
```scala
// First, import the application
scala> import com.company.library._
--> import com.company.library._

// Create a new library
scala> val library1 = new Library()
--> library1: com.company.library.Library = com.company.library.Library@...

// Search your library by book title
scala> library1.searchTitle("Code")
-> Da Vinci Code,The by Brown, Dan
--> res0: List[com.company.library.Book] = List(Book(Da Vinci Code,The,Brown, Dan,pidtkl))

// Search your library by book author
scala> library1.searchAuthor("Mosse")
-> Labyrinth by Mosse, Kate
--> res1: List[com.company.library.Book] = List(Book(Labyrinth,Mosse, Kate,hlpumcxw))

// Search your library by ISBN
scala> library1.searchIsbn("nggzbsum")
-> Life of Pi by Martel, Yann
--> res2: List[com.company.library.Book] = List(Book(Life of Pi by Martel, Yann,nggzbsum))

// Lend a book to someone
scala> library1.lend(library1.books(0), "Tom Hanks")
-> Da Vinci Code,The lent to Tom Hanks

// Check a book is available
scala> library1.checkAvailable(library1.books(0))
--> res3: Boolean = false
```



## Approach

### #search

I decided to use a for loop here after learning how the [Scala 'for' loop is different](https://stackoverflow.com/a/12084651/4215684) than in more declarative-approach languages and is converted to the equivalent of `list.filter(criteria).map(action)`. I could have simply used `filter` but decided to use `for` because it would allow me a simple and performant way to both add a found result to a list and to print the found result using a `yield` block. 

I initially had a `for` loop for each #search...* method but refactored so they all use a common private #search method under the hood. I was hoping for a syntax similar to `foo["bar"]` found in javascript so I could dynamically search on a given variable, i.e. `book[searchType]`  where searchType is one of "title", "author" or "ISBN" given as an argument to the #search method. However, sadly this isn't a thing, so I instead used a  `match` /`case`statement as a definition inside my `for` loop which sets searchField to the value of `book.title`, `book.author` or `book.ISBN` as appropriate. This is perhaps a more secure solution anyway as it means other developers can only use the #search method for the explicitly allowed fields.

### #lend

Since we weren't using databases here I decided to use a Map type to store my data. One of the advantages of this is that I am able to use a syntax similar to a ruby hash or javascript object i.e. `foo["bar"]` allowing me to easily pass an argument variable to the Map to retrieve the relevant data easily. And in addition, I don't have to use a string, I can just simply use a `Book` object as my key meaning that finding a book's borrower is as simple as `loans(someBookObject)`. This approach works fine for simply remembering who a book is loaned to, but when loaned dates + due dates come in to play, it may be more useful to have some kind of Loan object that can handle more complex operations.



### Dependency injection

Since this was quite a basic application, I decided to simply accept a List of Book objects as a parameter to my Library class (and by default use `Books.all`). This allows me to pass a separate list of books to the Library class in my tests. Since the Book class is very simple, it's not a huge issue, but strictly this is not separating the classes for testing as I am still relying upon the "real" Book class. 

Usually my approach would be to accept a dependent class as a parameter to my class, i.e. Library(bookClass = Book). The issue here is that if I had accepted a class in this way, in my tests the type of any passed in class would not match the type Book expected by many of my methods. Hence, why I opted for the simpler approach. 

However, if the Book class were more complicated it would be necessary to fully separate the Book class from my Library tests. In that case I would use the approach of creating a Book trait upon which I could create my real Book class and also change any Type expectations to my Book Trait. Then in the tests I would be able to extend the Book trait to create a mock class which could be passed to my Library class. Since it is an extension of the Book Trait any type expectations in the code will be satisfied while any actual implementation of the class will be entirely different! 😀



### Other improvements

Currently, it is possible to loan out a book that doesn't exist in the library. I could implement a check that occurs in the #lend method which throws an error if the book being loaned isn't in the library.

It might be nice to design the functions such that they can easily be chained together so I could do something like library1.searchTitle("Fire").searchAuthor("J.K. Rowling")(0).loanTo("Jim Carrey").