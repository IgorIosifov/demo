package com.example.demo;

import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/v1/items")
    public class Controller {
        Equipment equipment = new Equipment("no data", "no data");

        @GetMapping("/trainee")
        public Equipment get() {
            equipment.setId("echo"+equipment.getId());
            equipment.setContent("echo"+equipment.getContent());
            return equipment;
        }

        @PostMapping("/trainee")
        public Equipment post(@RequestBody Equipment newEquipment) {
            equipment.setId(newEquipment.getId());
            equipment.setContent(newEquipment.getContent());
            return equipment;
        }
        @GetMapping("/")
        public String loginPage() {
            return "index";
        }


}
