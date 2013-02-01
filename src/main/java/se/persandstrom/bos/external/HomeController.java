package se.persandstrom.bos.external;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.exception.InvalidDataException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    ApiController apiController;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        List<Entry> latest = apiController.getLatest(locale, model);
        model.addAttribute("latestList", latest);

        return "home";
    }

    @RequestMapping(value = "/text/{id}", method = RequestMethod.GET)
    String textGet(Locale locale, Model model, @PathVariable String id) {
        logger.info("textGet");

        Entry entry = apiController.get(locale, model, id);

        model.addAttribute("entry", entry);
        return "text";
    }

    @RequestMapping(value = "/text", method = RequestMethod.POST)
    String textPost(Locale locale, Model model, @ModelAttribute("entry") Entry entry) throws InvalidDataException {
        logger.info("textPost");

        entry = apiController.post(locale, model, entry);

        return "redirect:/text/" + entry.getKey();
    }

}
