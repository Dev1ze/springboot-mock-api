package com.example.firstSub.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Эмулятор системы выдачи микрокредитов",
                description = "Эмулируется работа внешней системы для проведения НТ сервиса микрозаймов",
                contact = @Contact(
                        name = "Kleymenov Artem Andreevich",
                        email = "@devize1337",
                        url = "https://github.com/Dev1ze"
                )
        )
)
public class SwaggerConfig {
}
