package org.fishbone.dao;

import java.util.List;
import java.util.Optional;
import org.fishbone.models.Book;
import org.fishbone.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from books where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class))
            .stream()
            .findAny()
            .orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into books (title, author, year) values (?,?,?)",
            book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update books set title=?, author=?, year=? where id=?",
            book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from books where id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select person.* from books join person on books.user_id = person.id where books"
                    + ".id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
            .stream()
            .findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("update books set user_id=null where id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("update books set user_id=? where id=?", person.getId(), id);
    }
}
