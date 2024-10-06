package dev.hlopes.runnerz.run;

import java.time.LocalDateTime;

public record Run(Integer id, String title, LocalDateTime startedOn, LocalDateTime completedOn,
                  Integer kms, Location location) {
}
