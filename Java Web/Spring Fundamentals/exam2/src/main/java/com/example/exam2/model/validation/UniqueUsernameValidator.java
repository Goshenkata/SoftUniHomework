package com.example.exam2.model.validation;


import com.example.exam2.repository.UserRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
  private UserRepository userRepository;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return userRepository.
        findByUsername(value).
        isEmpty();
  }
}
