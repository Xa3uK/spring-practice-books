package org.fishbone.dao;

import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("select * from person where id=?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class))
//            .stream()
//            .findAny()
//            .orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("insert into person (full_name, date_of_birth) values (?,?)",
//            person.getFullName(), person.getDateOfBirth());
//    }
//
//    public void update(int id, Person personUpdated) {
//        jdbcTemplate.update("update person set full_name=?, date_of_birth=? where id=?",
//            personUpdated.getFullName(), personUpdated.getDateOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from person where id=?", id);
//    }
//
//    public Optional<Person> getPersonByFullName(String fullName){
//        return jdbcTemplate.query("select * from person where full_name=?", new Object[]{fullName},
//            new BeanPropertyRowMapper<>(Person.class))
//            .stream()
//            .findAny();
//    }
//
//    public List<Book> getBooksByPersonId(int id){
//        return jdbcTemplate.query("select * from books where user_id=?", new Object[]{id},
//            new BeanPropertyRowMapper<>(Book.class));
//    }
}
