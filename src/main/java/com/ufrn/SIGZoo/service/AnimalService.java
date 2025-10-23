package com.ufrn.SIGZoo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.SIGZoo.model.dto.AnimalDTO;
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
    public AnimalDTO criar(AnimalDTO dto) {
        Animal animal = new Animal();

        animal.setNome(dto.getNome());
        animal.setSexo(dto.getSexo());
        animal.setNascimento(dto.getNascimento());
        animal.setOrigem(dto.getOrigem());
        
        if (dto.getVeterinarioId() != null) {
            Veterinario vet = veterinarioRepository.findById(dto.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinário com ID " + dto.getVeterinarioId() + " não encontrado!"));
            animal.setVeterinario(vet);
            vet.incrementarPacientes();

            veterinarioRepository.save(vet);
        }   

        animalRepository.save(animal);

        return toDTO(animal);
    }

    @Transactional
    public void deletar(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));

        Veterinario vetAtual = animal.getVeterinario();

        if (vetAtual != null) {
            vetAtual.decrementarPacientes();
            veterinarioRepository.save(vetAtual);
        }

        animalRepository.delete(animal);
    }

    @Transactional
    public AnimalDTO atualizar(Integer id, AnimalDTO dto) {
        Animal animalExistente = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));

        animalExistente.setNome(dto.getNome());
        animalExistente.setSexo(dto.getSexo());
        animalExistente.setNascimento(dto.getNascimento());
        animalExistente.setOrigem(dto.getOrigem());
        
        Integer vetAtualId = (animalExistente.getVeterinario() != null) ? animalExistente.getVeterinario().getId() : null;
        
        Integer vetNovoId = dto.getVeterinarioId();

        if (vetNovoId != null) {
            if (!vetNovoId.equals(vetAtualId)) {
                return toDTO(atribuirVeterinario(id, vetNovoId));
            }
        } 
        else if (vetAtualId != null) {
            return toDTO(desatribuirVeterinario(id));
        }

        animalRepository.save(animalExistente);

        return toDTO(animalExistente);
    }

    @Transactional(readOnly = true)
    public Page<AnimalDTO> listarTodos(Pageable pageable) {
        Page<Animal> animalPage = animalRepository.findAll(pageable);
        return animalPage.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public List<Animal> listarPorVeterinario(Integer idVet) {
        return animalRepository.findAllByVeterinarioId(idVet);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> listarPorSexo(String sexo) {
        List<Animal> animais = animalRepository.findAllBySexo(sexo);
        return listDTO(animais);
    }

    @Transactional(readOnly = true)
    public AnimalDTO buscarPorId(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));
        return toDTO(animal);
    }

    @Transactional(readOnly = true)
    public long obterQtdAnimais() {
        return animalRepository.count();
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

    private AnimalDTO toDTO(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalDTO dto = new AnimalDTO();
        
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        dto.setSexo(animal.getSexo());
        dto.setNascimento(animal.getNascimento());
        dto.setOrigem(animal.getOrigem());
        if (animal.getVeterinario() != null) {
            dto.setVeterinarioId(animal.getVeterinario().getId());
            dto.setVeterinarioNome(animal.getVeterinario().getNome());
        }
        return dto;
    }

    private List<AnimalDTO> listDTO(List<Animal> animais) {
        return animais.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
