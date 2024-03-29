package org.foo.ffk.controller;

import org.foo.ffk.enums.Gender;

public record CustomerRegistrationRequest (String name, String email, String password, Integer age, Gender gender){}