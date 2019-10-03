package com.invillia;

public class Application {

    public static void main(String[] args) {
        final TeamDAO teamDAO = new TeamDAO();
//        System.out.println(teamDAO.findAll());
        teamDAO.insert(new Team("Normandia"));
        System.out.println(teamDAO.findById(5L));
    }

}
