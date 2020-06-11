package micronaut.book

import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import javax.validation.constraints.Size

import micronaut.book.domain.Book
import micronaut.book.repository.BookRepository
import java.util.Optional

@Controller("/book")
open class BookController(private val bookRepository: BookRepository) {

    @Get("/")
    fun readAll(): MutableIterable<Book> {
        return bookRepository.findAll()
    }

    @Get("/{id}")
    fun readById(id: Long): Optional<Book> {
        return bookRepository.findById(id)
    }

    @Post("/")
    open fun create(@Size(max = 1024) @Body book: Book): HttpStatus {
        bookRepository.save(book)
        return HttpStatus.CREATED
    }
}