/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.urlshortener;

import java.net.MalformedURLException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sikhu
 */

/*
@Controller
public class mainController {

    @Autowired
    public Repo repo;

    @Autowired
    public RegRepo regRepo;

    //POST URL TO DATABASE METHOD
    @RequestMapping(value = "/posturl", method = RequestMethod.POST)
    @ResponseBody
    public String POSTM(@RequestBody urlShortenerMethods us) {

        urlShortenerMethods plz = new urlShortenerMethods(); //generate short URL using empty Constructor.
        urlShortenerMethods longURL = new urlShortenerMethods(us.getlongURL(), plz.getshortenedURL()); //constructor to save into Database 
        repo.save(longURL);

        return plz.getshortenedURL();
    }

    //GET URL FROM DATABASE METHOD
    @ResponseBody
    @RequestMapping(value = "/geturl", method = RequestMethod.GET)
    public String GETM(@RequestBody urlShortenerMethods us) throws MalformedURLException {
        Optional<urlShortenerMethods> data = repo.findUrlByshortenedURL(us.getshortenedURL());

        String getURL = null;

        if (data.isPresent()) {
            us = data.get(); //get data
            getURL = us.getlongURL(); //get url as string from data.
        }
        return getURL; //getData; 
    }

    //DELETE URL IN DATABASE METHOD
    @ResponseBody
    @RequestMapping(value = "/deleteurl", method = RequestMethod.DELETE)
    public Integer DELETEM(@RequestBody urlShortenerMethods us) throws MalformedURLException {
        Optional<urlShortenerMethods> data = repo.findUrlByshortenedURL(us.getshortenedURL());
        long id = 0;
        Integer idd = (int) id;

        if (data.isPresent()) {
            us = data.get();
            idd = us.getId();
            repo.deleteById(idd);
        }

        return idd; //getData;
    }

    //REGISTER A NEW USER
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String RegisterUser(@RequestBody Registration reg) {

        Registration regi = new Registration(
                reg.getEmail(),
                reg.getPhone(),
                reg.getFirstname(),
                reg.getLastname(),
                reg.getOrganization(),
                reg.getPassword()
        ) {};
        regRepo.save(regi);
        return "Thank you for registering to Short URL by Vinnoce (Pty) LTD";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(@RequestBody Registration reg) {

        Optional<Registration> data = regRepo.findFirstnameByEmailAndPassword(reg.getEmail(), reg.getPassword());

        if (data.isPresent()) {

            reg = data.get();
            String firstname = reg.getFirstname();
            return reg.getUID() + "\n" + firstname;

            // return " it worked";
        } else {
            return "Invalid Login Information";
        }

    }

}

*/
