package org.example.Dao;

import org.example.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotesDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NotesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Note> selectAll(Long user_id) {
        return jdbcTemplate.query("SELECT * FROM Notes WHERE user_id=? ORDER BY id", new Object[]{user_id}, new BeanPropertyRowMapper<>(Note.class));
    }

    public Note selectOne(Long id) {
        return jdbcTemplate.query("SELECT * FROM Notes WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Note.class))
                .stream().findAny().orElse(null);
    }

    public void addNote(Note note) {
        jdbcTemplate.update("INSERT INTO Notes(title, date, status, user_id)  VALUES(?,?, ?, ?)",
                note.getTitle(), note.getDate(), note.isStatus(), note.getUser_id());
    }

    public void updateNote(Long id, Note note) {
        jdbcTemplate.update("UPDATE Notes SET title=?, date=?, status=? WHERE  id=? and user_id=?", note.getTitle(),
                note.getDate(), note.isStatus(), id, note.getUser_id());

    }

    public void changeStatus(Long id, Note note) {
        jdbcTemplate.update("UPDATE Notes SET status=? WHERE  id=? and user_id=?", note.isStatus(), id, note.getUser_id());
    }

    public void deleteNote(Long id, Long user_id) {
        jdbcTemplate.update("DELETE FROM Notes WHERE id=? and user_id=?", id, user_id);
    }


}
