package dev.hlopes.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepository {

    private final List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                   .filter(run -> Objects.equals(run.id(), id))
                   .findFirst();
    }

    Run create(Run run) {
        var lastRun = findAll().stream().reduce((first, second) -> second).orElse(null);

        var newRun =
            new Run(lastRun.id() + 1,
                    run.title(),
                    run.startedOn(),
                    run.completedOn(),
                    run.kms(),
                    run.location());

        runs.add(newRun);

        return newRun;
    }

    Run update(Integer id, Run run) {
        var optionalRun = findById(id);

        if (optionalRun.isEmpty()) {
            return null;
        }

        var newRun =
            new Run(optionalRun.get().id(),
                    run.title(),
                    run.startedOn(),
                    run.completedOn(),
                    run.kms(),
                    run.location());

        runs.set(runs.indexOf(optionalRun.get()), newRun);

        return newRun;
    }

    void delete(Integer id) {
        runs.removeIf(run -> id.equals(run.id()));
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1,
                         "Monday",
                         LocalDateTime.now(),
                         LocalDateTime.now().plusHours(1),
                         3,
                         Location.INDOOR));

        runs.add(new Run(2,
                         "Wednesday",
                         LocalDateTime.now(),
                         LocalDateTime.now().plusHours(2),
                         5,
                         Location.OUTDOOR));
    }
}
