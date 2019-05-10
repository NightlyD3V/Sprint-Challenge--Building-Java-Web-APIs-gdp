package com.sprint.sprint.controller;

import com.sprint.sprint.SprintApplication;
import com.sprint.sprint.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/gdp")
public class GdpController {
    private static final Logger logger = LoggerFactory.getLogger(GDP.class);

    // localhost:8080/gdp/names
    @GetMapping(value = "/names")
    public ResponseEntity<?> getAllNames()
    {
        logger.info("Got all names");
        return new ResponseEntity<>(SprintApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    @GetMapping(value = "/economy")
    public ResponseEntity<?> getEconomy()
    {
        SprintApplication.ourGdpList.gdpList.sort((c1, c2) -> Integer.parseInt(c1.getGdp()) - Integer.parseInt(c2.getGdp()));
        logger.info("Got the economy");
        return new ResponseEntity<>(SprintApplication.ourGdpList.gdpList.toString(), HttpStatus.OK);
    }
}
