package com.creditdatamw.zerocell;

import com.creditdatamw.zerocell.handler.EntityHandler;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class TestReaderUtil {

    @Test
    public void testCanProcessFilePath() {
        final EntityHandler<Person> entityHandler = new EntityHandler<>(
            Person.class,
            "uploads",
            false,
            0
        );

        ReaderUtil.process(
            "src/test/resources/test_people.xlsx",
            entityHandler.getSheetName(),
            entityHandler.getEntitySheetHandler()
        );

        List<Person> people = entityHandler.readAsList();

        assertNotNull(people);
        assertFalse(people.isEmpty());
        assertEquals(5, people.size());

        Person zikani = people.get(0);

        assertEquals(1, zikani.getRowNumber());
        assertEquals("Zikani", zikani.getFirstName());
    }

    @Test
    public void testCanProcessFile() {
        final EntityHandler<Person> entityHandler = new EntityHandler<>(
            Person.class,
            "uploads",
            false,
            0
        );
        ReaderUtil.process(
            new File("src/test/resources/test_people.xlsx"),
            entityHandler.getSheetName(),
            entityHandler.getEntitySheetHandler()
        );

        List<Person> people = entityHandler.readAsList();

        assertNotNull(people);
        assertFalse(people.isEmpty());
        assertEquals(5, people.size());

        Person zikani = people.get(0);

        assertEquals(1, zikani.getRowNumber());
        assertEquals("Zikani", zikani.getFirstName());
    }

    @Test
    public void testCanProcessInputStream() throws IOException {
        final EntityHandler<Person> entityHandler = new EntityHandler<>(
            Person.class,
            "uploads",
            false,
            0
        );
        ReaderUtil.process(
            Files.newInputStream(
                Paths.get("src", "test", "resources", "test_people.xlsx")),
            entityHandler.getSheetName(),
            entityHandler.getEntitySheetHandler()
        );

        List<Person> people = entityHandler.readAsList();

        assertNotNull(people);
        assertFalse(people.isEmpty());
        assertEquals(5, people.size());

        Person zikani = people.get(0);

        assertEquals(1, zikani.getRowNumber());
        assertEquals("Zikani", zikani.getFirstName());
    }
}