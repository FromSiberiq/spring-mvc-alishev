package ru.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    //Подключение к БД
    private static final String URL = "jdbc:postgresql://localhost:5432/Alishev_spring";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123qwe";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            statement.executeQuery(SQL);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) {
       /* person.setId(++PEOPLE_COUNT);
        people.add(person);*/

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ", '" + person.getName() + "', " +
                    person.getAge() + ", '" +
                    person.getEmail() + "')";
            statement.executeUpdate(SQL);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        /*Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());*/
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
