package org.foo.ffk.controller;

public record CustomerUpdateRequest (
        String name,String email,String password,Integer age
){
}
