package com.example.demo;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller

    public class Controller {
//        private Equipment equipment;
//
//        @Autowired
//        public void setEquipment(Equipment equipment) {
//            this.equipment = equipment;
//
//        }

//        @GetMapping("/")
//        public String loginPage(Model model) {
//            model.addAttribute("equipment", equipment);
//            return "index";
//        }
//
//        @PostMapping("/")
//        public String post(@RequestBody Equipment newEquipment, Model model, HttpServletRequest request, HttpServletResponse response) {
//            try {
//                response.setStatus(201);
//                response.getWriter().flush();
//                response.getWriter().close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            equipment.setTrainingId(newEquipment.getTrainingId());
//            Integer dur = Integer.parseInt(newEquipment.getDuration()); //min
//            Integer factLoad = 0;
//            while (dur>0){
//                equipment.setDistance(
//                        newEquipment.getDistance());
//                dur = decreaseDuration(dur);
//                equipment.setDuration((dur).toString());
//
//                factLoad = increaseLoad(factLoad);
//
//                equipment.setActualDistance(
//                        factLoad.toString()
//                );
//            }
//
//            model.addAttribute("equipment",equipment);
//
//
//            return "index";
//        }

//        public Integer decreaseDuration(Integer dur)  {
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return dur -1;
//        }
//
//        public Integer increaseLoad (Integer factLoad) {
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return factLoad + 100;
//        }


//    @RequestMapping(value = "/result", method = RequestMethod.GET)
//    public String results(HttpServletRequest request, HttpServletResponse response) {
//
//        try {
//            //{ "trainingId":equipment.getTrainingId(), "distance":equipment.getActualDistance()}
//            response.getWriter().write(equipment.getTrainingId()+";"+ equipment.getActualDistance());
//            response.getWriter().flush();
//            response.getWriter().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "index";
//    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void users(HttpServletRequest request, HttpServletResponse response) {

        try {
            Gson g = new Gson();
;            response.getWriter().write(g.toJson(allUsers().toString()));
            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.setContentType("application/json");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<String> allUsers() {
            String user1 = "{" +
                    "               id: 1,\n" +
                    "               avatar: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBFC8gdBRergzgi31EKqSTlKhWUgqRwbxAtJKG9UX_iLOCVHFY&usqp=CAU',\n" +
                    "               followed: false,\n" +
                    "               fullName: 'Igor I.',\n" +
                    "               status: 'OK',\n" +
                    "               location: {city: 'Vlasikha', country: 'Russia'}\n" +
                    "           }";
            String user2 = " {" +
                    "               id: 2,\n" +
                    "               avatar: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBFC8gdBRergzgi31EKqSTlKhWUgqRwbxAtJKG9UX_iLOCVHFY&usqp=CAU',\n" +
                    "               followed: true,\n" +
                    "               fullName: 'Timur G.',\n" +
                    "               status: 'OKOK',\n" +
                    "               location: {city: 'Moscow', country: 'Russia'}\n" +
                    "           }";
            String user3 = " {" +
                    "               id: 3,\n" +
                    "               avatar: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBFC8gdBRergzgi31EKqSTlKhWUgqRwbxAtJKG9UX_iLOCVHFY&usqp=CAU',\n" +
                    "               followed: false,\n" +
                    "               fullName: 'Andrey S.',\n" +
                    "               status: 'OKOkOk',\n" +
                    "               location: {city: 'Iron', country: 'Russia'}\n" +
                    "           }";
            List<String> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);
            users.add(user3);
            return users;
    }
}
