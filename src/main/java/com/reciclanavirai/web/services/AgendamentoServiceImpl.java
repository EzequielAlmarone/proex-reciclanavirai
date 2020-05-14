package com.reciclanavirai.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reciclanavirai.web.domain.Agendamento;
import com.reciclanavirai.web.repositories.AgendamentoDAO;

@Service
@Transactional(readOnly = false)
public class AgendamentoServiceImpl implements AgendamentoService {
	@Autowired
	private AgendamentoDAO dao;

	@Override
	public void save(Agendamento agendamento) {
		dao.save(agendamento);

	}

	@Override
	public void update(Agendamento agendamento) {
		dao.update(agendamento);

	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Agendamento findById(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Agendamento> findAll() {
		return dao.findAll();
	}

}
