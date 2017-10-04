package com.crud.tasks.repository;


import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
  @Override
  List<Task> findAll();
}
