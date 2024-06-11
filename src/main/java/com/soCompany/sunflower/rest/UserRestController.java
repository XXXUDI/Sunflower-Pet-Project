package com.soCompany.sunflower.rest;

import com.soCompany.sunflower.dto.UserEditDto;
import com.soCompany.sunflower.dto.UserReadDto;
import com.soCompany.sunflower.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserReadDto findUserById(@RequestParam int id) {
        return userService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody UserEditDto dto) {
        UserReadDto result = userService.save(dto);
        return "redirect:/" + result.getId();
    }

    @PutMapping("/{id}")
    public UserReadDto update(@RequestBody UserEditDto dto,
                                  @PathVariable Integer id) {
        return userService.update(id, dto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable Integer id) {
        if(!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
