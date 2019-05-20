package com.transactions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class Controller {


	public static final Logger logger = LoggerFactory.getLogger(Controller.class);
	List<Trans> listaTrans = new ArrayList<Trans>();

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public ResponseEntity<List<Trans>> list() {
	
		if (listaTrans.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Trans>>(listaTrans, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/transactions/{transaction_id}", method = RequestMethod.GET)
	public ResponseEntity<Trans> listOne(@PathVariable("transaction_id") long transaction_id) {
		Trans trans = null;
		
		for (Trans trans_ : listaTrans) {
			if(trans_.getId() == transaction_id)trans = trans_;
		}
		
		if (trans == null) {
			return new ResponseEntity(
					new CustomErrorType("404", "Transação não Encontrada!"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trans>(trans, HttpStatus.OK);
	}
	
	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Trans trans, UriComponentsBuilder ucBuilder) {
		
		logger.info("Creating User : {}", trans);

	/*	if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getFull_name());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A User with name " + user.getFull_name() + " already exist."),
					HttpStatus.CONFLICT);
		}*/
		//userService.saveUser(user);
		
		if(trans.getValue() >= 100){
			return new ResponseEntity(
					new CustomErrorType("401", "Transação não autorizada, pois o valor não pode ser superior há 99"),
					HttpStatus.UNAUTHORIZED);
		}else{
			SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date startDate = new Date();
			System.out.println("startDate :" + startDate);
			Timestamp startDateTs = new Timestamp(startDate.getTime());
			System.out.println("startDateTs :" + startDateTs);
			
			trans.setTimestamp(startDateTs);
			int size = listaTrans.size();
			
			System.out.println("size :" + size);
			trans.setId(new Long(size+1));
			listaTrans.add(trans);
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(trans.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	

}