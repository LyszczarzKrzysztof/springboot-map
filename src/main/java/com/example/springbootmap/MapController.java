package com.example.springbootmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MapController {

//    @RequestMapping(method = RequestMethod.GET)
//    public String getMap(Model model, @RequestParam String x, @RequestParam String y){
//        model.addAttribute("x", x);
//        model.addAttribute("y", y);
//        return "map";
//    }

    private FileParser fileParser;

    @Autowired
    public MapController(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @GetMapping
    public String getMap(Model model) throws IOException {

//        List<Point> pointList = new ArrayList<>();
//
//        pointList.add(new Point( 51.76, 19.45, "Wykryte przypadki: 1"));
//        pointList.add(new Point( 50.25, 19.01, "Wykryte przypadki: 2"));

//        model.addAttribute("pointList", pointList);

        model.addAttribute("points", fileParser.getData());
        return "map";
    }
}
