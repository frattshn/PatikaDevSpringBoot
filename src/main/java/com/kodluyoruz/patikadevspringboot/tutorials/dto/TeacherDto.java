package com.kodluyoruz.patikadevspringboot.tutorials.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Log4j2
public class TeacherDto {

    private Long teacherId;

    @NotEmpty(message = "Name and surname cannot be null.")
    @Size(min = 1, max = 255)
    private String teacherNameSurname;

    @NotEmpty(message = "Email cannot be null.")
    @Email(message = "The email you entered is not in the correct format.")
    private String teacherEmail;

    @NotEmpty(message = "Password cannot be null.")
//    @Min(value = 10, message = "Minimum 10 characters.")
//    @Max(value = 50, message = "Maximum 50 characters.")
    @Size(min = 1, max = 18)
    private String teacherPassword;

}
