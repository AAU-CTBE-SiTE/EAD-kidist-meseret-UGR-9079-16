package com.site.actor_management.controller;

import com.site.actor_management.model.Actor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    List<Actor> actors = new ArrayList<>();

    public ActorController() {

        actors.add(new Actor(1, "John Doe", "The Great Adventure"));
        actors.add(new Actor(2, "Jane Smith", "Mystery Island"));

    }

    // CRUD - READ
    @GetMapping("/actor")
    public List<Actor> getActorDetails() {
        return actors;
    }

    // CRUD - READ
    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable int id) {

        for (Actor actor : actors) {

            if (actor.getId() == id) {
                return actor;
            }

        }

        return null;
    }

    // CRUD - CREATE
    @PostMapping("/actor")
    public Actor addActor(@RequestBody Actor actor) {

        actors.add(actor);

        return actor;
    }

    // CRUD - UPDATE
    @PutMapping("/actor/{id}")
    public Actor updateActor(@PathVariable int id,
                             @RequestBody Actor updatedActor) {

        for (int i = 0; i < actors.size(); i++) {

            if (actors.get(i).getId() == id) {
                actors.set(i, updatedActor);

                return updatedActor;
            }
        }

        return null;
    }


}
