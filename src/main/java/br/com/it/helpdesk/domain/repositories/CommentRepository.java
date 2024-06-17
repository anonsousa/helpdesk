package br.com.it.helpdesk.domain.repositories;

import br.com.it.helpdesk.domain.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
