package io.valkycodes.trs.controller;

import io.valkycodes.trs.model.po.Train;
import io.valkycodes.trs.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/home")
    public String homePage(@RequestParam(required = false) String source,
                           @RequestParam(required = false) String destination,
                           Model model)
    {
        List<Train> trains;

        if (source != null && !source.isEmpty() && destination != null && !destination.isEmpty()) {
            trains = trainService.findTrainsByRoute(source, destination);

            model.addAttribute("searchSource", source);
            model.addAttribute("searchDest", destination);
        } else {
            trains = trainService.getAllTrain();
        }

        model.addAttribute("trains", trains);

        return "home";
    }
}
