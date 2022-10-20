class LibraryCollection {
  capacity;
  books = [];
  constructor(capacity) {
    this.capacity = capacity;
  }
  addBook(bookName, bookAuthor) {
    if (this.books.length >= this.capacity) {
      throw new Error("Not enough space in the collection.");
    } else {
      let book = {
        bookName: bookName,
        bookAuthor: bookAuthor,
        payed: false,
      };
      this.books.push(book);
      return `The ${bookName}, with an author ${bookAuthor}, collect.`;
    }
  }
  payBook(bookName) {
    for (let index = 0; index < this.books.length; index++) {
      const book = this.books[index];
      if (book.bookName == bookName) {
        if (book.payed) {
          throw new Error(`${bookName} has already been paid.`);
        } else {
          this.books[index].payed = true;
          return `${bookName} has been successfully paid.`;
        }
      }
    }
    throw new Error(`${bookName} is not in the collection.`);
  }
  removeBook(bookName) {
    for (let index = 0; index < this.books.length; index++) {
      const book = this.books[index];
      if (book.bookName == bookName) {
        if (book.payed) {
          this.books.splice(index, 1);
          return `${bookName} remove from the collection.`;
        } else {
          throw new Error(
            `${bookName} need to be paid before removing from the collection.`
          );
        }
      }
    }
    throw new Error("The book, you're looking for, is not found.");
  }

  getStatistics(bookAuthor = false) {
    let foundBooks = [];
    if (bookAuthor) {
      for (const book of this.books) {
        if (book.bookAuthor == bookAuthor) {
          foundBooks.push(book);
        }
      }
      if (foundBooks.length == 0) {
        throw new Error(`${bookAuthor} is not in the collection.`);
      } else {
        let out = [];
        foundBooks.forEach((book) => {
          let paid = book.payed ? "Has Paid" : "Not Paid";
          out.push(`${book.bookName} == ${book.bookAuthor} - ${paid}.`);
        });
        return out.join("\n");
      }
    } else {
      let emptySlots = this.capacity - this.books.length;
      let out = [`The book collection has ${emptySlots} empty spots left.`];
      this.books.sort((a, b) => a.bookName.localeCompare(b.bookName));
      this.books.forEach((book) => {
        let paid = book.payed ? "Has Paid" : "Not Paid";
        out.push(`${book.bookName} == ${book.bookAuthor} - ${paid}.`);
      });
      return out.join("\n");
    }
  }
}
const library = new LibraryCollection(5)
library.addBook('Don Quixote', 'Miguel de Cervantes');
library.payBook('Don Quixote');
library.addBook('In Search of Lost Time', 'Marcel Proust');
library.addBook('Ulysses', 'James Joyce');
console.log(library.getStatistics());
