package org.testclasses;

import base.RoughBase;

public class TestRoughClass extends RoughBase {

    private String greetings;
    public void roughBeforeClass() {
        System.out.println("Hello from the test rough class" + greetings );
    }


}

class main {


    public static void main(String[] args) {
        TestRoughClass check = new TestRoughClass();
        check.roughBeforeClass();
    }
}
