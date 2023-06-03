package com.example.axbit.service;

import com.example.axbit.model.Author;
import com.example.axbit.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl extends AbstractServiceImpl<Author, AuthorRepository> implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        super(authorRepository);
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllEntity() {
        return super.getAllEntity();
    }

    @Override
    public Author getEntityById(Long id) {
        return super.getEntityById(id);
    }

    @Override
    public void deleteEntityById(Long id) {
        super.deleteEntityById(id);
    }

    @Override
    public void createAuthor(Author author) {
        author.setCreationDate(LocalDate.now());
        author.setModificationDate(LocalDate.now());
        authorRepository.save(author);
    }

    @Override
    public void updateAuthorById(Long id, Author author) {
        Author authorUpdate = getEntityById(id);
        authorUpdate.setName(author.getName());
        authorUpdate.setSurname(author.getSurname());
        authorUpdate.setPatronymic(author.getPatronymic());
        authorUpdate.setDateOfBirth(author.getDateOfBirth());
        authorUpdate.setModificationDate(LocalDate.now());
        authorRepository.save(authorUpdate);
    }
}
