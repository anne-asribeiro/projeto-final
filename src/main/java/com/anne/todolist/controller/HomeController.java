package com.anne.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.anne.todolist.model.ListaDeTarefas;
import com.anne.todolist.repository.ListaDeTarefasRepository;

@Controller
public class HomeController {

	@Autowired
	private ListaDeTarefasRepository listaDeTarefasRepository;

	@GetMapping("/home")
	public String home(Model model) {
		List<ListaDeTarefas> listaDeTarefas = listaDeTarefasRepository.findAll();

		model.addAttribute("listaDeTarefas", listaDeTarefas);
		return "home";
	}

}
