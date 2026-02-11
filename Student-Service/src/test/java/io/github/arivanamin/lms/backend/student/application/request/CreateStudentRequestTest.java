package io.github.arivanamin.lms.backend.student.application.request;

import io.github.arivanamin.lms.backend.core.domain.gender.Gender;
import io.github.arivanamin.lms.backend.student.domain.entity.*;
import io.github.arivanamin.lms.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CreateStudentRequestTest implements BaseUnitTest {

    @Test
    void toDomainEntityShouldMapRequestToDomain () {
        // given
        CreateStudentRequest request = getCreateStudentRequest();
        request.setDateOfBirth(LocalDate.now());

        // when
        Student result = request.toDomain();

        // then
        assertThat(result.getDateOfBirth()).isEqualTo(request.dateOfBirth);
        assertThat(result).usingRecursiveComparison()
            .ignoringFields("dateOfBirth")
            .ignoringActualNullFields()
            .isEqualTo(request);
    }

    private CreateStudentRequest getCreateStudentRequest () {
        CreateStudentRequest request = new CreateStudentRequest();
        request.setFirstName("Alpha");
        request.setLastName("Bravo");
        request.setEmail("delta.echo@gmail.com");
        request.setPhoneNumber("07701234568");
        request.setDateOfBirth(LocalDate.of(1995, 5, 1));
        request.setGender(Gender.FEMALE);
        request.setStatus(StudentStatus.ENROLLED);
        request.setGradeLevel(GradeLevel.GRADE_3);
        request.setAddress("North 5th street, CA");
        return request;
    }
}
