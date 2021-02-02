package by.dulik.eureka.data.repository;

import by.dulik.eureka.data.entity.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    List<Message> findAllByUserId(String id);
}
