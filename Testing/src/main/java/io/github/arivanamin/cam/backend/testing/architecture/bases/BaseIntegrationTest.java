package io.github.arivanamin.cam.backend.testing.architecture.bases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SuppressWarnings ({ "MarkerInterface", "NewClassNamingConvention" })
@ExtendWith (SpringExtension.class)
public interface BaseIntegrationTest extends BaseUnitTest {

}
