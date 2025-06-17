package com.tfg.DyDM.task;

import com.tfg.DyDM.model.Sala;
import com.tfg.DyDM.service.ArenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ArenaCleanupTask {

    private final ArenaService arenaService;

    @Scheduled(fixedRate = 60000) // cada 60 segundos
    public void limpiarSalasVacias() {
        Map<Long, Sala> mapa = arenaService.getSalasMap();

        Iterator<Map.Entry<Long, Sala>> it = mapa.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Long, Sala> entry = it.next();
            Sala sala = entry.getValue();

            if (sala.getHost() == null && sala.getJugador2() == null) {
                it.remove();
                System.out.println("🧹 Sala eliminada automáticamente: ID " + sala.getId());
            }
        }
    }
}
