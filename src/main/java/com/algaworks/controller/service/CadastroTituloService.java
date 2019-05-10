package com.algaworks.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.model.StatusTitulo;
import com.algaworks.model.Titulo;
import com.algaworks.repository.Titulos;
import com.algaworks.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}
	
	public void excluir(Titulo titulo) {
		titulos.delete(titulo);
	}

	public String receber(Titulo titulo) {
		titulo.setStatus(StatusTitulo.RECEBIDO);
		
		salvar(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();

		return titulos.findByDescricaoContaining(descricao);
		
	}
}
