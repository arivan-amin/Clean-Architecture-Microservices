package io.github.arivanamin.scm.backend.common.storage.audit;

import io.github.arivanamin.scm.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@Slf4j
class JpaAuditEventStorageTest implements BaseUnitTest {

    @Mock
    AuditEventRepository repository;

    @InjectMocks
    JpaAuditEventStorage storage;
    
    @Test
    void findAllShouldReturnAllEvents () {
        // given

        // when

        // then
    }
}
