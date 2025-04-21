package io.github.arivanamin.scm.backend.sso.application.endpoints;

import io.github.arivanamin.scm.backend.sso.application.request.CreateClientRequest;
import io.github.arivanamin.scm.backend.sso.application.response.ClientResponse;
import io.github.arivanamin.scm.backend.sso.application.response.CreateClientResponse;
import io.github.arivanamin.scm.backend.sso.core.command.CreateSsoClientCommand;
import io.github.arivanamin.scm.backend.sso.core.query.ReadClientByIdQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static io.github.arivanamin.scm.backend.sso.application.config.SsoApiURLs.CREATE_CLIENT_URL;
import static io.github.arivanamin.scm.backend.sso.application.config.SsoApiURLs.GET_CLIENT_BY_ID_URL;

@Tag (name = "SSO Controller")
@RestController
@RequiredArgsConstructor
@Slf4j
class SsoController {

    private final ReadClientByIdQuery readByIdQuery;
    private final CreateSsoClientCommand createCommand;

    @GetMapping (GET_CLIENT_BY_ID_URL)
    @Operation (summary = "Get a single SSO Client by id")
    @ResponseStatus (HttpStatus.OK)
    public ClientResponse getClientById (@PathVariable UUID id) {
        return ClientResponse.of(readByIdQuery.execute(id));
    }

    @PostMapping (CREATE_CLIENT_URL)
    @Operation (summary = "Creates an SSO Client")
    @ResponseStatus (HttpStatus.CREATED)
    public CreateClientResponse createClient (@RequestBody @Valid CreateClientRequest request) {
        UUID createdClientId = createCommand.execute(request.toEntity());
        return CreateClientResponse.of(createdClientId);
    }
}
