package com.sda.java9.finalproject.security.controller;

import com.sda.java9.finalproject.security.entity.AppUser;
import com.sda.java9.finalproject.security.service.AdminAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/admin/users")
@CrossOrigin("http://localhost:4200") // @PreAuthorize("hasAuthority('ROOT')")
public class AdminAreaController {

    private final AdminAreaService adminService;

    @GetMapping
    public List<AppUser> findAll(){
        return adminService.findAll();
    }

    @PutMapping
    public AppUser update(@RequestBody AppUser appUser){
        adminService.update(appUser);
        return appUser;
    }
}
