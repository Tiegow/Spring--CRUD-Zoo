package com.ufrn.SIGZoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.entity.Animal;
import com.ufrn.SIGZoo.model.entity.Veterinario;
import com.ufrn.SIGZoo.repository.AnimalRepository;
import com.ufrn.SIGZoo.repository.VeterinarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Transactional
    public Animal criar(Animal animal) {
        return animalRepository.save(animal);
    }

    @Transactional
    public void deletar(Integer id) {
        animalRepository.deleteById(id);
    }

    @Transactional
    public Animal atualizar(Integer id, Animal animalAtualizado) {
        Animal animalExistente = animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        
        animalAtualizado.setId(animalExistente.getId());
        return animalRepository.save(animalAtualizado);
    }

    @Transactional(readOnly = true)
    public Animal buscarPorId(Integer id) {
        return animalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
    }

    @Transactional
    public Animal atribuirVeterinario(Integer animalId, Integer veterinarioId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        Veterinario vetNovo = veterinarioRepository.findById(veterinarioId).orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));
        
        Veterinario vetAtual = animal.getVeterinario();

        // Mesmo veterinário
        if (vetNovo.equals(vetAtual)) {
            return animal;
        }

        // Animal já tinha um veterinário
        if (vetAtual != null) {
            vetAtual.decrementarPacientes();
            veterinarioRepository.save(vetAtual);
        }

        vetNovo.incrementarPacientes();
        veterinarioRepository.save(vetNovo);

        animal.setVeterinario(vetNovo);
        return animalRepository.save(animal);
    }

    @Transactional
    public Animal desatribuirVeterinario(Integer animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        Veterinario vetAtual = animal.getVeterinario();

        if (vetAtual != null) {
            vetAtual.decrementarPacientes();
            veterinarioRepository.save(vetAtual);
        }
        
        animal.setVeterinario(null);

        return animalRepository.save(animal);
    }
}
