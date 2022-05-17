package org.fishbone.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.fishbone.models.Person;
import org.fishbone.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
            .stream()
            .findAny()
            .orElse(null);
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("select * from person where email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class))
            .stream()
            .findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age , email, address) values (?,?,?,?)", person.getName(),
            person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person personUpdated) {
        jdbcTemplate.update("update person set name=?, age=?, email=?, address=? where id=?",
            personUpdated.getName(),
            personUpdated.getAge(), personUpdated.getEmail(), personUpdated.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public void testMultipleUpdate() {
        List<Person> people = create1000People();
        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("insert into person values(?,?,?,?)", person.getId(), person.getName(),
                person.getAge(),
                person.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println(after - before);
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, 30, "Name" + i, "test" + i + "@mail.ua", "street"));
        }
        return people;
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("insert into person values(?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, people.get(i).getId());
                ps.setString(2, people.get(i).getName());
                ps.setInt(3, people.get(i).getAge());
                ps.setString(4, people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
}
