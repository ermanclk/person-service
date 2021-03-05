package com.embl.person.controller;


import com.embl.person.dto.PersonDto;
import com.embl.person.entity.Person;
import com.embl.person.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private PersonService personService;
    private ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    /**
     * creates person
     * @param personDto person object to created in request body
     * @return
     */
    @PostMapping
    public PersonDto create(@RequestBody @Valid PersonDto personDto) {

        return toDTO(personService.create(toEntity(personDto)),PersonDto.class);
    }

    /**
     * updates Person
     * @param personDto person object to updated in request body
     * @return
     */

    @PutMapping
    public PersonDto update(@RequestBody @Valid PersonDto personDto) {
        return toDTO(personService.update(toEntity(personDto)),PersonDto.class);
    }

    /**
     * find full object details by id
     * @param id id of person object
     * @return  Person Object
     */
    @GetMapping(path = "/{id}")
    public PersonDto findById(@PathVariable String id) {
        return toDTO(personService.findById(Long.parseLong(id)), PersonDto.class);
    }

    /**
     * delete given object by Id
     * @param id id of person object
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable String id) {
         personService.deleteById(Long.parseLong(id));;
    }

    /**
     * find person list, if parameters (firstName, lastName, age) exist,then filter by them.
     * If there is not any filter parameter bring them all by pagination
     *
     * @param firstName     optional first name parameter for filtering by name
     * @param lastName      optional last name parameter for filtering by last name
     * @param age           optional age parameter for filtering by age
     * @param pageNumber    page number
     * @param size          page size
     * @return Person Object list with paging info
     */
    @GetMapping(path = "/findAll")
    public Map<String, Object> findAll(@RequestParam Optional<String> firstName,
                                       @RequestParam Optional<String> lastName,
                                       @RequestParam Optional<Integer> age,
                                       @RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "10") int size) {

        Page<Person> page = personService.findAll(firstName,lastName,age,PageRequest.of(pageNumber, size));

        List<PersonDto> result= page.getContent().stream()
                .map(entity-> toDTO(entity, PersonDto.class))
                .collect(Collectors.toList());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("persons", result);
        responseMap.put("pageNumber", page.getNumber());
        responseMap.put("totalItems", page.getTotalElements());
        responseMap.put("totalPages", page.getTotalPages());

        return responseMap;
    }

    /**
     * converts given entity object into dto
     * @param person
     * @param dtoClassType
     * @param <T>
     * @return PersonDTO Object
     */
    private <T> T toDTO(Person person, Class<T> dtoClassType) {
        return modelMapper.map(person, dtoClassType);
    }

    /**
     * converts given dto object into entity
     * @param personDto
     * @return Person Entity Object
     */
    private Person toEntity(PersonDto personDto){
        return modelMapper.map(personDto, Person.class);
    }


}