package com.ejemplo.microservicio_peliculas.config;
// 📦 Define el paquete del archivo, organiza las clases por funcionalidad

// 📚 IMPORTACIONES NECESARIAS PARA SPRING SECURITY

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
// Provee una forma limpia de usar configuraciones por defecto

// 🔐 CLASE DE CONFIGURACIÓN DE SEGURIDAD
@Configuration // 🛠️ Indica que esta clase contiene configuraciones para el contexto de Spring
@EnableWebSecurity // 🔒 Activa la seguridad web personalizada
public class SecurityConfig {

    @Bean // 🔁 Este método retorna un objeto que será gestionado por Spring como un
          // "bean"
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 🧱 Método que construye la cadena de filtros de seguridad para la aplicación

        return http
                .csrf(csrf -> csrf.disable())
                // ❌ Desactiva la protección CSRF (Cross-Site Request Forgery)
                // Esto se hace normalmente en APIs REST porque no manejan formularios web

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                // ✅ Indica que cualquier solicitud HTTP debe estar autenticada
                // Es decir, no se permiten accesos públicos sin login

                .httpBasic(Customizer.withDefaults())
                // 🔑 Habilita autenticación básica (usuario/contraseña vía cabecera
                // Authorization)
                // Customizer.withDefaults() aplica la configuración por defecto de httpBasic

                .build();
        // 🧱 Construye y retorna la cadena de seguridad que se usará en la app
    }
}
