package com.FKFabian.medicalclinic.common;

import com.FKFabian.medicalclinic.model.Patient;
import com.FKFabian.medicalclinic.model.PatientCreateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.FKFabian.medicalclinic.common.PatientValidator.checkIfAnyValueIsNull;
import static com.FKFabian.medicalclinic.common.PatientValidator.validatePatientDataToChange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PatientValidatorTest {
    @Test
    void checkIfAnyValueIsNull_emailIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto(null
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333", LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("Email cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_passwordIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , null, "idCard1", "John"
                , "Smith", "333-333-333", LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("Password cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_idCardNoIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , "pass1", null, "John"
                , "Smith", "333-333-333", LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("IDCardNo cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_firstNameIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , "pass1", "idCard1", null
                , "Smith", "333-333-333", LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("First name cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_lastNameIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , "pass1", "idCard1", "John"
                , null, "333-333-333", LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("Last name cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_phoneNumberIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , "pass1", "idCard1", "John"
                , "Smith", null, LocalDate.of(2000, 2, 2));
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("Phone number cannot be null", result.getMessage());
    }

    @Test
    void checkIfAnyValueIsNull_birthdayIsNull_ThrowsIllegalArgumentException() {
        // given
        PatientCreateDto patientCreateDto = new PatientCreateDto("email"
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333", null);
        //when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patientCreateDto));
        // then
        assertEquals("Birthday cannot be null", result.getMessage());
    }

    @Test
    void validatePatientDataToChange_AnotherIdCard_ThrowsIllegalArgumentException() {
        // given
        Patient patient = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));
        PatientCreateDto patientCreateDto = new PatientCreateDto("patient@gmail.com"
                , "pass1", "idCard2", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 12, 5));
        // when
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class
                , () -> validatePatientDataToChange(patient, patientCreateDto));
        // then
        assertEquals("You cannot change ID card number", result.getMessage());
    }

    @Test
    void validatePatientDataToChange_CorrectData_VerifyIdCardNo() {
        //given
        Patient patient = new Patient(1L, "patient1@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "444-444-444"
                , LocalDate.of(2000, 12, 5));
        PatientCreateDto patientCreateDto = new PatientCreateDto("patient@gmail.com"
                , "pass1", "idCard1", "John"
                , "Smith", "333-333-333"
                , LocalDate.of(2000, 12, 5));
        // when
        validatePatientDataToChange(patient, patientCreateDto);
        // then
        assertEquals(patient.getIdCardNo(), patientCreateDto.getIdCardNo());
    }

    @ParameterizedTest
    @MethodSource("providePatientWithNullValue")
    void checkIfAnyValueIsNull_incorrectData_ExceptionThrown(PatientCreateDto patient, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class
                , () -> checkIfAnyValueIsNull(patient));
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> providePatientWithNullValue() {
        return Stream.of(
                Arguments.of(new PatientCreateDto(null
                        , "pass1", "idCard1", "John"
                        , "Smith", "333-333-333"
                        , LocalDate.of(2000, 12, 5)), "Email cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , null, "idCard1", "John"
                                , "Smith", "333-333-333"
                                , LocalDate.of(2000, 12, 5)),
                        "Password cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , "pass1", null, "John"
                                , "Smith", "333-333-333"
                                , LocalDate.of(2000, 12, 5)),
                        "IDCardNo cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , "pass1", "idCard1", null
                                , "Smith", "333-333-333"
                                , LocalDate.of(2000, 12, 5)),
                        "First name cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , "pass1", "idCard1", "John"
                                , null, "333-333-333"
                                , LocalDate.of(2000, 12, 5)),
                        "Last name cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , "pass1", "idCard1", "John"
                                , "Smith", null
                                , LocalDate.of(2000, 12, 5)),
                        "Phone number cannot be null"),
                Arguments.of(new PatientCreateDto("email"
                                , "pass1", "idCard1", "John"
                                , "Smith", "333-333-333"
                                , null),
                        "Birthday cannot be null"));

    }
}


