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
scala> library1.searchIsbn("nggzbsum") //N.B. Partial searches will return empty
-> Life of Pi by Martel, Yann
--> res2: List[com.company.library.Book] = List(Book(Life of Pi by Martel, Yann,nggzbsum))

// Lend a book to someone
scala> library1.lend(library1.books(0), "Tom Hanks")
-> Da Vinci Code,The lent to Tom Hanks

// Check a book is available
scala> library1.checkAvailable(library1.books(0))
--> res3: Boolean = false
```