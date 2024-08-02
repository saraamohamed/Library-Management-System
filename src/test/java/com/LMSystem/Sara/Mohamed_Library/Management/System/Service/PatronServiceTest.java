package com.LMSystem.Sara.Mohamed_Library.Management.System.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.Patron;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.PatronRepository;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Services.PatronService;

@ExtendWith(MockitoExtension.class)
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @Test
    public void testGetAllPatrons() {
        Patron patron1 = new Patron();
        patron1.setId(1L);
        patron1.setName("John Doe");
        patron1.setContactInformation("john@example.com");

        Patron patron2 = new Patron();
        patron2.setId(2L);
        patron2.setName("Jane Doe");
        patron2.setContactInformation("jane@example.com");

        when(patronRepository.findAll()).thenReturn(Arrays.asList(patron1, patron2));

        List<Patron> patrons = patronService.getAllPatrons();

        assertEquals(2, patrons.size());
        verify(patronRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatronById() {
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        patron.setName("John Doe");
        patron.setContactInformation("john@example.com");

        when(patronRepository.findById(id)).thenReturn(Optional.of(patron));

        Optional<Patron> foundPatron = patronService.getPatronById(id);

        assertTrue(foundPatron.isPresent());
        assertEquals("John Doe", foundPatron.get().getName());
        verify(patronRepository, times(1)).findById(id);
    }

    @Test
    public void testAddPatron() {
        Patron patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
        patron.setContactInformation("john@example.com");

        when(patronRepository.save(patron)).thenReturn(patron);

        Patron savedPatron = patronService.addPatron(patron);

        assertNotNull(savedPatron);
        assertEquals("John Doe", savedPatron.getName());
        verify(patronRepository, times(1)).save(patron);
    }

    @Test
    public void testUpdatePatron() {
        Long id = 1L;
        Patron existingPatron = new Patron();
        existingPatron.setId(id);
        existingPatron.setName("John Doe");
        existingPatron.setContactInformation("john@example.com");

        Patron updatedDetails = new Patron();
        updatedDetails.setName("John Smith");
        updatedDetails.setContactInformation("johnsmith@example.com");

        when(patronRepository.findById(id)).thenReturn(Optional.of(existingPatron));
        when(patronRepository.save(any(Patron.class))).thenReturn(existingPatron);

        Patron updatedPatron = patronService.updatePatron(id, updatedDetails);

        assertNotNull(updatedPatron);
        assertEquals("John Smith", updatedPatron.getName());
        assertEquals("johnsmith@example.com", updatedPatron.getContactInformation());
        verify(patronRepository, times(1)).findById(id);
        verify(patronRepository, times(1)).save(existingPatron);
    }

    @Test
    public void testDeletePatron() {
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        patron.setName("John Doe");
        patron.setContactInformation("john@example.com");

        when(patronRepository.findById(id)).thenReturn(Optional.of(patron));

        patronService.deletePatron(id);

        verify(patronRepository, times(1)).delete(patron);
    }
}
