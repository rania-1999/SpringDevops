package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Reglement;

public interface IReglementService {

	List<Reglement> retrieveAllReglements();

	Reglement addReglement(Reglement r);

	Reglement retrieveReglement(Long id);

}
