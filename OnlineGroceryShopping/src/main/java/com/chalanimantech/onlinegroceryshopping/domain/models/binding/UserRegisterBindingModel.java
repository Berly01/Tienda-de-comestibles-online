package com.chalanimantech.onlinegroceryshopping.domain.models.binding;

import com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages;
import com.chalanimantech.onlinegroceryshopping.validation.annotation.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.INCORRECT_PASSWORD;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.INCORRECT_USERNAME;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.INCORRECT_ADDRESS;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.USER_NAME_MIN_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.USER_NAME_MAX_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.PASSWORD_MIN_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.VALID_EMAIL_ADDRESS_REGEX;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String address;
    private String email;

    public UserRegisterBindingModel() {}

    @NotNull(message = INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG)
    @Size(min = USER_NAME_MIN_LENGTH, max = USER_NAME_MAX_LENGTH, message = INCORRECT_USERNAME)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = htmlEscape(username);
    }

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, message = INCORRECT_PASSWORD)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = htmlEscape(password);
    }


    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = htmlEscape(confirmPassword);
    }

    @NotNull
    @Size(min = 5, message = INCORRECT_ADDRESS)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = htmlEscape(address);
    }

    @NotNull
    @Email(regexp = VALID_EMAIL_ADDRESS_REGEX, message = ExceptionMessages.INCORRECT_EMAIL)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = htmlEscape(email);
    }

    private String htmlEscape(String input){
        input = input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");

        return input;
    }
}
