package com.esprit.examen.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.services.IFactureService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des factures")
@RequestMapping("/facture")
@CrossOrigin("*")
public class FactureRestController {

	@Autowired
	IFactureService factureService;

	// http://localhost:8089/SpringMVC/facture/retrieve-all-factures
	@GetMapping("/retrieve-all-factures")
	@ResponseBody
	public List<Facture> getFactures() {
		return factureService.retrieveAllFactures();
	}

	// http://localhost:8089/SpringMVC/facture/retrieve-facture/8
	@GetMapping("/retrieve-facture/{facture-id}")
	@ResponseBody
	public Facture retrieveFacture(@PathVariable("facture-id") Long factureId) {
		return factureService.retrieveFacture(factureId);
	}

	@PostMapping("/add-facture")
	@ResponseBody
	public Facture addFacture(@RequestBody Facture f) {

		return factureService.addFacture(f);
	}

	/*
	 * une facture peut etre annulé si elle a été saisie par erreur Pour ce faire,
	 * il suffit de mettre le champs active à false
	 */
	@PutMapping("/cancel-facture/{facture-id}")
	@ResponseBody
	public void cancelFacture(@PathVariable("facture-id") Long factureId) {
		factureService.cancelFacture(factureId);
	}

	@GetMapping(value = "/pourcentageRecouvrement/{startDate}/{endDate}")
	public float pourcentageRecouvrement(
			@PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		try {
			return factureService.pourcentageRecouvrement(startDate, endDate);
		} catch (Exception e) {
			return 0;
		}
	}

}
