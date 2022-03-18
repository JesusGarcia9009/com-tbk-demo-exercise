package com.tbk.exercise.restcontroller;

import static com.tbk.exercise.utils.ConstantUtil.LOG_END;
import static com.tbk.exercise.utils.ConstantUtil.LOG_START;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbk.exercise.queue.QueueService;
import com.tbk.exercise.queue.Ticket;
import com.tbk.exercise.services.UserService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/queue-user-services")
@Api(tags = { "Queue User Services" })
public class UserQueueControllerImpl implements UserQueueController {

	/**
	 * Global variables
	 */
	private QueueService queueService;

	@Value("${queue.name}")
	private String queueName;

	@Value("${worker.name}")
	private String workerName;

	@Value("${store.enabled}")
	private boolean storeEnabled;

	@Value("${worker.enabled}")
	private boolean workerEnabled;

	/**
	 * Class constructor with @autowire annotation
	 * 
	 * @param UserService @see {@link UserService}
	 */
	@Autowired
	public UserQueueControllerImpl(QueueService queueService) {
		this.queueService = queueService;
	}

	@Override
	@GetMapping("/home")
	public String home(Model model) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		int pendingMessages = queueService.pendingJobs(queueName);
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("pendingJobs", pendingMessages);
		model.addAttribute("completedJobs", queueService.completedJobs());
		model.addAttribute("isConnected", queueService.isUp() ? "yes" : "no");
		model.addAttribute("queueName", this.queueName);
		model.addAttribute("workerName", this.workerName);
		model.addAttribute("isStoreEnabled", this.storeEnabled);
		model.addAttribute("isWorkerEnabled", this.workerEnabled);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return "home";
	}

	@Override
	@PostMapping("/submit")
	public String submit(@ModelAttribute Ticket ticket) {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		for (long i = 0; i < ticket.getQuantity(); i++) {
            String id = UUID.randomUUID().toString();
            queueService.send(queueName, id);
        }
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return "success";
	}

	@Override
	@GetMapping(value="/metrics", produces="text/plain")
	public String metrics() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		int totalMessages = queueService.pendingJobs(queueName);
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return "# HELP messages Number of messages in the queueService\n"
        + "# TYPE messages gauge\n"
        + "messages " + totalMessages;
	}

	@Override
	@GetMapping(value="/health")
	public ResponseEntity<HttpStatus> health() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		HttpStatus status;
        if (queueService.isUp()) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return new ResponseEntity<>(status);
	}

}
