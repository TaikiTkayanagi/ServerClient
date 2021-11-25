package com.example;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
  private String name;
  private int age;

  public Person(){}

  public Person(String name, int age){
    this.name = name;
    this.age = age;
  }

  public String getName(){
    return this.name;
  }

  public int getAge(){
    return this.age;
  }
}
