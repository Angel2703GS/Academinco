package edu.unc.academico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.academico.domain.Departamento;
import edu.unc.academico.domain.Investigador;
import edu.unc.academico.repository.DepartamentoRepository;
import edu.unc.academico.repository.InvestigadorRepository;

@Service
public class InvestigadorDepartamentoServiceImp implements InvestigadorDepartamentoService {

	@Autowired
	private InvestigadorRepository invRep;
	
	@Autowired
	private DepartamentoRepository dptoRep;
	
	@Override
	@Transactional
	public Investigador relaplaceDpto(Long idInv, Long idDpto) {
		Optional<Investigador> invEntity = invRep.findById(idInv);
		Optional<Departamento> dptoEntity = dptoRep.findById(idDpto);
		
		invEntity.get().setDepartamento(dptoEntity.get());
		return invEntity.get();
	}

	@Override
	@Transactional
	public void removeDpto(Long idInv) {
		Optional<Investigador> invEntity = invRep.findById(idInv);
        Optional<Departamento> departamento = dptoRep
                .findById(invEntity.get().getDepartamento().getIdDpto());
        departamento.ifPresent(dpto 
                -> dpto.getInvestigadores().remove(invEntity.get()));
        invEntity.get().setDepartamento(null);
	}

}
