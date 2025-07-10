package com.pizarro777.msvc.comentario.init;

import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.repositories.ComentarioRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
    private final ComentarioRepository comentarioRepository;

    public LoadDataBase(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (comentarioRepository.count() == 0) {
            for (int i = 0; i < 100; i++) {
                Comentario comentario = new Comentario();

                comentario.setComentario(faker.lorem().sentence());
                comentario.setIdProducto(faker.number().numberBetween(1L, 100L));
                comentario.setFechaCreacion(LocalDate.now());

                comentarioRepository.save(comentario);
                log.info("Comentario creado: {}", comentario);
            }
        }
    }
}
