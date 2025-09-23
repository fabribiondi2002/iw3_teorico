package com.edu.iua.iw3.integration.cli2.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.iw3.controllers.BaseRestController;
import com.edu.iua.iw3.controllers.Constants;
import com.edu.iua.iw3.integration.cli2.business.IProductCli2Business;
import com.edu.iua.iw3.model.business.BusinessException;
import com.edu.iua.iw3.util.IStandardResponseBusiness;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constants.URL_INTEGRATION_CLI2 + "/products")
@Slf4j
@Profile("cli2")
public class ProductCli2RestController extends BaseRestController {

	@Autowired
	private IProductCli2Business productBusiness;

	@Autowired
	private IStandardResponseBusiness response;

	@GetMapping(value = "/list-expired", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listExpired(
			@RequestParam(name = "since", required = false, defaultValue = "1970-01-01 00:00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date since) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(since);
			if (c.get(Calendar.YEAR) == 1970) {
				since = new Date();
			}
			log.debug(since.toString());
			return new ResponseEntity<>(productBusiness.listExpired(since), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}