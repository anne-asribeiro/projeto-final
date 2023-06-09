package com.anne.todolist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.anne.todolist.model.ListaDeTarefas;
import com.anne.todolist.model.ListaDeTarefas;
import com.anne.todolist.repository.ListaDeTarefasRepository;

@Controller
public class ListaTarefaController {

    @Autowired
    private ListaDeTarefasRepository listaDeTarefasRepository;

    // Acessa o formulario
    @GetMapping("/formTarefa")
    public String tarefaForm(Model model, ListaDeTarefas tarefa) {
    	model.addAttribute("tarefa", tarefa);
        return "addTarefasForm";
    }

    // Adiciona novo ListaDeTarefas
    @PostMapping("/addTarefa")
    public String novo(@Valid ListaDeTarefas tarefa, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }

        listaDeTarefasRepository.save(tarefa);

        return "redirect:/home";
    }

    // Acessa o formulario de edição
    @GetMapping("formTarefa/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") int id) {

        ListaDeTarefas tarefa = listaDeTarefasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
       
        model.addAttribute("tarefa", tarefa);
        return "atualizaForm";
    }

    // Atualiza tarefa
    @PostMapping("updateTarefa/{id}")
    public String alterarProduto(@Valid ListaDeTarefas tarefa, BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "redirect:/form";
        }
        
        listaDeTarefasRepository.save(tarefa);
        return "redirect:/home";
    }

    @GetMapping("deleteTarefa/{id}")
    @CacheEvict(value = "listaDeTarefas", allEntries = true)
    public String delete(@PathVariable(name = "id") int id, Model model) {

        ListaDeTarefas tarefa = listaDeTarefasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        listaDeTarefasRepository.delete(tarefa);
        return "redirect:/home";
    }

}
