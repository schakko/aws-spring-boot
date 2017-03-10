package de.schakko.samples.aws.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/counter")
public class CounterController {
	@Autowired(required = false)
	private LongRunningWorker longRunningWorker;

	@Autowired
	private PausableWorker pausableWorker;

	@Autowired
	private CounterRepository counterRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView r = new ModelAndView("counter/index");

		r.addObject("hasLongRunningWorker", longRunningWorker != null);
		r.addObject("longRunningWorker", longRunningWorker);
		r.addObject("pausableWorker", pausableWorker);
		r.addObject("workers", counterRepository.findAll());

		return r;
	}

	@RequestMapping(path = "start", method = RequestMethod.POST)
	public ModelAndView startWorker() {
		pausableWorker.setPaused(false);

		return index();
	}

	@RequestMapping(path = "stop", method = RequestMethod.POST)
	public ModelAndView stopWorker() {
		pausableWorker.setPaused(true);

		return index();
	}
}
