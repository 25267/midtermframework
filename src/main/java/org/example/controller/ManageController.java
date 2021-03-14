package org.example.controller;

import org.example.Dao.NotesDao;
import org.example.Dao.UserDao;
import org.example.events.loginEvent;
import org.example.events.loginEventHandler;
import org.example.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
public class ManageController implements ApplicationEventPublisherAware {
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    private final NotesDao notesDao;
    private final UserDao userDao;
    private Long userId;
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    public ManageController(NotesDao notesDao, UserDao userDao) {
        this.notesDao = notesDao;
        this.userDao = userDao;
    }


    public boolean login() throws IOException {
        System.out.println("Enter name");
        String name = read.readLine();
        System.out.println("Enter password");
        String password = read.readLine();

        if (userDao.login(name, password) != null) {

            userId = userDao.login(name, password).getId();

            this.eventPublisher.publishEvent(new loginEvent(this, userDao.login(name,password).getName()));
            return true;
        } else {

            return false;
        }

    }


    public void registration() throws IOException {
        System.out.println("Enter name");
        String name = read.readLine();
        System.out.println("Enter password");
        String password = read.readLine();

        userDao.registration(name, password);

        System.out.println("registered");

    }




    public void selectAll(){
        for(Note note : notesDao.selectAll(userId)){
            System.out.println("id: " + note.getId() + "\n" + "title: " + note.getTitle()
                    + "\n" + "target date: " + note.getDate() + "\n" + "status: " + note.isStatus() + "\n" );

        }
    }


    public void addNote() throws IOException {
        System.out.println("Enter title");
        String newNoteTitle = read.readLine();
        System.out.println("Enter target date DD/MM/YYYY");
        String newNoteDate = read.readLine();
        System.out.println("Enter status false or true");
        boolean newNoteStatus = Boolean.parseBoolean(read.readLine());

        Note note =  new Note(newNoteTitle,newNoteDate,newNoteStatus, userId);

        notesDao.addNote(note);
        System.out.println("Added");
    }

    public void updateNote() throws IOException {
        System.out.println("Enter id of the Note");
        Long noteId = Long.valueOf(read.readLine());
        System.out.println("Enter title");
        String newNoteTitle = read.readLine();
        System.out.println("Enter target date DD/MM/YYYY");
        String newNoteDate = read.readLine();
        System.out.println("Enter status false or true");
        boolean newNoteStatus = Boolean.parseBoolean(read.readLine());

        Note note =  new Note(newNoteTitle,newNoteDate,newNoteStatus, userId);
        notesDao.updateNote(noteId, note);
        System.out.println("Updated");
    }


    public void changeStatus() throws IOException {
        System.out.println("Enter id of the Note");
        Long noteId = Long.valueOf(read.readLine());
        System.out.println("Enter status false or true");
        boolean newNoteStatus = Boolean.parseBoolean(read.readLine());


        Note note = new Note(newNoteStatus, userId);

        notesDao.changeStatus(noteId, note);

    }


    public void deleteNote() throws IOException {
        System.out.println("Enter id of the Note");
        Long noteId = Long.valueOf(read.readLine());

        notesDao.deleteNote(noteId, userId);
        System.out.println("Deleted");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
