package com.uade.tpo.megagame.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El Producto ya se encuentra agregado")
public class ProductoDuplicadoException extends Exception {

}
