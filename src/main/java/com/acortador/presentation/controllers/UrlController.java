package com.acortador.presentation.controllers;

import com.acortador.application.commands.AcortarURLCommand;
import com.acortador.application.commands.AcortarURLCommandHandler;
import com.acortador.application.commands.ActualizarClicsCommand;
import com.acortador.application.commands.ActualizarClicsCommandHandler;
import com.acortador.application.queries.ObtenerURLPorCodigoQuery;
import com.acortador.application.queries.ObtenerURLPorCodigoQueryHandler;
import com.acortador.domain.entities.Url;
import com.acortador.presentation.dtos.AcortarUrlRequest;
import com.acortador.presentation.dtos.UrlStatsResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    private final AcortarURLCommandHandler acortarHandler;
    private final ObtenerURLPorCodigoQueryHandler obtenerHandler;
    private final ActualizarClicsCommandHandler actualizarClicsHandler;

    public UrlController(
            AcortarURLCommandHandler acortarHandler,
            ObtenerURLPorCodigoQueryHandler obtenerHandler,ActualizarClicsCommandHandler actualizarClicsHandler

    ) {
        this.acortarHandler = acortarHandler;
        this.obtenerHandler = obtenerHandler;
        this.actualizarClicsHandler = actualizarClicsHandler;
    }

    @PostMapping("/acortar")
    public ResponseEntity<?> acortar(@Valid @RequestBody AcortarUrlRequest request) {
        try {
            AcortarURLCommand command = new AcortarURLCommand(
                    request.getUrlOriginal(),
                    request.getCodigoPersonalizado()
            );

            String codigoGenerado = acortarHandler.handle(command);
            return ResponseEntity.ok("URL acortada: https://acortar.io/" + codigoGenerado);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error inesperado");
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtener(@PathVariable String codigo) {
        try {
            ObtenerURLPorCodigoQuery query = new ObtenerURLPorCodigoQuery(codigo);
            Optional<Url> resultado = obtenerHandler.handle(query);

            if (resultado.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            actualizarClicsHandler.handle(new ActualizarClicsCommand(codigo));
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", resultado.get().getUrlOriginal())
                    .build();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error inesperado");
        }
    }
    @GetMapping("/{codigo}/stats")
    public ResponseEntity<?> obtenerEstadisticas(@PathVariable String codigo) {
        try {
            ObtenerURLPorCodigoQuery query = new ObtenerURLPorCodigoQuery(codigo);
            Optional<Url> resultado = obtenerHandler.handle(query);

            if (resultado.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Url url = resultado.get();
            UrlStatsResponse response = new UrlStatsResponse(
                    url.getCodigoCorto().getValor(),
                    url.getUrlOriginal(),
                    url.getClics(),
                    url.getFechaCreacion()
            );

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error inesperado");
        }
    }

}
