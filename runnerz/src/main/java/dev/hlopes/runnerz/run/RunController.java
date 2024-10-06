package dev.hlopes.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> getRuns() {
        return runRepository.findAll();
    }

    @GetMapping("{id}")
    Run getRun(@PathVariable Integer id) {
        var optionalRun = runRepository.findById(id);

        if (optionalRun.isEmpty()) {
            throw new RunNotFoundException();
        }

        return optionalRun.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Run create(@Valid @RequestBody Run run) {
        return runRepository.create(run);
    }

    @PutMapping("/{id}")
    Run update(@PathVariable Integer id, @Valid @RequestBody Run run) {
        return runRepository.update(id, run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }
}
