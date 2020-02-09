package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@org.springframework.stereotype.Controller

    public class Controller {
        private Equipment equipment;

        @Autowired
        public void setEquipment(Equipment equipment) {
            this.equipment = equipment;
        }
//        @GetMapping("/trainee")
//        public Equipment get() {
//            equipment.setLoad("echo");
//            equipment.setDuration("echo");
//            return equipment;
//        }

        @GetMapping("/")
        public String loginPage(Model model) {
            model.addAttribute("equipment", equipment);
            return "index";
        }

        @PostMapping("/")
        public String post(@RequestBody Equipment newEquipment, Model model) {
            equipment.setTrainingId(newEquipment.getTrainingId());
            Integer dur = Integer.parseInt(newEquipment.getDuration()); //min
            Integer factLoad = 0;
            while (dur>0){
                equipment.setDistance(
                        newEquipment.getDistance());
                dur = decreaseDuration(dur);
                equipment.setDuration((dur).toString());

                factLoad = increaseLoad(factLoad);

                equipment.setActualDistance(
                        factLoad.toString()
                );
            }

            model.addAttribute("equipment",equipment);
            return "index";
        }

        public Integer decreaseDuration(Integer dur)  {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return dur -1;
        }

        public Integer increaseLoad (Integer factLoad) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return factLoad + 100;
        }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String results(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.getWriter().write(equipment.getTrainingId()+";"+equipment.getActualDistance());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}

//    @RequestMapping(value = "/dosomething", method = RequestMethod.GET)
//    public ModelAndView dosomething(HttpServletRequest request, HttpServletResponse response)  throws IOException {
//        // setup your Cookie here
//        response.setCookie(cookie)
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("redirect:/other-page");
//
//        return mav;
