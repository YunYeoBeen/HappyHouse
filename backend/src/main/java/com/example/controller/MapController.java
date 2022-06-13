package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.dto.Location;
import com.example.model.service.MapService;


@RestController
@CrossOrigin
@RequestMapping("/map")
public class MapController {
    private MapService mapService;

    @Autowired
    public void setMapService(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/loc/{addr}")
    public Location getLocByAddr(@PathVariable String addr) {
        return mapService.getFocusLocation(addr);
    }
}
