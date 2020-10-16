package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import com.lambdaschool.bookstore.models.Wrote;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void A_findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void B_findBookById()
    {
        assertEquals("The Da Vinci Code", bookService.findBookById(28).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void B1_notFindBookById()
    {
        assertEquals("", bookService.findBookById(99999).getTitle());
    }

    @Test
    public void C_delete()
    {
        bookService.delete(26);
        assertEquals(4, bookService.findAll().size());
    }

    @Test
    public void D_save()
    {
        Author a6 = new Author("Ian", "Stewart");
        a6.setAuthorid(20);
        Set<Wrote> wrote = new HashSet<>();
        wrote.add(new Wrote(a6, new Book()));
        Book b1 = new Book();
        b1.setBookid(26);
        b1.setTitle("Some Title");
        b1.setIsbn("9780738206753");
        b1.setCopy(2020);

        Section s1 = new Section("Fiction");
        s1.setSectionid(21);
        b1.setSection(s1);

        b1.setWrotes(wrote);

        Book addBook = bookService.save(b1);

        assertEquals("Some Title", addBook.getTitle());
    }

    @Test
    public void E_update()
    {
    }

    @Test
    public void F_deleteAll()
    {
    }
}