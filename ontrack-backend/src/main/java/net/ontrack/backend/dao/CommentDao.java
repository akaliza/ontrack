package net.ontrack.backend.dao;

import net.ontrack.backend.dao.model.TComment;
import net.ontrack.core.model.Entity;
import org.joda.time.DateTime;

import java.util.Collection;

public interface CommentDao {

    int createComment(Entity entity, int entityId, String content, String author, Integer authorId);

    Collection<TComment> findByEntityAndText(Entity entity, String text);

    void renameAuthor(int id, String name);

    Collection<TComment> findByEntity(Entity entity, int entityId, int offset, int count);

    int importComment(Entity entity, int entityId, String content, String author, Integer authorId, DateTime timestamp);
}
