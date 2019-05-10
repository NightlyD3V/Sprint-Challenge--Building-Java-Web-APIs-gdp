package com.sprint.sprint.controller;

import com.sprint.sprint.SprintApplication;
import com.sprint.sprint.exception.ResourceNotFoundException;
import com.sprint.sprint.gdpList;
import com.sprint.sprint.model.GDP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("/gdp")
public class GdpController {
    private static final Logger logger = LoggerFactory.getLogger(GDP.class);

    // localhost:8080/gdp/names
    @GetMapping(value = "/names")
    public ResponseEntity<?> getAllNames()
    {
        SprintApplication.ourGdpList.gdpList.sort((c1, c2) -> c1.getCountry().compareToIgnoreCase(c2.getCountry()));
        logger.info("Got all names");
        return new ResponseEntity<>(SprintApplication.ourGdpList, HttpStatus.OK);
    }

    @GetMapping(value = "/economy")
    public ResponseEntity<?> getEconomy()
    {
        SprintApplication.ourGdpList.gdpList.sort((c1, c2) -> Integer.parseInt(c1.getGdp()) - Integer.parseInt(c2.getGdp()));
        logger.info("Got the economy");
        return new ResponseEntity<>(SprintApplication.ourGdpList.gdpList, HttpStatus.OK);
    }

    @GetMapping(value = "/{countryName}")
    public ResponseEntity<?> getCountry(@PathVariable String countryName)
    {
        GDP rtnGDP = SprintApplication.ourGdpList.findGdp(g -> g.getCountry().toUpperCase().equals(countryName.toUpperCase()));
        return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
    }

    @GetMapping(value ="/country/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable int id)
    {
        GDP rtnGDP;
        if ((SprintApplication.ourGdpList.findGdp(g -> g.getId() == id)) == null)
        {
            throw new ResourceNotFoundException("Country with ID " + id + " not found");
        } else
        {
            rtnGDP = SprintApplication.ourGdpList.findGdp(g -> g.getId() == id);
        }
        return new ResponseEntity<>(rtnGDP, HttpStatus.OK);
    }

    @GetMapping(value="/country/stats/median")
    public ResponseEntity<?> getCountryMedian()
    {
        SprintApplication.ourGdpList.gdpList.sort((c1, c2) -> Integer.parseInt(c1.getGdp()) - Integer.parseInt(c2.getGdp()));
        return new ResponseEntity<>(SprintApplication.ourGdpList.gdpList.get(SprintApplication.ourGdpList.gdpList.size() / 2), HttpStatus.OK);
    }
    //Server side rendering
    @GetMapping(value ="/gdptable")
    public ModelAndView getRender()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("GDPtable");
        SprintApplication.ourGdpList.gdpList.sort((c1, c2) -> Integer.parseInt(c1.getGdp()) - Integer.parseInt(c2.getGdp()));
        mav.addObject("gdpList", SprintApplication.ourGdpList.gdpList);
        return mav;
    }

    @GetMapping(value ="/economy/greatest/{GDP}")
    public ModelAndView getRender2(@PathVariable int GDP)
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("GDPtable");
        ArrayList<GDP> rtnGDP = SprintApplication.ourGdpList.findGdps(g -> Integer.parseInt(g.getGdp()) >= GDP);
        mav.addObject("gdpList", rtnGDP);
        return mav;
    }
}
