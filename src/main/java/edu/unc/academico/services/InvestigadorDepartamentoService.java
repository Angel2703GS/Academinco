package edu.unc.academico.services;

import edu.unc.academico.domain.Investigador;

public interface InvestigadorDepartamentoService {
	
	public Investigador relaplaceDpto(Long idInv, Long idDpto);
	public void removeDpto(Long idInv);
}
