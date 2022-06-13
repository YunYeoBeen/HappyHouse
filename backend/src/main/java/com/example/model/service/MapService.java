package com.example.model.service;

import com.example.model.dto.Location;

public interface MapService {
    Location getFocusLocation(String addr);
}
