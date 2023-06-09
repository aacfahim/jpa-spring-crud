package com.aacfahim.JPACRUD.service;

import com.aacfahim.JPACRUD.model.Colleagues;
import com.aacfahim.JPACRUD.repository.ColleaguesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColleaguesService {

    @Autowired
    ColleaguesRepository colleaguesRepository;


   public List<Colleagues> getAllColleagues(){
       List<Colleagues> list = new ArrayList<Colleagues>();

       colleaguesRepository.findAll().forEach(friends -> list.add(friends));

       return list;
   }

   public Colleagues getColleagueByNum(int num){
       return colleaguesRepository.findById(num).get();
   }


   public Colleagues changeDesignation(String designation, int phone){
       Colleagues UpdatedColleague =  colleaguesRepository.findById(phone).get();
       UpdatedColleague.setDesignation(designation);
       return colleaguesRepository.save(UpdatedColleague);
   }

    public Colleagues changeSalary(int increment, int phone){
        Colleagues UpdatedColleague =  colleaguesRepository.findById(phone).get();
        int salary = UpdatedColleague.getNum();
        salary += increment;
        UpdatedColleague.setNum(salary);
        return colleaguesRepository.save(UpdatedColleague);
    }

    public void addEmployee(Colleagues colleagues){
       colleaguesRepository.save(colleagues);
        System.out.println("Added " + colleagues.getName() + " | " + colleagues.getPhone());
    }

    public void inactive(int phone){
       colleaguesRepository.deleteById(phone);
    }

    public long getColleagueCount(){

       return colleaguesRepository.count();
    }





}
