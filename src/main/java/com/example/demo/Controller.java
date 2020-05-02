package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@org.springframework.stereotype.Controller

public class Controller {

    private Map<Integer, Set<Integer>> followUnfollow = new HashMap<>();
    List<User> users = new ArrayList<>();

    private List<User> getAllUsers() {
        if (users.isEmpty()) {
            users = allUsers();
        }
        return users;
    }

    Integer currentUser = 1;

    @RequestMapping(value = "/follow/{id}", method = RequestMethod.POST)
    public void follow(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) {

        if (id.equals(currentUser) || id < 1 || id >getAllUsers().size()) {
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

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void follow(HttpServletRequest request, HttpServletResponse response, @RequestBody Object body) {

        try {
            response.setStatus(200);
            Gson g = new Gson();
            response.getWriter().write(g.toJson(body));
//            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/follow/{id}", method = RequestMethod.DELETE)
    public void unfollow(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") Integer id) {
        if (id.equals(currentUser) || id < 1 || id > getAllUsers().size()) {
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
    public void getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") int id) {
        if (id < 1) id = 1;
        if (id > getAllUsers().size()) id = getAllUsers().size();
        try {
            Gson g = new Gson();
            response.setStatus(200);
            response.getWriter().write(g.toJson(getAllUsers().get(id - 1)));
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody String status) {
        try {
            if (!status.isEmpty()) { mapIdToUser().get(1).status = status;}
            response.setStatus(200);
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

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public void me(HttpServletRequest request, HttpServletResponse response) {

        try {
            Cookie [] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("key") && cookie.getValue().equals("21e12ekd129d01ijdoi12odm12mdl12mlmqwd"))  {
                    Gson g = new Gson();
                    response.setStatus(200);
                    response.getWriter().write((g.toJson(getAllUsers().get(0))));
                } else {
                    response.setStatus(400);
                }
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {

        try {
            Gson g = new Gson();
            LoginPass lg = g.fromJson(body, LoginPass.class);
            if (lg.login.equals("Login") && lg.password.equals("Pass")) {
                response.setStatus(200);
                response.addCookie(new Cookie("key", "21e12ekd129d01ijdoi12odm12mdl12mlmqwd"));
            } else {
                response.setStatus(400);
            }
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // size 2 page 2
    private List<User> someUsers(int pageSize, int page) {
        if (pageSize > getAllUsers().size()) {
            pageSize = getAllUsers().size();
        }
        System.out.println(pageSize);
        System.out.println(getAllUsers().size());
        List<User> su = new ArrayList<>();
        for (int i = pageSize * (page - 1); i < pageSize * page; i++) {
            su.add(getAllUsers().get(i));
        }
        return su;
    }


    private List<User> allUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            users.add(new User(i,
                    "",
                    "Name" + i,
                    "Courier",
                    "City" + i,
                    "Russia" + i));
        }
        for (int i = 10; i < 20; i++) {
            users.add(new User(i,
                    "",
                    "Name" + i,
                    "Client",
                    "City" + i,
                    "Russia" + i));
        }
        return users;
    }

    private Map<Integer, User> mapIdToUser(){
        Map<Integer,User> mapIdToUser = new HashMap<>();
        for (User user : getAllUsers()) {
            mapIdToUser.put(user.id, user);
        }
        return mapIdToUser;
    }
}
