package se.persandstrom.bos;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.persandstrom.bos.internal.api.BosApi;
import se.persandstrom.bos.internal.api.Entry;

@Controller
public class ApiController {
	
	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public @ResponseBody Entry getRandom(Locale locale, Model model) {
		return new BosApi().getRandom();
	}
	
	@RequestMapping(value = "/latest", method = RequestMethod.GET)
	public @ResponseBody List<Entry> getLatest(Locale locale, Model model) {
		return new BosApi().getLatest();
	}

}
