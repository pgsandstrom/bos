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
import org.springframework.web.bind.annotation.ResponseBody;

import se.persandstrom.bos.internal.api.BosApi;
import se.persandstrom.bos.internal.api.Entry;
import se.persandstrom.bos.internal.exception.InvalidDataException;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

	static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	BosApi bosApi;

	public ApiController() {
	}

	@RequestMapping(value = "text/random", method = RequestMethod.GET)
	public @ResponseBody
	Entry getRandom(Locale locale, Model model) {
		return bosApi.getRandom();
	}

	@RequestMapping(value = "text/latest", method = RequestMethod.GET)
	public @ResponseBody
	List<Entry> getLatest(Locale locale, Model model) {
		return bosApi.getLatest();
	}

	@RequestMapping(value = "text/get/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Entry get(Locale locale, Model model, @PathVariable String id) {
		return bosApi.get(id);
	}

	@RequestMapping(value = "text", method = RequestMethod.POST)
	public @ResponseBody
	Entry post(Locale locale, Model model, @ModelAttribute("entry") Entry entry) throws InvalidDataException {
		//		logger.info("post content: {}.", entry.getContent());
		//		logger.info("post time: {}.", entry.getCreatedTimeMs());

		//				@SuppressWarnings("unchecked")
		//				Enumeration<String> parameterNames = request.getParameterNames();
		//				while (parameterNames.hasMoreElements()) {
		//					String nextElement = parameterNames.nextElement();
		//					String parameter = request.getParameter(nextElement);
		//					logger.info("parameter: " + nextElement + ", " + parameter);
		//				}
		//		
		//		@SuppressWarnings("unchecked")
		//		Enumeration<String> attributeNames = request.getAttributeNames();
		//		while (attributeNames.hasMoreElements()) {
		//			String nextElement = attributeNames.nextElement();
		//			Object attribute = request.getAttribute(nextElement);
		//			logger.info("attribute: " + nextElement + ", " + attribute);
		//		}

		//		logger.info("submit: {}.", submit);

		//		entry = new Entry(123,"tja");
		//		logger.info("post key on new: {}.", entry.getKey());

		bosApi.post(entry);

		return entry;
	}

}
