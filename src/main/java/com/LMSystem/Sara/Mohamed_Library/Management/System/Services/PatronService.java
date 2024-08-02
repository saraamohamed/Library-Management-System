package com.LMSystem.Sara.Mohamed_Library.Management.System.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.Patron;
import com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories.PatronRepository;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new RuntimeException("Patron not found"));
        patron.setName(patronDetails.getName());
        patron.setContactInformation(patronDetails.getContactInformation());
        return patronRepository.save(patron);
    }

    @Transactional
    public void deletePatron(Long id) {
        Patron patron = patronRepository.findById(id).orElseThrow(() -> new RuntimeException("Patron not found"));
        patronRepository.delete(patron);
    }
}
