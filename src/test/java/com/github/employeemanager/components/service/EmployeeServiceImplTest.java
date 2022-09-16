package com.github.employeemanager.components.service;

import com.github.employeemanager.components.domain.Employee;
import com.github.employeemanager.components.dto.EmployeeBaseDto;
import com.github.employeemanager.components.dto.request.EmployeeRequestDto;
import com.github.employeemanager.components.dto.response.EmployeeResponseDto;
import com.github.employeemanager.components.exception.EmployeeNotFoundException;
import com.github.employeemanager.components.mapper.EmployeeMapper;
import com.github.employeemanager.components.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee;

    private EmployeeRequestDto request;

    private EmployeeResponseDto response;

    private Employee getEmployee() {

        return Employee.builder()
                .id(1L)
                .name("Lucas Samweyes")
                .email("lsamweyes0@fda.com")
                .jobTitle("Senior Full-Stack Developer")
                .phone("300-245-4509")
                .imageUrl("https://www.bootdey.com/app/webroot/img/Content/avatar/avatar7.png")
                .employeeCode("2f0d3f6a-136c-49f5-8d4b-16ede1578873")
                .build();
    }


    private EmployeeRequestDto getRequest() {

        return EmployeeRequestDto.builder()
                .id(1L)
                .name("Lucas Samweyes")
                .email("lsamweyes0@fda.com")
                .jobTitle("Senior Full-Stack Developer")
                .phone("300-245-4509")
                .imageUrl("https://www.bootdey.com/app/webroot/img/Content/avatar/avatar7.png")
                .employeeCode("2f0d3f6a-136c-49f5-8d4b-16ede1578873")
                .build();
    }


    @AfterEach
    public void cleanUp() {
        repository.deleteAll();
    }

    @Test
    public void shouldReturnListAllEmployeeResponseDtoWhenEmployeesAreFounded() {

        //given
        List<Employee> list = getListEmployee();
//        when(repository.findAll()).thenReturn(list);
        given(repository.findAll()).willReturn(list);

        //when
        List<EmployeeResponseDto> result = service.getAllEmployees();

        //then
//        System.out.println(result);
        assertThat(result.get(2).getName()).isEqualTo("James Cowderoy")
                .isInstanceOf(String.class);
        assertThat(result.size()).isEqualTo(5);
        assertThat(result).isInstanceOf(List.class);
        assertThat(result.get(1)).isInstanceOf(EmployeeResponseDto.class);
    }

    @Test
    public void shouldReturnEmployeeResponseDtoByIdWhenEmployeeIsFounded() {

        //given
        request = getRequest();
        employee = getEmployee();
        given(repository.findById(request.getId())).willReturn(Optional.of(employee));

        //when
        EmployeeResponseDto byId = service.findEmployeeById(request.getId());

        //then
        assertThat(byId.getName()).isEqualTo("Lucas Samweyes");
        assertThat(byId.getEmail()).isEqualTo("lsamweyes0@fda.com");
        assertThat(byId).isInstanceOf(EmployeeResponseDto.class);
    }

    @Test
    public void shouldBeThrownEmployeeNotFoundExceptionWhenEmployeeIsNotFounded() {

        //given
        request = getRequest();
        given(repository.findById(request.getId())).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy( () -> service.findEmployeeById(request.getId()))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("No employee with " + request.getId() + " id");
    }

    @Test
    public void shouldReturnEmployeeResponseDtoWhenEmployeeIs() {

        //given
        employee = getEmployee();

        //when
        response = EmployeeMapper.toResponse(employee);

        //then

        assertThat(response).isInstanceOf(EmployeeResponseDto.class);
        assertThat(response.getJobTitle()).isEqualTo("Senior Full-Stack Developer");
    }


    private List<Employee> getListEmployee() {

        Employee lucasSamweyes =
                new Employee(
                        1L,
                        "Lucas Samweyes",
                        "lsamweyes0@fda.com",
                        "Senior Full-Stack Developer",
                        "300-245-4509",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar7.png",
                        "2f0d3f6a-136c-49f5-8d4b-16ede1578873"
                );

        Employee oliverSkerratt =
                new Employee(
                        2L,
                        "Oliver Skerratt",
                        "oskerratt@fda.com",
                        "Senior Java Developer",
                        "363-977-1893",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar5.png",
                        "a346cce8-c33f-427b-884d-8a4d113bd164"
                );

        Employee jamesCowderoy =
                new Employee(
                        3L,
                        "James Cowderoy",
                        "jcowderoy@fda.com",
                        "Chief Executive Officer",
                        "955-985-2702",
                        "https://www.bootdey.com/img/Content/avatar/avatar1.png",
                        "bec4f978-7c05-45a4-b1c4-6bc8e457ad17"
                );

        Employee barrieRockcliffe =
                new Employee(
                        4L,
                        "Barrie Rockcliffe",
                        "brockcliffe@fda.com",
                        "Senior Front-End Developer",
                        "490-351-2899",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar6.png",
                        "22dea798-e979-4393-afd4-f76765893c4e"
                );

        Employee krystleFeasley =
                new Employee(
                        5L,
                        "Krystle Feasley",
                        "kfeasley@fda.com",
                        "Secretary",
                        "473-436-5477",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar3.png",
                        "0f82f41d-aff7-40a8-8842-0b5c46f57763"
                );

        List<Employee> list = new ArrayList<>();

        list.add(lucasSamweyes);
        list.add(oliverSkerratt);
        list.add(jamesCowderoy);
        list.add(barrieRockcliffe);
        list.add(krystleFeasley);

        return list;
    }

    private List<EmployeeResponseDto> getListEmployeeResponseDto() {

        EmployeeResponseDto lucasSamweyes =
                new EmployeeResponseDto(
                        1L,
                        "Lucas Samweyes",
                        "lsamweyes0@fda.com",
                        "Senior Full-Stack Developer",
                        "300-245-4509",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar7.png",
                        "2f0d3f6a-136c-49f5-8d4b-16ede1578873"
                );

        EmployeeResponseDto oliverSkerratt =
                new EmployeeResponseDto(
                        2L,
                        "Oliver Skerratt",
                        "oskerratt@fda.com",
                        "Senior Java Developer",
                        "363-977-1893",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar5.png",
                        "a346cce8-c33f-427b-884d-8a4d113bd164"
                );

        EmployeeResponseDto jamesCowderoy =
                new EmployeeResponseDto(
                        3L,
                        "James Cowderoy",
                        "jcowderoy@fda.com",
                        "Chief Executive Officer",
                        "955-985-2702",
                        "https://www.bootdey.com/img/Content/avatar/avatar1.png",
                        "bec4f978-7c05-45a4-b1c4-6bc8e457ad17"
                );

        EmployeeResponseDto barrieRockcliffe =
                new EmployeeResponseDto(
                        4L,
                        "Barrie Rockcliffe",
                        "brockcliffe@fda.com",
                        "Senior Front-End Developer",
                        "490-351-2899",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar6.png",
                        "22dea798-e979-4393-afd4-f76765893c4e"
                );

        EmployeeResponseDto krystleFeasley =
                new EmployeeResponseDto(
                        5L,
                        "Krystle Feasley",
                        "kfeasley@fda.com",
                        "Secretary",
                        "473-436-5477",
                        "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar3.png",
                        "0f82f41d-aff7-40a8-8842-0b5c46f57763"
                );

        List<EmployeeResponseDto> list = new ArrayList<>();

        list.add(lucasSamweyes);
        list.add(oliverSkerratt);
        list.add(jamesCowderoy);
        list.add(barrieRockcliffe);
        list.add(krystleFeasley);

        return list;
    }

}