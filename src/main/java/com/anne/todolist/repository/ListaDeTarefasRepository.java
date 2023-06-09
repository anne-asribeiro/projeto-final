package com.anne.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anne.todolist.model.ListaDeTarefas;

public interface ListaDeTarefasRepository extends JpaRepository<ListaDeTarefas, Integer> {

}
