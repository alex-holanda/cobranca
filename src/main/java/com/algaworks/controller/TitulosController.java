package com.algaworks.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.controller.service.CadastroTituloService;
import com.algaworks.model.StatusTitulo;
import com.algaworks.model.Titulo;
import com.algaworks.repository.filter.TituloFilter;

@Controller
@RequestMapping("/titulos")
public class TitulosController {
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
		
		mv.addObject("titulos", todosTitulos);
		
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Titulo titulo) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		
		mv.addObject(titulo);
		
		return mv;
	}
	
	@PostMapping("/novo")
	public String salvar(@Validated Titulo titulo, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return "CadastroTitulo";
		}
		
		try {
			cadastroTituloService.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			result.reject("dataVencimento", null, e.getMessage());
		}
		
		
		return "CadastroTitulo";
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody  String deletar(@PathVariable("codigo") Titulo titulo) {
		
		cadastroTituloService.excluir(titulo);
		
		return "SUCCESS";
	}
	
	@PutMapping("/{codigo}/receber")
	public @ResponseBody String receber(@PathVariable("codigo") Titulo titulo) {
		
		return cadastroTituloService.receber(titulo);
		
	}
	
	
	@ModelAttribute("statusTitulo")
	public List<StatusTitulo> statusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
