package se.persandstrom.bos.external;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.persandstrom.bos.internal.api.BosApi;
import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.exception.DataNotFoundException;
import se.persandstrom.bos.internal.exception.InvalidDataException;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    BosApi bosApi;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public
    @ResponseBody
    String test() throws IOException {
        return "you should not be here";
    }

    @RequestMapping(value = "text/latest", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Entry> getLatest(Locale locale, Model model) {
        return bosApi.getLatest();
    }

    @RequestMapping(value = "text/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Entry get(Locale locale, Model model, @PathVariable String id) {
        return bosApi.get(id);
    }

    @RequestMapping(value = "text", method = RequestMethod.POST)
    public
    @ResponseBody
    Entry post(Locale locale, Model model, @ModelAttribute("entry") Entry entry) throws InvalidDataException {
        bosApi.post(entry);
        return entry;
    }

    @RequestMapping(value = "text/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Entry delete(Locale locale, Model model, @PathVariable String id) throws InvalidDataException,
            DataNotFoundException {
        Entry entry = new Entry(null, id);
        bosApi.delete(entry);
        return entry;
    }

}
