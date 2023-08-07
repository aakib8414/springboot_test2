package com.test.test1.actuator;

public class RecordClassDemo {
    public static void main(String[] args) {
        Person person = new Person("John", "Doe", 30);

        System.out.println(person.firstName()); // Accessing the firstName field
        System.out.println(person.lastName());  // Accessing the lastName field
        System.out.println(person.age());       // Accessing the age field

        System.out.println(person); // Prints the automatically generated toString

        Person anotherPerson = new Person("Jane", "Smith", 25);
        System.out.println(person.equals(anotherPerson)); // Uses t
    }
}

