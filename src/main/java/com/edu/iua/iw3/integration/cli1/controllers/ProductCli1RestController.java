package com.edu.iua.iw3.integration.cli1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.iua.iw3.controllers.BaseRestController;
import com.edu.iua.iw3.controllers.Constants;
import com.edu.iua.iw3.integration.cli1.business.IProductCli1Business;
import com.edu.iua.iw3.integration.cli1.model.ProductCli1;
import com.edu.iua.iw3.model.business.BusinessException;
import com.edu.iua.iw3.model.business.FoundException;
import com.edu.iua.iw3.model.business.NotFoundException;
import com.edu.iua.iw3.util.IStandardResponseBusiness;


@RestController
@RequestMapping(Constants.URL_INTEGRATION_CLI1 + "/products")
@Profile("cli1")
public class ProductCli1RestController extends BaseRestController {

	@Autowired
	private IProductCli1Business productBusiness;

	@Autowired
	private IStandardResponseBusiness response;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		try {
			return new ResponseEntity<>(productBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{codCli1}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadByCode(@PathVariable("codCli1") String codCli1) {
		try {
			return new ResponseEntity<>(productBusiness.load(codCli1), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<?> add(@RequestBody ProductCli1 product) {
		try {
			ProductCli1 r = productBusiness.add(product);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constants.URL_INTEGRATION_CLI1 + "/products/" + r.getCodCli1());
			return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (FoundException e) {
			return new ResponseEntity<>(response.build(HttpStatus.FOUND, e, e.getMessage()), HttpStatus.FOUND);
		}
	}



}