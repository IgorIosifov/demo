package com.example.demo;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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
// response.setStatus(201);
// response.getWriter().flush();
// response.getWriter().close();
//            } catch (IOException e) {
// e.printStackTrace();
//            }
//            equipment.setTrainingId(newEquipment.getTrainingId());
//            Integer dur = Integer.parseInt(newEquipment.getDuration()); //min
//            Integer factLoad = 0;
//            while (dur>0){
// equipment.setDistance(
//         newEquipment.getDistance());
// dur = decreaseDuration(dur);
// equipment.setDuration((dur).toString());
//
// factLoad = increaseLoad(factLoad);
//
// equipment.setActualDistance(
//         factLoad.toString()
// );
//            }
//
//            model.addAttribute("equipment",equipment);
//
//
//            return "index";
//        }

//        public Integer decreaseDuration(Integer dur)  {
//            try {
// Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
// e.printStackTrace();
//            }
//            return dur -1;
//        }
//
//        public Integer increaseLoad (Integer factLoad) {
//            try {
// Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
// e.printStackTrace();
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
    private Map<Integer, Set<Integer>> followUnfollow = new HashMap<>();

    Integer currentUser = 1;

    @RequestMapping(value = "/follow/{id}", method = RequestMethod.POST)
    public void follow(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) {
        if (id.equals(currentUser) || id < 1 || id > allUsers().size()) {
            response.setStatus(400);
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        try {
            followUnfollow.computeIfAbsent(currentUser, k -> new HashSet<>());
            followUnfollow.get(currentUser).add(id);
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/follow/{id}", method = RequestMethod.DELETE)
    public void unfollow(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) {
        if (id.equals(currentUser) || id < 1 || id > allUsers().size()) {
            response.setStatus(400);
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        try {
            followUnfollow.get(currentUser).remove(id);
            response.setStatus(200);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public void user(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") int id) {
        if (id < 1) id = 1;
        if (id > allUsers().size()) id = allUsers().size();
        try {
            Gson g = new Gson();
            response.setStatus(200);
            response.getWriter().write(g.toJson(allUsers().get(id - 1)));
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void users(HttpServletRequest request, HttpServletResponse response, @RequestParam int size, @RequestParam int page) {

        try {
            Gson g = new Gson();
            response.setStatus(200);
            response.getWriter().write(g.toJson(someUsers(size, page)));
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // size 2 page 2
    private List<User> someUsers(int pageSize, int page) {
        if (pageSize > allUsers().size()) {
            pageSize = allUsers().size();
        }
        System.out.println(pageSize);
        System.out.println(allUsers().size());
        List<User> su = new ArrayList<>();
        for (int i = pageSize * (page - 1); i < pageSize * page; i++) {
            su.add(allUsers().get(i));
        }
        return su;
    }


    private List<User> allUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            users.add(new User(i,
                    "",
                    false,
                    "Name" + i,
                    "OK" + i,
                    "City" + i,
                    "Russia" + i));
        }
        return users;
    }
}
