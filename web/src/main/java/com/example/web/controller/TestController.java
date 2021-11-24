package com.example.web.controller;

import com.example.web.entity.BaseResult;
import com.example.web.entity.WebParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.util.Objects;
import java.util.Set;

@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public BaseResult test(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        if (Objects.isNull(name)) {
            throw new Exception("name is empty");
        }
        return new BaseResult(200, "success", name);
    }

    @RequestMapping(value = "/valid")
    public BaseResult validate(@RequestBody WebParam webParam) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<WebParam>> validates = validator.validate(webParam);
        for (ConstraintViolation<WebParam> v : validates) {
            log.error(v.getMessage());
            return BaseResult.fail(v.getMessage());
        }

        return BaseResult.success(null);
    }

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}
