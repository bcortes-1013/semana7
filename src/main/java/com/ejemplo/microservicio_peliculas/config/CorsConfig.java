package com.ejemplo.microservicio_peliculas.config;
// 📦 Define el paquete de la clase, sirve para agrupar todas las configuraciones

// 📚 IMPORTACIONES NECESARIAS PARA CONFIGURAR CORS

import org.springframework.context.annotation.Bean;
// Permite declarar un método como Bean gestionado por Spring

import org.springframework.context.annotation.Configuration;
// Indica que esta clase contiene configuraciones para el contexto de Spring

import org.springframework.web.servlet.config.annotation.CorsRegistry;
// Permite registrar configuraciones específicas de CORS para rutas

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// Interfaz para configurar el comportamiento del MVC en Spring (como CORS)

// 🌐 CONFIGURACIÓN GLOBAL DE CORS
@Configuration // 🛠️ Marca esta clase como de configuración, Spring la carga al arrancar
public class CorsConfig {

    @Bean // 🔁 Registra este configurador como un Bean que Spring puede utilizar
    public WebMvcConfigurer corsConfigurer() {
        // 🔧 Devuelve una implementación de WebMvcConfigurer
        // que va a personalizar las reglas de CORS

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 📌 Aquí definimos las reglas de CORS

                registry.addMapping("/**")
                        // 🔁 Aplica las reglas a **todas las rutas** del backend (endpoint general)

                        .allowedOrigins("*")
                        // 🌍 Permite que **cualquier origen** (dominio externo) pueda consumir la API
                        // ✅ Útil para desarrollo y pruebas con frontends externos (React, Angular,
                        // etc.)

                        // Puedes restringirlo a orígenes específicos:
                        // .allowedOrigins("https://midominio.com", "http://localhost:3000")

                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // 📡 Métodos HTTP permitidos (los más comunes en REST APIs)

                        .allowedHeaders("*");
                // 📬 Permite todos los tipos de encabezados (como Authorization, Content-Type,
                // etc.)
            }
        };
    }
}
