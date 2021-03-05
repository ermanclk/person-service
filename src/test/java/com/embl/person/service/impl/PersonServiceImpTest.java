package com.embl.person.service.impl;

import com.embl.person.entity.Person;
import com.embl.person.exception.NotFoundException;
import com.embl.person.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImpTest {

    private PersonServiceImp classToTest;
    private PersonRepository personRepository;
    private Person dummyObject;
    private final Long TEST_ID=1L;
    private final String TEST_NAME="dummyName";
    private final String TEST_LASTNAME ="dummyJob";
    private final Integer TEST_AGE=18;
    private PageRequest pageable;

    @Before
    public void setUp() {
        personRepository = mock(PersonRepository.class);
        dummyObject = mock(Person.class);
        pageable=mock(PageRequest.class);
        when(personRepository.save(dummyObject)).thenReturn(dummyObject);

        classToTest= spy(new PersonServiceImp(personRepository));
    }

    @Test
    public void givenDummyObjectWhenCreateThenCallRepository(){
        //Act
        classToTest.create(dummyObject);
        //Assert
        verify(personRepository,times(1)).save(dummyObject);
    }

    @Test
    public void givenDummyObjectWhenUpdateThenCallRepository(){
        //Arrange
        when(personRepository.save(dummyObject)).thenReturn(any());
        //Act
        classToTest.create(dummyObject);
        //Assert
        verify(personRepository,times(1)).save(dummyObject);
    }

    @Test(expected=IllegalArgumentException.class)
    public void givenIdNullWhenUpdateThenThrowIllegalArgumentException(){
        //Arrange
        when(dummyObject.getId()).thenReturn(null);
        //Act
        classToTest.update(dummyObject);
    }

    @Test(expected= NotFoundException.class)
    public void givenElementNotFoundWhenUpdateThenThrowNotFoundException(){
        //Arrange
        when(dummyObject.getId()).thenReturn(TEST_ID);
        when(personRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        //Act
        classToTest.update(dummyObject);
    }

    @Test
    public void givenElementExistWhenFindByIdThenReturnElement(){
        //Arrange
        when(personRepository.findById(TEST_ID)).thenReturn(Optional.of(dummyObject));
        //Act
        Person person= classToTest.findById(TEST_ID);
        //assert
        assertThat(person,equalTo(dummyObject));
    }

    @Test(expected= NotFoundException.class)
    public void givenElementNotExistWhenFindByIdThenThrowNotFoundException(){
        //Arrange
        when(personRepository.findById(TEST_ID)).thenReturn(Optional.empty());
        //Act
        classToTest.findById(TEST_ID);
    }

    @Test
    public void givenNameWhenFindAllThenReturnRepositoryResult(){
        //Arrange
        Page dummyPage= mock(Page.class);
        when(personRepository.findAllByNameAndAgeAndJob(anyString(),anyString(),anyInt(),any())).thenReturn(dummyPage);
        //Act
        Page<Person> actual= classToTest.findAll(Optional.of(TEST_NAME),Optional.of(TEST_LASTNAME),Optional.of(TEST_AGE),pageable);
        //Assert
        assertThat(actual,equalTo(dummyPage));
    }

}