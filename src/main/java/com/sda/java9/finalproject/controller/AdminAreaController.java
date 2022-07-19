package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.entity.AppUser;
import com.sda.java9.finalproject.service.AdminAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/admin/users")
@CrossOrigin("http://localhost:4200") // @PreAuthorize("hasAuthority('ROOT')")
public class AdminAreaController {

    private final AdminAreaService adminService;

    @GetMapping
    public List<AppUserDTO> findAll(){
        return adminService.findAll();
    }

    @PutMapping
    public AppUserDTO update(@RequestBody AppUserDTO appUser){
        adminService.update(appUser);
        return appUser;
    }
}
