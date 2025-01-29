package util;

import entity.Island;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Runner {
    private Island island;
    private ScheduledExecutorService scheduler;

    private final Manager simulationManager;
    private final Statistic statistics;

    public Runner(Island island) {
        this.island = island;
        this.simulationManager = new Manager(island);
        this.statistics = new Statistic(island);
    }

    public void start() {
        scheduler = Executors.newScheduledThreadPool(4);

        scheduler.scheduleAtFixedRate(statistics::print, 1, 9, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(simulationManager::update, 0, 8, TimeUnit.SECONDS);

        System.out.println("Симуляция запущена!");
    }

    public void stop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            try {
                scheduler.shutdown();
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                    System.out.println("Принудительное завершение симуляции.");
                } else {
                    System.out.println("Симуляция остановлена.");
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                System.err.println("Ошибка при остановке симуляции: " + e.getMessage());
            }
        }
    }
}
