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
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void createAuthor(Author author) {
        author.setDateOfCreation(LocalDate.now());
        author.setModificationDate(LocalDate.now());
        authorRepository.save(author);
    }

    @Override
    public void updateAuthorById(Long id, Author author) {
        Author authorUpdate = getAuthorById(id);
        authorUpdate.setName(author.getName());
        authorUpdate.setSurname(author.getSurname());
        authorUpdate.setPatronymic(author.getPatronymic());
        authorUpdate.setDateOfBirth(author.getDateOfBirth());
        authorUpdate.setModificationDate(LocalDate.now());
        authorRepository.save(authorUpdate);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
}
